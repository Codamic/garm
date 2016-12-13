(ns garm.controllers.home
  (:require [selmer.parser :as parser]))


(defn home
  "Home page controller."
  [request]
  ;; (parser/render-file "views/home/index.html" {})
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "<h1>hello</h1>"}
  )
