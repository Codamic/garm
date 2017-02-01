(ns garm.components.crawler
  "This namespace contains the Component to run the
  crawler scheduler."
  (:require [hellhound.scheduler            :as s]
            [hellhound.logger.core          :as logger]
            [com.stuartsierra.component     :as component]
            [garm.parsers.tse               :refer [parsed-data]]
            [hellhound.components.websocket :refer [send-to-all]]))

(defn job
  []
  (let [data (parsed-data)]
    (send-to-all [:app-db/update {:keys [:symbol-table] :value (into [] data)}])))

(defrecord Crawler []
  component/Lifecycle
  (start [component]
    (let [name        "crawler"]
      (logger/info "Starting Crawler Component. . .")
      (assoc component :schedule-map (s/schedule job {:id name :every :minute}))))
  (stop  [component]
    (s/stop (:schedule-map component))
    (dissoc component :schedule-map)))



(defn make-crawler
  "Creates a crawler component instance."
  ([]
   (make-crawler {}))
  ([options]
   (->Crawler)))


(defn crawler
  "Create an instance from crawler component. This function is meant
  to be used with `hellhound.system.defsystem` macro."
  ([system-map]
   (crawler system-map {}))
  ([system-map options]
   (assoc-in system-map [:crawler] (make-crawler))))
