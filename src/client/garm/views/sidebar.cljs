(ns garm.views.sidebar
  (:require [re-frame.core :as re-frame]
            [reagent.core :refer [atom]]
            [cljs-time.core :refer [now]]
            [cljs-time.coerce :refer [to-long]]
            [hell-hound.frontend.helpers.jquery :refer [$]]))

;; TODO: Don't forget to add the slimscroll to the sidebar

(defn- open-submenu
  [submenu]
  (re-frame/dispatch [:jquery (str ".has_sub .submenu:not(#" submenu ")")  :slideUp 400])
  ;(re-frame/dispatch [:jquery (str "#" submenu) :addClass "expanded"])
  (re-frame/dispatch [:jquery (str "#" submenu) :slideToggle 400]))


(defn sidebar-entry
  "Represent menu entries of sidebar."
  [text icon-class & children]
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])
        counter          (to-long (now))
        id               (str "submenu-" counter)]

    (js/console.log counter)
    [:li {:class "has_sub"}
     [:a {:href "javascript:void(0);" :class "wave-effect" :on-click #(open-submenu id)}
      [:i {:class icon-class}]
      [:span text]
      [:span {:class "menu-arrow"}]]
     [:ul {:class "submenu list-unstyled" :id id}
      children]]))

(defn sidebar-item
  "Represent the menu item at the far end position of sidebar."
  [text target]
  (fn []
    [:li
     [:a {:href target}
      text]]))

(defn sidebar
  "Sidebar component."
  []
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])]
    [:div {:class "left side-menu"}
     [:div {:class "sidebar-inner slimscrollleft"}
      [:div {:id "sidebar-menu"}
       [:ul
        [sidebar-entry "some text" "fa fa-home"
         [sidebar-item "text1" "#sam"]
         [sidebar-item "text1" "#sam"]
         [sidebar-item "text1" "#sam"]]
        [sidebar-entry "some text" "fa fa-book"
         [sidebar-item "text1" "#sam"]
         [sidebar-item "text1" "#sam"]
         [sidebar-item "text1" "#sam"]]]]]]))
