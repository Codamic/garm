(ns garm.views.navbar
  (:require [re-frame.core        :as re-frame]
            [reagent.core         :as r]
            [hellhound.i18n.core  :refer [t]]
            [garm.views.logo      :refer [logo]]
            [garm.views.grommet   :refer [title button header footer box icon
                                          menu anchor] :as grommet]))

(defn gravatar
  [email]
  "http://gravatar.com/avatar/767fc9c115a1b989744c755db47feb60")

(defn navbar []
  [header {:colorIndex "neutral-3-a"
           :full       "horizontal"
           :alignSelf  "stretch"
           :flex       true
           :className  "rtl"}

   [button {:icon     (icon "Menu")
            :on-click (fn [_] (re-frame/dispatch [:toggle-sidebar]))
            :plain    true}]

   [title "watcher"]

   [box {:flex       true
         :justify    "end"
         :direction  "row"
         :responsive false}]])
