(ns garm.views
  (:require [re-frame.core :as re-frame]
            [hellhound.i18n.core :as i]
            [garm.locale :refer [dict]]
            [garm.views.dashboard :refer [dashboard index]]
            [garm.views.about :refer [about]]))




;; main

(defmulti panels identity)
(defmethod panels :dashboard [] [dashboard [index]])
(defmethod panels :about [] [dashboard [about]])
(defmethod panels :default [] [:div [:h1 "404"]])

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])
        lang         (re-frame/subscribe [:lang])]
    (i/init dict @lang)
    (fn []
      [show-panel @active-panel])))
