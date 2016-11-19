(ns garm.server
  (:require [garm.handler :refer [dev-handler]]
            [config.core :refer [env]]
            [org.httpkit.server :refer [run-server]]
            [com.stuartsierra.component :as component :refer [Lifecycle]]))

(defrecord WebServer [host port]
  Lifecycle
  (start [this]
    (println (str "Webserver started on " host ":" port))
    (assoc this :server (run-server #'dev-handler {:ip host :port port})))

  (stop [this]
    ((:server this))
    (dissoc this :server)))


(defn make-webserver [host port]
  (WebServer. host port))
