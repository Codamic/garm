(ns garm.views.dashboard
  (:require [re-frame.core      :as re-frame]
            [garm.views.grommet :refer [app box split table table-row]]
            [garm.views.navbar  :refer [navbar]]
            [garm.views.sidebar :refer [sidebar sidebar-layer]]))

(defn- wrapper-classes
  [sidebar-status]
  (if (true? sidebar-status) "enlarged forced" "expanded forced"))

(defn dashboard []
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])]
    (fn []
      [app {:lang "fa" :className  "rtl" :centered false}

       [split {:priority "right"
               :flex "right"
               :fixed false}
        [box {:justify "center"
              :align "center"
              :pad "none"}

         [sidebar-layer @sidebar-expanded]]

        [box {:pad "none"}
         [navbar {:toggle "this.toggleDrawer"}]


         [box { :pad "medium"}

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
               "plays accordion"]]]]]]]]])))
       ;; [:div {:id "wrapper" :class (wrapper-classes  @sidebar-expanded)}
       ;;  [navbar]
       ;;  [sidebar]]]))
