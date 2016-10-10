(ns garm.views
  (:require [re-frame.core :as re-frame]
            [garm.views.dashboard :refer [dashboard]]))




;; main

(defmulti panels identity)
(defmethod panels :dashboard [] [dashboard])
(defmethod panels :default [] [:div])

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [show-panel @active-panel])))
