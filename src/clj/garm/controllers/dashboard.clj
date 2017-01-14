(ns garm.controllers.dashboard
  (:require [garm.state        :refer [lang]]
            [hellhound.system  :refer [get-system]]
            [selmer.parser     :as parser]))


(defn dashboard
  "Dashboard page controller."
  [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (parser/render-file "views/dashboard/index.html" {:lang @lang
                                                           :dir (if (= @lang :en) "ltr" "rtl")})
   })

(defn test-handler
  "A test controller."
  [request]
  (let [chsk-send! (:chsk-send! (:websocket (get-system)))]
    (chsk-send! :sente/all-users-without-uid [:garm/event1 {:name "sameer"}])
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body "OK"}))
