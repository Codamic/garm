(set-env!
 :source-paths    #{ "src/client"}
 :resource-paths  #{"src/server"  "../hellhound/src/" }
 :asset-paths     #{"resources/"}
 :dependencies '[[org.clojure/clojure           "1.9.0-alpha12"]
                 [org.clojure/clojurescript     "1.9.229"]
                 [codamic/hellhound             "0.7.0-SNAPSHOT"]
                 [yogthos/config                "0.8"]
                 [com.andrewmcveigh/cljs-time   "0.4.0"]
                 [codamic/garm-vendor           "0.1.0"]
                 [cljsjs/bootstrap              "3.3.6-1"]
                 [selmer                        "1.0.9"]

                 ;; HellHound
                 [adzerk/boot-cljs           "1.7.228-2" :scope "test"]
                 [binaryage/devtools "0.8.2"]

])

(require
 '[adzerk.boot-cljs      :refer [cljs]])

(task-options!
  pom {:project      'codamic/garm
       :version      "0.1.0-SNAPSHOT"
       :description  "Stock exchange utility belt"
       :license      {:name "GPLv3" :url "https://www.gnu.org/licenses/gpl.html"}}

  jar {:main     'garm.system
       :manifest {"Description"  "Stock exchange utility belt"
                  "Url"          "http://github.com/Codamic/garm"}}
  cljs {:ids              #{"ids/dev/app"}
        :source-map       true}
  ;less {:source-map true}
  ;sass {:source-map true}
  )

(deftask bb
  "asd"
  []
  (comp (speak)))

(deftask build-frontend
  "Build the clojurescript application."
  []
  (comp (speak)
        (cljs)
        ;(sass)
        ;(less)

        ))

(deftask move
  ""
  []
  (comp
   (sift   :to-asset #{#"ids/dev/"})
   (target :dir #{"resources/js/"})))

(deftask build-backend
  "Build and install the hellhound"
  []
  (comp (pom) (jar) (install)))


;; (deftask run []
;;   (comp (serve)
;;         (watch)
;;         (cljs-repl)
;;         (reload)
;;         (build)))

(deftask production []
  (task-options! cljs   {:optimizations :advanced}
                 ;sass   {:output-style :compressed}
                 ;less   {:compression true}
                 )
  identity)

(deftask development []
  (task-options! cljs   {:optimizations :none :source-map true}
                 ;reload {:on-jsload 'sd.app/init}
                 ;less   {:source-map  true}
                 )
  identity)


;; (deftask dev
;;   "Simple alias to run application in development mode"
;;   []
;;   (comp (development)
;;         (run)))


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
