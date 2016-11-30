(ns garm.controllers.dashboard
  (:require [selmer.parser :as parser]))


(defn dashboard
  "Dashboard page controller."
  [request]
  (parser/render-file "views/dashboard/index.html" {}))
