(ns garm.logger)


(defn log
  [& rest]
  (js/console.log (apply str rest)))
