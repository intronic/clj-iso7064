(ns clj-iso7064.core
  (:require [clojure.pprint :refer [cl-format]]))

(defprotocol iso7064PureSystem
  (checksum [this s] "Return checksum digits for s.")
  (compute [this s] "Compute checksum digits and append to s.")
  (strip [this s] "Strip checksum digits and return s (without verification).")
  (verify [this s] "Verify checksum digits of s."))

(deftype ^:private iso7064 [^Integer modulus ^Integer radix delim]

  iso7064PureSystem

  ;; Notes on checksum:-
  ;; Calculate 2-digit checksum (uppercase) on alphanumeric field igoring all
  ;; non-alphanumeric characters as fillers
  ;; cl-format notes:
  ;;   Format number radix 36, fixed width 2 columns, with leading 0: ~36,2,'0r
  ;;   Format number radix 10, fixed width 2 columns, with leading 0:
  ;;     Decimal format code: ~2,'0d
  ;;     Radix format code:   ~10,2,'0r
  ;;   Format data between parens in uppercase:  ~:@(~)
  ;;   http://www.lispworks.com/documentation/HyperSpec/Body/22_cba.htm
  ;; Be careful with display font because 0/O, 1/I/l confusion

  (checksum [this s]
    (assert (or (= [97 10] [modulus radix])
                (= [1271 36] [modulus radix])))
    (let [in (remove neg? (map #(java.lang.Character/digit ^Character % radix)
                               (seq (str s 0))))
          calc (reduce (fn [p val] (mod (* (+ p val) radix) modulus)) 0 in)
          cksum (mod (+ (- modulus calc) 1) modulus)]

      (case radix
        10 (cl-format nil "~:@(~10,2,'0r~)" cksum)
        36 (cl-format nil "~:@(~36,2,'0r~)" cksum)
        nil)))

  (compute [this s]
    (str s delim (checksum this s)))

  (strip [this s]
    (subs s 0 (- (count s) 2 (count delim))))

  (verify [this s]
    (.endsWith (or ^String s "") ^String (checksum this (strip this s)))))

(defn mod-97-10
  ([] (mod-97-10 ""))
  ([delim] (->iso7064 97 10 delim)))

(defn mod-1271-36
  ([] (mod-1271-36 ""))
  ([delim] (->iso7064 1271 36 delim)))
