(ns garm.handlers
  (:require [re-frame.core :as re-frame]
            [hell-hound.frontend.fx.jquery]
            [garm.logger :refer [log]]
            [hell-hound.connection.client :as channels]
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

(re-frame/reg-event-fx
 :jquery
 (fn [_ [_ selector func & values]]
   (log "JQUERY HANDLER:")
   (log selector)
   (log func)
   (log values)
   {:jquery {:method func :values values :selector selector}}))



(go-loop [event (<! channels/ch-chsk)]
  (println "<<<<<<<<<<")
  (println  (:event event))
  ;(println (keys event))
  ;(println (<! (:ch-recv event)))
  ;(println (:send-fn event))
  ;(println (clj->js (:state event)))
  ;(println (:event  event))
  ;(println (:id event))
  ;(println (:?data  event))
  )
