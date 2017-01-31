(ns garm.handlers
  (:require [re-frame.core :as re-frame]
            [hellhound.frontend.fx.jquery]
            [hellhound.connection.client :as channels]
            [garm.logger :refer [log]]
            [garm.db :as db])
  (:require-macros
   [cljs.core.async.macros :as asyncm :refer (go go-loop)]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
 :toggle-sidebar
 (fn [db [_]]
   (assoc db :sidebar-expanded (not (:sidebar-expanded db)))))

(re-frame/reg-event-db
 :app-db/update
 (fn [db [_ keys value]]
   (js/console.log (str "Updating '" (first keys) "'..."))
   (assoc db (first keys) (second value))))

(re-frame/reg-event-fx
 :jquery
 (fn [_ [_ selector func & values]]
   (log "JQUERY HANDLER:")
   (log selector)
   (log func)
   (log values)
   {:jquery {:method func :values values :selector selector}}))


(re-frame/reg-event-fx
 :js/alert
 (fn [_ [_ data]]
   (js/alert data)
   {}))

(channels/start-event-router!)


;; Sente Handlers
(defmethod channels/dispatcher :garm/symbole-table
  [{:as ev-msg :keys [?data]}]
  (let [[?uid ?csrf-token ?handshake-data] ?data]
    (js/console.log (str "update data: " ?data))))
