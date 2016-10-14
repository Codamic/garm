(ns garm.handlers
    (:require [re-frame.core :as re-frame]
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
   (.log js/console "HERERERE")
   (assoc db :sidebar-expanded (not (:sidebar-expanded db)))))
