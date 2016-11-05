(ns garm.system
  (:require [com.stuartsierra.component :as component]
            [hellhound.components.websocket :refer [make-websocket]]
            [garm.server :refer [make-webserver]]
            [system.core :refer [defsystem]]
            [system.repl :refer [set-init! start]]
            ))

(defn create-system
  "Create the system map."
  [host port]
  (component/system-map
   :websocket (make-websocket)
   :web (make-webserver host port)))

;; (defn -main [& args]
;;   (let [host     (first args)
;;         port-num (first (rest args))
;;         port     (read-string port-num)]

;;     (if-not (number? port)
;;       (println "Daah, Port number should be a NUMBER.")
;;       (do
;;         (-> (create-system host port)
;;             (component/start))))))

(defsystem dev-system
  [:websocket (make-websocket)
   :web       (make-webserver "localhost" 4000)])



(defn -main [& args]
  (set-init! #'dev-system)
  (start))
