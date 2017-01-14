(ns garm.components.crawler
  "This namespace contains the Component to run the
  crawler scheduler."
  (:require [hellhound.scheduler            :as s]
            [hellhound.logger.core          :as logger]
            [com.stuartsierra.component     :as component]
            [garm.parsers.tse               :refer [fetch-and-parse]]
            [hellhound.components.websocket :refer [send-to-all]]))


(defn job
  []
  (let [data (fetch-and-parse)]
    (send-to-all [:garm/symbol-table (into [] data)])))

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
