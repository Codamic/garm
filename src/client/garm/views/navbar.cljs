(ns garm.views.navbar
  (:require [garm.views.logo :refer [logo]]))

(defn navbar []
  [:div {:class "topbar"}
   [:div {:class "topbar-left"}
    [logo]]
   [:div {:class "navbar navbar-default" :role "navigation"}
    [:div {:class "container"}
     [:div
      [:div {:class "pull-left"}
      [:button {:class "button-menu-mobile open-left waves-effect waves-light"}
       [:i {:class "md md-menu"}]]
       [:span {:class "clearfix"}]]
      ]]]
   ])
