(ns garm.handler
  (:require [hellhound.routes.core          :refer [make-handler route-table hellhound-routes GET redirect-to-not-found]]
            ;; [cemerick.friend  :as friend]
            ;; (cemerick.friend [workflows   :as workflows]
            ;;                  [credentials :as creds])
            [garm.controllers.dashboard     :refer [dashboard test-handler]]
            [hellhound.middlewares.core     :refer :all]))


(def routes (make-handler
             ["/" [(GET "" dashboard)
                   (GET  "test"  test-handler)
                   ;["public" (resources-maybe {:prefix "assets"})]
                  (hellhound-routes)
                  (redirect-to-not-found)
                   ]]))


(def dev-handler (-> #'routes
                     wrap-development-kit))

(def handler (-> #'routes
                 wrap-production-kit))
