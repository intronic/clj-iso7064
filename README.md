clj-iso7064
===========

A clojure library with a partial implementation of iso7064 Pure Systems,
inspired / copied from a java implementation:

  https://github.com/danieltwagner/iso7064

## Usage ##

```clj
(require '[clj-iso7064.core :refer :all])
```

### Calculate checksum digits ###

```clj
(checksum (mod-97-10) "794")
;; "44"

(checksum (mod-1271-36) "ISO79")
;; "3W"

(checksum (mod-1271-36) "iso79")
;; "3W"
```

### Compute string with optional delimiter and checksum appended ###

```clj
(compute (mod-97-10) "794")
;; "79444"

(compute (mod-97-10 "-") "794") 
;; "794-44"

(compute (mod-97-10 " ") "794") 
;; "794 44"

(compute (mod-1271-36) "ISO79")
;; "ISO793W"
```

### Strip checksum from tail returning original string ###

```clj
(strip (mod-97-10) "79444")
;; "794"

(strip (mod-97-10 "-") "794-44")
;; "794"

(strip (mod-97-10 " ") "794 44")
;; "794"

(strip (mod-1271-36) "ISO793W")
;; "ISO79"
```

### Verify string with optional delimiter and checksum appended ###

```clj
(verify (mod-97-10) "79444")
;; true

(verify (mod-97-10 "-") "794-44")
;; true

(verify (mod-97-10 " ") "794 44")
;; true

(verify (mod-97-10) "79445")
;; false

(verify (mod-1271-36) "ISO793W")
;; true

(verify (mod-1271-36) "iso793W")
;; true

(verify (mod-1271-36) "ISO793w")
;; false

(verify (mod-1271-36) "ISO793X")
;; false

(verify (mod-1271-36) "ISO793X")
;; false
```

## License

Copyright © 2014 Insilico Informatics, Pty Ltd.

Licensed under the LGPL v3.0 (GNU Lesser General Public License)
https://www.gnu.org/licenses/lgpl.html

