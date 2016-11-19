(ns garm.system
  (:require [com.stuartsierra.component :as component]
            [hellhound.components.websocket :refer [websocket-server]]
            [hellhound.components.webserver :refer [webserver]]
            [system.core  :refer [defsystem]]
            [system.repl  :refer [set-init! start]]
            [garm.handler :refer [routes]]
            ))

(defn create-system
  "Create the system map."
  [routes]
  (-> (component/system-map)
      (websocket-server)
      (webserver routes)))

;; (defn -main [& args]
;;   (let [host     (first args)
;;         port-num (first (rest args))
;;         port     (read-string port-num)]

;;     (if-not (number? port)
;;       (println "Daah, Port number should be a NUMBER.")
;;       (do
;;         (-> (create-system host port)
;;             (component/start))))))

(defn dev-system
  []
  (-> (component/system-map)
      (websocket-server)
      (webserver routes)))



(defn -main [& args]
  (set-init! #'dev-system)
  (start))
