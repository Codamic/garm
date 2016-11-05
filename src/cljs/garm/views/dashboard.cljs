(ns garm.views.dashboard
  (:require [re-frame.core :as re-frame]
            [garm.views.navbar :refer [navbar]]
            [garm.views.sidebar :refer [sidebar]]))




(defn- wrapper-classes
  [sidebar-status]
  (if (true? sidebar-status) "enlarged forced" "expanded forced"))

(defn dashboard []
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])]
    (fn []
      [:div {:id "wrapper" :class (wrapper-classes  @sidebar-expanded)}
       [navbar]
       [sidebar]])))
