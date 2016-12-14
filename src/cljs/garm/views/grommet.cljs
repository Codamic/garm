(ns garm.views.grommet
  (:require [reagent.core :as r]
            [cljsjs.react]
            [cljsjs.react.dom]
            [cljsjs.grommet]))


(def app   (r/adapt-react-class (.-App   js/Grommet)))
(def box   (r/adapt-react-class (.-Box   js/Grommet)))
(def split (r/adapt-react-class (.-Split js/Grommet)))
(def table (r/adapt-react-class (.-Table js/Grommet)))

(def table-row (r/adapt-react-class (.-TableRow js/Grommet)))
