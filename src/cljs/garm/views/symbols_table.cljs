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
  (fn []
    [table-row {:id (:id symbol)}
     [:td (:symbol symbol)]
     [:td (:name   symbol)]
     [:td (:value  symbol)]
     [:td (:volum  symbol)]
     [:td (:total-trades   symbol)]
     [:td (:best-demand   symbol)]
     [:td (:best-supply   symbol)]
     ]))

(defn symbols-table
  "The global symbols table"
  []
  (let [symbols (re-frame/subscribe [:symbols-table])
        lang    (re-frame/subscribe [:lang])]

    [table {:scrollable true
            :selectable true
            :className  (if (= @lang :en) "ltr" "rtl")}
     ;;:onSort
     [table-header
      {:labels [(t :symbols-table/symbol)
                (t :symbols-table/name)
                (t :symbols-table/value)
                (t :symbols-table/volume)
                (t :symbols-table/total-trades)
                (t :symbols-table/best-demand)
                (t :symbols-table/best-supply)]
       :sortIndex 0
       :sortAscending false}]

     [:tbody
      (if (nil? @symbols)
        (do
          [table-row
           [:td {:colSpan 7}
            "TODO"]])
        (for [symbol @symbols]
          ^{:key (:id symbol)} [symbol-row symbol]))]]))
