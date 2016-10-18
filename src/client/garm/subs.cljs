(ns garm.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 :active-panel
 (fn [db _]
   (:active-panel db)))


(re-frame/reg-sub
 :sidebar-expanded
 (fn [db _]
   (:sidebar-expanded db)))

(re-frame/reg-sub
 :sidebar-title
 (fn [db _]
   (:sidebar-title db)))


(re-frame/reg-sub
 :lang
 (fn [db _]
   (:lang db)))
