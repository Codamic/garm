(ns garm.parsers.tse
  (:import (java.io ByteArrayOutputStream))
  (:require [cheshire.core   :refer :all]
            [clj-http.client :as http]
            [hellhound.logger.core :as logger]
            [cognitect.transit :as t]))


(defn transite-write
  "asdasdasd"
  [x]
  (let [baos (ByteArrayOutputStream.)
        w    (t/writer baos :json)
        _    (t/write w x)
        ret  (.toString baos)]
    (.reset baos)
    ret))


(defn transit-writev
  "asdasdasdasd"
  [x]
  (let [baos (ByteArrayOutputStream.)
        wv   (t/writer baos :json-verbose)
        _    (t/write wv x)
        ret  (.toString baos)]
    (.reset baos)
    ret))


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
    (map->Symbol {:id     (get symbol-data "i")
                  :symbol (get details     "namad")
                  :name   (get details     "name")})))

(defn fetch-and-parse
  []
  (let [data             (parse-string (fetch-data))
        symbols-data     (get data "bData")
        datamap          (map make-symbol symbols-data)]
    datamap))

(defn parsed-data
  []
  (transite-write (fetch-and-parse)))
