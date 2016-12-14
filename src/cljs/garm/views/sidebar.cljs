(ns garm.views.sidebar
  (:require [re-frame.core :as re-frame]
            [reagent.core :refer [atom] :as r]
            [cljs-time.core :refer [now]]
            [cljs-time.coerce :refer [to-long]]
            [hellhound.i18n.core :refer [t]]
            [hellhound.frontend.helpers.jquery :refer [$]]
            [garm.views.grommet :refer [title button
                                        header footer box layer
                                        icon menu anchor] :as grommet]))


(defn sidebar-layer
  [sidebar-status]
  (when sidebar-status
    [layer {:flush true
            :closer false
            :onClose #(re-frame/dispatch [:toggle-sidebar])
            :align "right"}
     [sidebar]]))


(defn sidebar
  []
  (let [expanded         (re-frame/subscribe [:sidebar-expanded])
        sidebar-title    (re-frame/subscribe [:sidebar-title])
        counter 0]
    [grommet/sidebar {:colorIndex "neutral-3-a"
                      :className  ""
                      :fixed true
                      :size "small"}

     [header {:pad "medium"
              :justify "between"}
      [title
       "Watcher"]]

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
