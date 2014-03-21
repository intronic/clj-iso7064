(ns clj-iso7064.expectations.core
  (:require [clojure.pprint :refer [cl-format]]
            [expectations :refer :all]
            [clj-iso7064.core :refer :all])
  (:import [clj_iso7064.core iso7064]))

(expect iso7064 (mod-97-10))
(expect iso7064 (mod-1271-36))
(expect AssertionError (checksum (->iso7064 1 10 "") "0"))
(expect AssertionError (checksum (->iso7064 97 1 "") "0"))

;;; checksums taken from:-
;;; https://github.com/danieltwagner/iso7064/blob/master/src/test/java/com/github/danieltwagner/iso7064/Mod97_10Test.java

(expect "44" (checksum (mod-97-10) "794"))
(expect "07" (checksum (mod-97-10) "107571"))

(expect "79444"    (compute (mod-97-10) "794"))
(expect "794-44"    (compute (mod-97-10 "-") "794"))
(expect "10757107" (compute (mod-97-10) "107571"))

(expect "794"  (strip (mod-97-10) "79444"))
(expect "1075" (strip (mod-97-10) "107571"))

(expect true  (verify (mod-97-10) "79444"))
(expect false (verify (mod-97-10) "79445"))

(expect true  (verify (mod-97-10) "10757107"))
(expect false (verify (mod-97-10) "10757108"))


(expect "3W" (checksum (mod-1271-36) "ISO79"))
(expect "3W" (checksum (mod-1271-36) "iso79"))

(expect "ISO793W"    (compute (mod-1271-36) "ISO79"))
(expect "iso793W"    (compute (mod-1271-36) "iso79"))

(expect "ISO79"  (strip (mod-1271-36) "ISO793W"))

(expect true  (verify (mod-1271-36) "ISO793W"))
(expect true  (verify (mod-1271-36) "iso793W"))

(expect false (verify (mod-1271-36) "ISO793w"))
(expect false (verify (mod-1271-36) "ISO793X"))
(expect false (verify (mod-1271-36) "ISO793X"))

;;; https://usersite.datalab.eu/printclass.aspx?type=wiki&id=91772
(expect "58" (checksum (mod-97-10) "0600001234567"))
(expect "0600001234567 58" (compute (mod-97-10 " ") "0600001234567"))
