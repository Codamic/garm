(ns garm.handler
  (:require [hellhound.routes.core          :refer [make-handler route-table hellhound-routes]]
            ;; [cemerick.friend  :as friend]
            ;; (cemerick.friend [workflows   :as workflows]
            ;;                  [credentials :as creds])
            [ring.logger                    :refer [wrap-with-logger]]
            [clojure.pprint                 :refer [pprint]]
            [garm.controllers.home          :refer [home]]
            [garm.controllers.dashboard     :refer [dashboard]]
            [ring.middleware.params         :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.reload         :refer [wrap-reload]]
            [ring.middleware.anti-forgery   :refer [wrap-anti-forgery]]
            [immutant.web.middleware        :refer [wrap-development wrap-write-error-handling]]
            [hellhound.middlewares.logger   :refer [wrap-logger]]
            [ring.middleware.session        :refer [wrap-session]]))


(def routes (make-handler
             ["/" {"" {:get  dashboard}
                   ;"dashboard" {:get dashboard}
                   }]))


(def dev-handler (-> #'routes
                     wrap-keyword-params
                     wrap-params
                     wrap-logger
                     wrap-anti-forgery
                     wrap-session
                     ;;wrap-with-logger
                     ;;wrap-development
                     wrap-reload))

(def handler (-> #'routes
                 wrap-keyword-params
                 wrap-params
                 wrap-anti-forgery
                 wrap-session))
