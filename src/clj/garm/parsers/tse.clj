(ns garm.parsers.tse
  (:import (java.io ByteArrayOutputStream))
  (:require [cheshire.core   :refer :all]
            [clj-http.client :as http]
            [hellhound.logger.core :as logger]
            [cognitect.transit :as t]))


(def site-url {:fa {:index-json "http://new.tse.ir/json/MarketWatch/data_1.json"}})


(defrecord Symbol [id symbol name])

(defn fetch-data
  []
  (logger/info "Fetching data.")
  (:body (http/get (:index-json (:fa site-url)))))

(defn parse-details [data]
  (let [default-map {}]
    (reduce #(assoc %1
                    (get %2 "t")
                    (get %2 "v")) {} data)))

(defn make-symbol
  [symbol-data]
  (let [details (parse-details (get symbol-data "val"))]
    {:id              (get symbol-data "i")
     :symbol          (get details     "namad")
     :name            (get details     "name")
     :volum           (get details     "hajm")
     :value           (get details     "arzesh")
     :total_trades    (get details "dm")
     :max_price       (get details "bish")
     :min_price       (get details "kam")
     :previous_day    (get details "rgh")
     :effect_on_index (get details "tdsh")
     :pe              (get details "pe")
     :eps             (get details "eps")
     :market_value    (get details "ab")
     ;; TODO: Add the total demand and total supply too
     :best-demand     (get details "bt")
     :best-supply     (get details "ba")
     :final_price     {:price      (get details "pghey")
                       :tolerance  (get details "ptagh")
                       :percentage (get details "pdar")}
     :starting_price  {:price      (get details "aghey")
                       :tolerance  (get details "atagh")
                       :percentage (get details "adar")}
}))

(defn fetch-and-parse
  []
  (let [data             (parse-string (fetch-data))
        symbols-data     (get data "bData")
        datamap          (map make-symbol symbols-data)]
    datamap))

(defn parsed-data
  []
  (fetch-and-parse))
