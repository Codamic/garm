(ns garm.views.sidebar
    (:require [re-frame.core :as re-frame]))

;; TODO: Don't forget to add the slimscroll to the sidebar

(defn sidebar-entry
  "Represent menu entries of sidebar."
  [text icon-class children]
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])]
    [:li {:class "has_sub"}
     [:a {:href "javascript:void(0);" :class "wave-effect"}
      [:i {:class icon-class}]
      [:span text]
      [:span {:class "menu-arrow"}]]
     [:ul {:class "list-unstyled"}
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
