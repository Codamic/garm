(set-env!
 :source-paths    #{"src/cljs"
                    "src/clj"
                    "resources/assets"
                    }
 :checkouts '[;[cljsjs/grommet                "1.1.0-0"]
              [codamic/hellhound             "0.12.0-SNAPSHOT"]]

 :resource-paths  #{"resources/statics"}
 ;:asset-paths     #{}
 :dependencies '[[org.clojure/clojure           "1.9.0-alpha14"]
                 ;[org.clojure/clojure           "1.8.0"]
                 [org.clojure/clojurescript     "1.9.229"]
                 [codamic/hellhound             "0.12.0-SNAPSHOT"]
                 [yogthos/config                "0.8"]
                 [com.andrewmcveigh/cljs-time   "0.4.0"]
                 [codamic/garm-vendor           "0.1.0"]
                 [cljsjs/bootstrap              "3.3.6-1"]
                 [selmer                        "1.0.9"]
                 [cljsjs/grommet                "1.1.0-0"]
                 [cheshire                      "5.6.3"]
                 [clj-http                      "3.1.0"]

                 ;; HellHound
                 [adzerk/boot-cljs           "1.7.228-2" :scope "test"]
                 [deraen/boot-less           "0.6.0"     :scope "test"]
                 [deraen/boot-sass           "0.3.0"     :scope "test"]
                 [binaryage/devtools         "0.8.2"     :scope "test"]
                 [adzerk/boot-reload         "0.4.13"    :scope "test"]

                 ;; Cljs repl dependencies ----------------------------
                 [adzerk/boot-cljs-repl      "0.3.3"     :scope "test"]
                 [com.cemerick/piggieback    "0.2.1"     :scope "test"]
                 [weasel                     "0.7.0"     :scope "test"]
                 [org.clojure/tools.nrepl    "0.2.12"    :scope "test"]
                 ;; ---------------------------------------------------

                 [org.danielsz/system        "0.3.2-SNAPSHOT"]
])

(require
 '[adzerk.boot-cljs       :refer [cljs]]
 '[adzerk.boot-reload     :refer [reload]]
 '[adzerk.boot-cljs-repl  :refer [cljs-repl-env start-repl]]
 '[deraen.boot-less       :refer [less]]
 '[deraen.boot-sass       :refer [sass]]
 '[system.boot            :refer [system] :as s]
 '[garm.system            :refer [dev-system]]
 '[hellhound.boot.helpers :refer :all]
 '[hellhound.boot.repl    :refer [cider]]
 '[environ.boot           :refer [environ]])

(task-options!
  pom {:project      'codamic/garm
       :version      "0.1.0-SNAPSHOT"
       :description  "Stock exchange utility belt"
       :license      {:name "GPLv3" :url "https://www.gnu.org/licenses/gpl.html"}}

  jar {:main     'garm.system
       :manifest {"Description"  "Stock exchange utility belt"
                  "Url"          "http://github.com/Codamic/garm"}}

  cljs {:ids              #{"application"}
        :optimizations :none :source-map true}

  ;; system {:sys #'dev-system :auto false :files ["src/clj/handler.clj"
  ;;                                               "src/clj/system.clj"]}
  less {:source-map true}
  sass {:source-map true})


(deftask dev
  [l with-less          bool "Use LESS instead of SASS."
   p port        PORT   int  "Port to run the web server on."]
  (let [port_ (or port 4000)]
    (set-env! :source-paths #(conj % "src/js/dev"))
    (environ :env {:http-port (str port_)})

    (comp
     (cider)
     (watch)
     (if with-less
       (less)
       (sass))
     (system :sys #'dev-system :auto true :files ["handler.clj" "system.clj"])
     (cljs-repl-env :ws-host "localhost" :ids #{"application"})
     (cljs)
     (repl :server true)
     (target))))


(deftask prod
  "Setup the prod environment."
  []
  (environ :env {:http-port "4000"})
  identity)



;; (deftask run []
;;   (comp (serve)
;;         (watch)
;;         (cljs-repl)
;;         (reload)
;;         (build)))


(deftask testing []
  (set-env! :source-paths #(conj % "test/cljs"))
  identity)



;;; This prevents a name collision WARNING between the test task and
;;; clojure.core/test, a function that nobody really uses or cares
;;; about.
;; (ns-unmap 'boot.user 'test)

;; (deftask test []
;;   (comp (testing)
;;         (test-cljs :js-env :phantom
;;                    :exit?  true)))

;; (deftask auto-test []
;;   (comp (testing)
;;         (watch)
;;         (test-cljs :js-env :phantom)))
