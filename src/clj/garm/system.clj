(ns garm.system
  (:require [com.stuartsierra.component :as component]
            [hellhound.components.websocket :refer [websocket-server]]
            [hellhound.components.webserver :refer [webserver]]
            [hellhound.system  :refer [defsystem]]
            [system.repl  :refer [set-init! start]]
            [garm.handler :refer [dev-handler]]))


(defsystem dev-system
  (websocket-server)
  (webserver dev-handler))

(defn -main [& args]
  (set-init! #'dev-system)
  (start))
