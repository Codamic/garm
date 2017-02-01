(ns garm.views.dashboard
  (:require [re-frame.core      :as re-frame]
            [reagent.core       :as r]
            [garm.views.grommet :refer [app box split table table-row]]
            [garm.views.symbols-table :refer [symbols-table]]
            [garm.views.navbar  :refer [navbar]]
            [garm.views.sidebar :refer [sidebar sidebar-layer]]))

(defn index []
          [box {:pad "none"
                :colorIndex "light-1"}
           [symbols-table]])

(defn dashboard
  "Main component for dashboard page. Any children of dashboard
  should be wrap by this component"
  [children]
  (let [sidebar-expanded (re-frame/subscribe [:sidebar-expanded])
        lang             (re-frame/subscribe [:lang])]


    [app {:lang @lang
          :className  (if (= @lang :en) "ltr" "rtl")
          :centered false}

     [sidebar-layer lang @sidebar-expanded]

     [navbar @lang]

     [box { :pad "medium"}
      children]]))
