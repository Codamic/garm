(defproject garm "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0-alpha12"]
                 [org.clojure/clojurescript "1.9.229"]
                 [reagent "0.6.0"]
                 [binaryage/devtools "0.8.2"]
                 [re-frame "0.8.0"]
                 [secretary "1.2.3"]
                 [compojure "1.5.0"]
                 [http-kit "2.1.18"]
                 [yogthos/config "0.8"]
                 [ring "1.4.0"]
                 [hell-hound "0.1.0-SNAPSHOT"]
                 [com.stuartsierra/component "0.3.1"]
                 [selmer "1.0.9"]]

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-less "1.7.6-SNAPSHOT"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/server" "../lein-less/src/" "../hell-hound/src/"]

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
     :source-paths ["src/client"]
     :figwheel     {:on-jsload "garm.core/mount-root"}
     :compiler     {:main                 garm.core
                    :output-to            "resources/public/js/compiled/app.js"
                    ;; :libs ["/js/jquery.min.js"
                    ;;        "/js/bootstrap.min.js"
                    ;;        "js/detect.js"
                    ;;        "js/fastclick.js"
                    ;;        "js/jquery.slimscroll.js"
                    ;;        "js/jquery.blockUI.js"
                    ;;        "js/waves.js"
                    ;;        "js/wow.min.js"
                    ;;        "js/jquery.nicescroll.js"
                    ;;        "js/jquery.scrollTo.min.js"
                    ;;        "js/plugins/peity/jquery.peity.min.js"
                    ;;        "js/plugins/waypoints/lib/jquery.waypoints.js"
                    ;;        "js/plugins/counterup/jquery.counterup.min.js"
                    ;;        "js/plugins/morris/morris.min.js"
                    ;;        "js/plugins/raphael/raphael-min.js"
                    ;;        "js/plugins/jquery-knob/jquery.knob.js"
                    ;;        "/js/pages/jquery.dashboard.js"
                    ;;        "/js/jquery.core.js"
                    ;;        "/js/jquery.app.js"]

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
