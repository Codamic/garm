(ns garm.views
  (:require [re-frame.core :as re-frame]
            [hell-hound.i18n.core :as i]
            [garm.locale :refer [dict]]
            [garm.views.dashboard :refer [dashboard]]))




;; main

(defmulti panels identity)
(defmethod panels :dashboard [] [dashboard])
(defmethod panels :default [] [:div])

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])
        lang         (re-frame/subscribe [:lang])]
    (i/init dict @lang)
    (fn []
      [show-panel @active-panel])))
