(ns garm.handler
  (:require [compojure.core               :refer [GET defroutes]]
            [compojure.route                :refer [resources]]
            [cemerick.friend  :as friend]
            (cemerick.friend [workflows   :as workflows]
                             [credentials :as creds])

            [ring.util.response             :refer [resource-response]]
            [garm.controllers.home          :refer [home]]
            [garm.controllers.dashboard     :refer [dashboard]]
            [hellhound.connection.server    :as    connection]
            [ring.middleware.params         :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.reload         :refer [wrap-reload]]
            [ring.middleware.anti-forgery   :refer [wrap-anti-forgery]]
            [ring.middleware.session        :refer [wrap-session]]))

(def users {"root" {:username "root"
                    :password (creds/hash-bcrypt "admin_password")
                    :roles #{::admin}}
            "jane" {:username "jane"
                    :password (creds/hash-bcrypt "user_password")
                    :roles #{::user}}})

(defroutes routes
  (GET "/" [] home)
  (GET "/dashboard" [] dashboard)
  (connection/routes)
  ;;TODO: This route should be exists only in development
  (resources "/" {:root ""}))

(def secured-app (-> routes
                     (friend/authenticate {:credential-fn (partial creds/bcrypt-credential-fn users)
                                           :workflows [(workflows/interactive-form)]})))
(def dev-handler (-> #'secured-app
                     wrap-keyword-params
                     wrap-params
                     wrap-anti-forgery
                     wrap-session
                     wrap-reload))

(def handler secured-app)
