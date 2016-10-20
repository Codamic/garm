(ns garm.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response]]
            [garm.controllers.home :refer [home]]
            [garm.controllers.dashboard :refer [dashboard]]
            [hell-hound.connection.server :as connection]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defroutes routes
  (GET "/" [] home)
  (GET "/dashboard" [] dashboard)
  (connection/routes)
  (resources "/"))

(def dev-handler (-> #'routes
                     wrap-keyword-params
                     wrap-params
                     wrap-reload))

(def handler routes)
