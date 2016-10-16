(ns garm.views.dashboard
  (:require [re-frame.core :as re-frame]
            [garm.views.navbar :refer [navbar]]
            [garm.views.sidebar :refer [sidebar]]))


(defn dashboard []
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])]
    (fn []
      [:div {:id "wrapper" :class (if (true? @sidebar-expanded) "enlarged forced" "forced")}
       [navbar]
       [sidebar]])))
