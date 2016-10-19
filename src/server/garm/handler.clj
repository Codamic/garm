(ns garm.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response]]
            [garm.controllers.home :refer [home]]
            [garm.controllers.dashboard :refer [dashboard]]
            [hell-hound.connection.server :as connection]
            [ring.middleware.reload :refer [wrap-reload]]))

(defroutes routes
  (GET "/" [] home)
  (GET "/dashboard" [] dashboard)
  (connection/routes)
  (resources "/"))

(def dev-handler (-> #'routes wrap-reload))

(def handler routes)
