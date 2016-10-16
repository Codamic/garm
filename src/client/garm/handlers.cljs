(ns garm.handlers
  (:require [re-frame.core :as re-frame]
            [hell-hound.frontend.fx.jquery]
            [garm.logger :refer [log]]
            [garm.db :as db]))

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

(re-frame/reg-event-fx
 :jquery
 (fn [_ [_ selector func & values]]
   (log "JQUERY HANDLER:")
   (log selector)
   (log func)
   (log values)
   {:jquery {:method func :values values :selector selector}}))
