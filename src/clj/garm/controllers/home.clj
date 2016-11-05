(ns garm.controllers.home
  (:require [compojure.route :refer [resources]]
            [selmer.parser :as parser]))


(defn home
  "Home page controller."
  [request]
  (parser/render-file "views/home/index.html" {}))
