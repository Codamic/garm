(ns garm.views.dashboard
  (:require [re-frame.core      :as re-frame]
            [garm.views.grommet :refer [app box split table table-row]]
            [garm.views.navbar  :refer [navbar]]
            [garm.views.sidebar :refer [sidebar]]))

(defn- wrapper-classes
  [sidebar-status]
  (if (true? sidebar-status) "enlarged forced" "expanded forced"))

(defn dashboard []
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])]
    (fn []
      [app {:lang "fa" :class  "rtl" :centered false}

       [split {:priority "right"
               :flex "right"
               :fixed false}
        [box {:justify "center"
              :align "center"
              :pad "none"}]
        [box {:pad "none"}
         ;[nav-bar {:toggle "this.toggleDrawer"}]
         [:br]

         [app {:lang "fa" :class "rtl" :centered true}

          [box {:colorIndex "light-1"}]

          [table {:scrollable true
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
              "plays accordion"]]

            [table-row
             [:td
              "Tracy"]

             [:td
              "travels the world"]]

            [table-row
             [:td
              "Chris"]

             [:td
              "drops the mic"]]]]]]]])))
       ;; [:div {:id "wrapper" :class (wrapper-classes  @sidebar-expanded)}
       ;;  [navbar]
       ;;  [sidebar]]]))
