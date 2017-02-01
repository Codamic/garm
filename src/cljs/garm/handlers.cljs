(ns garm.handlers
  (:require [re-frame.core :as re-frame]
            [hellhound.handlers.jquery]
            [hellhound.handlers.app-db]
            [hellhound.connection :as channels]
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


(channels/start-event-router!)
