(ns garm.controllers.dashboard
  (:require [compojure.route :refer [resources]]
            [selmer.parser :as parser]))


(defn dashboard
  "Dashboard page controller."
  [request]
  (parser/render-file "views/dashboard/index.html" {}))
