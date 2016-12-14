(ns garm.views.dashboard
  (:require [re-frame.core      :as re-frame]
            [reagent.core       :as r]
            [garm.views.grommet :refer [app box split table table-row]]
            [garm.views.navbar  :refer [navbar]]
            [garm.views.sidebar :refer [sidebar sidebar-layer]]))

(defn index []
          [box {:pad "none"
              :colorIndex "light-1"}
         [table {
                 :scrollable true
                 :selectable true}
          [:thead
           [:tr
            [:th
             "Name"]

            [:th
             "Note"]]]

          [:tbody
           [table-row
            [:td
             "Alan"]

            [:td
             "plays accordion"]]]]])

(defn dashboard [children]
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])
        lang             (re-frame/subscribe [:lang])]


    [app {:lang @lang
          :className  (if (= @lang :en) "ltr" "rtl")
          :centered false}

     [sidebar-layer lang @sidebar-expanded]

     [navbar @lang]

     [box { :pad "medium"}
      children]]))
