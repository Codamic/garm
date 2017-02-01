(ns garm.views.symbols-table
    (:require [re-frame.core      :as re-frame]
              [reagent.core       :as r]
              [hellhound.i18n.core  :refer [t]]
              [garm.views.grommet :refer [table
                                          table-row
                                          table-header]]))



(defn symbol-row
  "A symbol row in the symbol table"
  [symbol]
  (js/console.log "<<<<<<")
  (js/console.log symbol)
  [table-row
   [:td
    (:name symbol)]
   [:td]])

(defn symbols-table
  "The global symbols table"
  []
  (let [symbols (re-frame/subscribe [:symbols-table])]

    [table {:scrollable true
            :selectable true}
     ;;:onSort
     [table-header
      {:labels [(t :symbols-table/name)
                (t :symbols-table/price)]
       :sortIndex 0
       :sortAscending false}

      [:tbody
       (if (nil? @symbols)
         (do
           (js/console.log "zczczxcdafsdfsdfsdfsdf")
           [table-row
            [:td
             "asdasdasd"]
            [:td]])
          (doseq [symbol @symbols]
            [symbol-row symbol]))]]]))
