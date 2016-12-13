(ns garm.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [devtools.core :as devtools]
              [garm.handlers]
              [garm.subs]
              [garm.vendor.js.wow]
              [garm.vendor.js.waves]
              [cljsjs.bootstrap]
              [cljsjs.jquery]
              [cljsjs.react]
              [cljsjs.react.dom]

              [cljsjs.grommet]
              [garm.routes :as routes]
              [garm.views :as views]
              [garm.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")
    (devtools/install!)))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root)
  (.init (new js/WOW {:boxClass "wow"
                      :animateClass "animated"
                      :offset 50
                      :mobile false})))
