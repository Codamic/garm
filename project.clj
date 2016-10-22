(defproject codamic/garm "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0-alpha12"]
                 [org.clojure/clojurescript "1.9.229"]
                 [codamic/hell-hound "0.6.0-SNAPSHOT"]
                 [yogthos/config "0.8"]
                 [com.andrewmcveigh/cljs-time "0.4.0"]
                 [codamic/garm-vendor "0.1.0"]
                 [cljsjs/bootstrap "3.3.6-1"]
                 [selmer "1.0.9"]]

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-less "1.7.6-SNAPSHOT"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/server" "../lein-less/src/" "../hell-hound/src/" "../src/garm-vendor/src/"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "test/js"]

  :figwheel {:css-dirs ["resources/public/css"]
             :ring-handler garm.handler/dev-handler
             :http-server-root "public"
             :server-port 3000}

  :less {:source-paths ["resources/less"]
         :target-files ["resources/less/bootstrap/bootstrap.less" "resources/less/dashboard/style.less"]
         :target-path  "resources/public/css"}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :profiles
  {:dev
   {:source-paths ["dev"]
    :dependencies [[figwheel-sidecar "0.5.7"]
                   [com.cemerick/piggieback "0.2.1"]]

    :plugins      [[lein-figwheel "0.5.7"]
                   [lein-doo "0.1.7"]
                   [cider/cider-nrepl "0.14.0-SNAPSHOT"]]
    }}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/client" "../hell-hound/src/"]
     :figwheel     {:on-jsload "garm.core/mount-root"}
     :compiler     {:main                 garm.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true}}

    {:id           "min"
     :source-paths ["src/client"]
     :jar true
     :compiler     {:main            garm.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    {:id           "test"
     :source-paths ["src/client" "test/client"]
     :compiler     {:output-to   "resources/public/js/compiled/test.js"
                    :output-dir  "resources/public/js/compiled/test/out"
                    :main          garm.runner
                    :optimizations :none}}
    ]}

  :main ^:skip-aot garm.system

  ;:aot [garm.system]

  :uberjar-name "garm.jar"

  :prep-tasks [["cljsbuild" "once" "min"]["less" "once"] "compile"])
