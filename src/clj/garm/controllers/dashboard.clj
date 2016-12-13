(ns garm.controllers.dashboard
  (:require [selmer.parser :as parser]))


(defn dashboard
  "Dashboard page controller."
  [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (parser/render-file "views/dashboard/index.html" {})
   })
