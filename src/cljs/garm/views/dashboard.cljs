(ns garm.views.dashboard
  (:require [re-frame.core      :as re-frame]
            [garm.views.grommet :refer [app box split table table-row]]
            [garm.views.navbar  :refer [navbar]]
            [garm.views.sidebar :refer [sidebar sidebar-layer]]))

(defn dashboard []
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])
        lang             (re-frame/subscribe [:lang])]

    (fn []
      [app {:lang @lang
            :className  (if (= @lang :en) "ltr" "rtl")
            :centered false}

       [sidebar-layer lang @sidebar-expanded]

       [navbar @lang]

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
             "plays accordion"]]]]]]])))

(defn old-dashboard []
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])
        lang             (re-frame/subscribe [:lang])]

    (fn []
      [app {:lang @lang
            :className  (if (= @lang :en) "ltr" "rtl")
            :centered false}

       [split {:priority (if (= @lang :en) "left" "right")
               :flex     (if (= @lang :en) "left" "right")
               :fixed    false}

        [box {:justify   "center"
              :align     "center"
              :pad       "none"}

         [sidebar-layer lang @sidebar-expanded]]

        [box  {:pad "none"}
         [navbar @lang]

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
