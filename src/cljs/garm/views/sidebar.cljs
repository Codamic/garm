(ns garm.views.sidebar
  (:require [re-frame.core :as re-frame]
            [reagent.core :refer [atom] :as r]
            [cljs-time.core :refer [now]]
            [cljs-time.coerce :refer [to-long]]
            [hellhound.i18n.core :refer [t]]
            [hellhound.frontend.helpers.jquery :refer [$]]
            [garm.views.grommet :refer [title button
                                        header footer box
                                        icon menu anchor] :as grommet]))

;; TODO: Don't forget to add the slimscroll to the sidebar

(defn- open-submenu
  [submenu]
  (re-frame/dispatch [:jquery (str ".expanded .has_sub .submenu:not(#" submenu ")")  :slideUp 400])
  (re-frame/dispatch [:jquery (str ".expanded #" submenu) :slideToggle 400]))


;; (defn sidebar-entry
;;   "Represent menu entries of sidebar."
;;   [text icon-class & children]
;;   (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])
;;         counter          (to-long (now))
;;         id               (str "submenu-" counter)]

;;     [:li {:class "has_sub"}
;;      [:a {:href "javascript:void(0);" :class "wave-effect" :on-click #(open-submenu id)}
;;       [:i {:class icon-class}]
;;       [:span text]
;;       [:span {:class "menu-arrow"}]]
;;      [:ul {:class "submenu list-unstyled" :id id}
;;       children]]))

;; (defn sidebar-item
;;   "Represent the menu item at the far end position of sidebar."
;;   [text & {:keys [href icon]}]
;;   (fn []
;;     [:li.has_sub
;;      (if (not (nil? icon))
;;        [:a.waves-effect {:href href}
;;         [:i {:class icon}]
;;         [:span text]]
;;        [:a.waves-effect {:href href}
;;         [:span text]])]))


;; (defn sidebar
;;   "Sidebar component."
;;   []
;;   (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])
;;         sidebar-title    (re-frame/subscribe [:sidebar-title])
;;         counter 0]
;;     [:div {:class "left side-menu"}
;;      [:div {:class "sidebar-inner slimscrollleft"}
;;       [:div {:id "sidebar-menu"}
;;        [:ul


;;         [:li {:class "text-muted menu-title"} (t [@sidebar-title])]

;;         [sidebar-item (t [:dashboard]) :href "#asd" :icon "ti-home"]
;;         [sidebar-entry "some text" "fa fa-book"
;;          ^{:key (+ 4 counter)} [sidebar-item "text1" :href "#"]
;;          ^{:key (+ 5 counter)} [sidebar-item "text1" :href "#"]
;;          ^{:key (+ 6 counter)} [sidebar-item "text1" :href "#"]]]]]]))

(defn sidebar
  []
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])
        sidebar-title    (re-frame/subscribe [:sidebar-title])
        counter 0]
    [grommet/sidebar {:colorIndex "neutral-3-a"
                      :className "hidden"
                      :fixed true
                      :size "small"}

     [header {:pad "medium"
              :justify "between"}
      [title
       "watcher"]]

     [box {:flex    "grow"
           :justify "start"}

      [menu {:primary true}
       [anchor {:href "#"
                :class "active"}
        "home page"]
       [anchor {:href "#"}
        "notifiers"]
       [anchor {:href "#"}
        "about us"]]]
     [footer {:pad "medium"}
      [button {:icon  (icon "User")}]]]))
;(.render (new js/Grommet.Icons.Base.User (clj->js {}) js/window))
