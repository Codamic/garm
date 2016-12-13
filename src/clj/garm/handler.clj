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
            [ring.middleware.session        :refer [wrap-session]]))


;; (def routes (make-handler ["/"
;;                            {:get {"s" home}}]))

(def routes (make-handler
             ["/" {"" {:get  home}
                   "dashboard" {:get dashboard}}]))

(pprint "\nRoute Table:")
(pprint (route-table))

;; (defroutes "routes"
;;   (get_ "/" home))


;; (defroutes routes
;;   (GET "/" [] home)
;;   (GET "/dashboard" [] dashboard)
;;   (connection/routes)
;;   ;;TODO: This route should be exists only in development
;;   (resources "/" {:root ""}))


(def dev-handler (-> #'routes
                     wrap-keyword-params
                     wrap-params
                     wrap-anti-forgery
                     wrap-session
                     wrap-with-logger
                     wrap-reload))

(def handler (-> #'routes
                 wrap-keyword-params
                 wrap-params
                 wrap-anti-forgery
                 wrap-session))
