(ns garm.views.sidebar
  (:require [re-frame.core :as re-frame]
            [reagent.core :refer [atom]]
            [hell-hound.frontend.helpers.jquery :refer [$]]))

;; TODO: Don't forget to add the slimscroll to the sidebar
(def menu-counter (atom 0))

(defn- open-submenu
  [submenu]
  (re-frame/dispatch [:jquery ".has_sub .submenu" :slideUp 400])
  (re-frame/dispatch [:jquery (str "#" submenu) :slideDown 400]))


(defn sidebar-entry
  "Represent menu entries of sidebar."
  [text icon-class children]
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])
        counter          (inc @menu-counter)
        id               (str "submenu-" counter)]
    (reset! menu-counter counter)
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
  [:li
   [:a {:href target}
    text]])

(defn sidebar
  "Sidebar component."
  []
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])]
    [:div {:class "left side-menu"}
     [:div {:class "sidebar-inner slimscrollleft"}
      [:div {:id "sidebar-menu"}
       [:ul
        [sidebar-entry "some text" "fa fa-home"
         [sidebar-item "text1" "#sam"]]]]]]))
