(set-env!
 :resource-paths #{"src"}
 :dependencies '[[org.clojure/clojure "1.9.0-alpha12"]
                 [org.clojure/clojurescript "1.9.229"]
                 [codamic/hellhound "0.7.0-SNAPSHOT"]
                 [yogthos/config "0.8"]
                 [com.andrewmcveigh/cljs-time "0.4.0"]
                 [codamic/garm-vendor "0.1.0"]
                 [cljsjs/bootstrap "3.3.6-1"]
                 [selmer "1.0.9"]])

(task-options!
  pom {:project 'codamic/garm
       :version "0.1.0-SNAPSHOT"}
  jar {:manifest {"Description"
                  "Url" "http://github.com/Codamic/garm"}})

(deftask build
  "Build and install the hellhound"
  []
  (set-env! :dependencies '[[binaryage/devtools "0.8.2"]])
  (comp (pom) (jar) (install)))
