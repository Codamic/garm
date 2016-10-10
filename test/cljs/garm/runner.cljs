(ns garm.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [garm.core-test]))

(doo-tests 'garm.core-test)
