(ns garm.handler
  (:require [compojure.core                 :refer [GET defroutes]]
            [compojure.route                :refer [resources]]
            [ring.util.response             :refer [resource-response]]
            [garm.controllers.home          :refer [home]]
            [garm.controllers.dashboard     :refer [dashboard]]
            [hellhound.connection.server    :as    connection]
            [ring.middleware.params         :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.reload         :refer [wrap-reload]]
            [ring.middleware.anti-forgery   :refer [wrap-anti-forgery]]
            [ring.middleware.session        :refer [wrap-session]]))

(defroutes routes
  (GET "/" [] home)
  (GET "/dashboard" [] dashboard)
  (connection/routes)
  ;;TODO: This route should be exists only in development
  (resources "/" {:root ""}))

(def dev-handler (-> #'routes
                     wrap-keyword-params
                     wrap-params
                     wrap-anti-forgery
                     wrap-session
                     wrap-reload))

(def handler routes)
