(ns garm.views.navbar
  (:require [re-frame.core :as re-frame]
            [hell-hound.i18n.core :refer [t]]
            [garm.views.logo :refer [logo]]))

(defn gravatar
  [email]
  "http://gravatar.com/avatar/767fc9c115a1b989744c755db47feb60")


(defn search-bar
  []
  (fn []
    [:form.navbar-left.app-search.pull-left.hidden-xs {:role "search"}
     [:input.form-controll {:type "text" :placeholder (t [:search])}]
     [:a {:href ""}
      [:i.fa.fa-search]]]))

(defn profile-button
  []
  [:li.dropdown.top-menu-item-xs
   [:a.dropdown-toggle.profile.waves-effect.waves-light
    {:data-toggle "dropdown" :aria-expanded true :href ""}
    [:img.img-circle {:src (gravatar "email") :alt "user-img"}]]
   [:ul.dropdown-menu ]
   [:li
    [:a {:href "#"}
     [:i.ti-user.m-r-10.text-custom]
     (t [:profile])]]

   [:li
    [:a {:href "#"}
     [:i.ti-setting.m-r-10.text-custom]
     (t [:setting])]]

   [:li
    [:a {:href "#"}
     [:i.ti-lock.m-r-10.text-custom]
     (t [:lock])]]

   [:li
    [:a {:href "#"}
     [:i.ti-power.m-r-10.text-custom]
     (t [:logout])]]])


(defn navbar-menu-item
  [name target]
  [:li
   [:a.waves-effect.waves-light {:href target}
    (t [name])]])

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
       [navbar-menu-item :navbar/home "/"]
       [navbar-menu-item :navbar/blog "/blog"]]
      ;[search-bar]

      [:ul.nav.navbar-nav.navbar-right.pull-right
       [profile-button]
       ]]]]])
