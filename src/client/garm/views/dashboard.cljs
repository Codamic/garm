(ns garm.views.dashboard
  (:require [re-frame.core :as re-frame]
            [garm.views.navbar :refer [navbar]]))


(defn dashboard []
  (fn []
    [:div {:id "wrapper"}
     [navbar]]))
