(ns garm.views.navbar
  (:require [re-frame.core :as re-frame]
            [hell-hound.i18n.core :refer [t]]
            [garm.views.logo :refer [logo]]))



(defn navbar []
  [:div {:class "topbar"}
   [:div {:class "topbar-left"}
    [logo]]
   [:div {:class "navbar navbar-default" :role "navigation"}
    [:div {:class "container"}
     [:div
      [:div {:class "pull-left"}
      [:button {:class "button-menu-mobile open-left waves-effect waves-light" :on-click #(re-frame/dispatch [:toggle-sidebar])}
       [:i {:class "md md-menu"}]]
       [:span {:class "clearfix"}]]


      [:ul {:class "nav navbar-nav hiiden-xs"}

       [:li
        [:a.waves-effect.waves-light {:href "#"}
         (t [:home])]]
       ]
      ]]]
   ])
