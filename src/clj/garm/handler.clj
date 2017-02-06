(ns garm.handler
  (:require [hellhound.routes.core          :refer [make-handler route-table hellhound-routes GET redirect-to-not-found]]
            ;; [cemerick.friend  :as friend]
            ;; (cemerick.friend [workflows   :as workflows]
            ;;                  [credentials :as creds])
            [ring.logger                    :refer [wrap-with-logger]]
            [clojure.pprint                 :refer [pprint]]
            [garm.controllers.dashboard     :refer [dashboard test-handler]]
            [ring.middleware.params         :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.reload         :refer [wrap-reload]]
            [ring.middleware.anti-forgery   :refer [wrap-anti-forgery]]
            [immutant.web.middleware        :refer [wrap-development wrap-write-error-handling]]
            [hellhound.middlewares.logger   :refer [wrap-logger]]
            [ring.middleware.session        :refer [wrap-session]]
            [ring.middleware.resource       :refer  [wrap-resource]]
            [ring.middleware.content-type   :refer [wrap-content-type]]
            [ring.middleware.not-modified   :refer [wrap-not-modified]]
            [bidi.ring                      :refer [resources-maybe resources]]))


(def routes (make-handler
             ["/" [(GET "" dashboard)
                   (GET  "test"  test-handler)
                   ;["public" (resources-maybe {:prefix "assets"})]
                  (hellhound-routes)
                  (redirect-to-not-found)
                   ]]))


(def dev-handler (-> #'routes
                     wrap-keyword-params
                     wrap-params
                     wrap-logger
                     wrap-anti-forgery
                     wrap-session
                     (wrap-resource "assets")
                     wrap-content-type
                     wrap-not-modified

                     ;;wrap-with-logger
                     ;;wrap-development
                     wrap-reload))

(def handler (-> #'routes
                 wrap-keyword-params
                 wrap-params
                 wrap-anti-forgery
                 wrap-session))
