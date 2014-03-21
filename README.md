clj-iso7064
===========

A clojure library with a partial implementation of iso7064 Pure Systems,
inspired / copied from a java implementation:

  https://github.com/danieltwagner/iso7064

## Usage

```
(require [clj-iso7064.core :refer :all])

(checksum (mod-97-10) "794")
;; "44"

(compute (mod-97-10) "794")
;; "79444"

(strip (mod-97-10) "79444")
;; "794"

(verify (mod-97-10) "79444")
;; true

(verify (mod-97-10) "79445")
;; false


(checksum (mod-1271-36) "ISO79")
;; "3W"

(checksum (mod-1271-36) "iso79")
;; "3W"

(compute (mod-1271-36) "ISO79")
;; "ISO793W"

(strip (mod-1271-36) "ISO793W")
;; "ISO79"

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

