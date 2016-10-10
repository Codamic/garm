(ns garm.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response]]
            [garm.controllers.home :refer [home]]
            [garm.controllers.dashboard :refer [dashboard]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defroutes routes
  (GET "/" [] home)
  (GET "/dashboard" [] dashboard)
  (resources "/"))

(def dev-handler (-> #'routes wrap-reload))

(def handler routes)
