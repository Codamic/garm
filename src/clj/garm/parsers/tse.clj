(ns garm.parsers.tse
  (:import (java.io ByteArrayOutputStream))
  (:require [cheshire.core   :refer :all]
            [clj-http.client :as http]
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
  (:body (http/get (:index-json (:fa site-url)))))

(defn parse-details [data]
  (let [default-map {}]
    (reduce #(assoc %1
                    (get %2 "t")
                    (get %2 "v")) {} data)))

(defn make-symbol [symbol-data]
  (let [details (parse-details (get symbol-data "val"))]
    (map->Symbol {:id     (get symbol-data "i")
                  :symbol (get details     "namad")
                  :name   (get details     "name")})))

(defn parse []
  (let [data             (parse-string (fetch-data))
        symbols-data     (get data "bData")
        datamap          (map make-symbol symbols-data)]
    (println datamap)
    (transite-write datamap)))


;; export function tse_parse(data) {
;;   var parsed_json = JSON.parse(data);
;;   var allSymbols  = new yomi.Symbols();
;;   var symbols     = [];

;;   for(var i = 0; i < parsed_json.bData.length; i++) {
;;     let symbol = new yomi.Symbol();
;;     let obj    = parsed_json.bData[i];

;;     symbol.setId(obj.i);

;;     for(var j = 0; j < obj.val.length; j++) {
;;       let key_pair = obj.val[j];

;;       if (key_pair.t == "namad") {
;;         symbol.setName(key_pair.v);
;;       }

;;       if (key_pair.t == "name") {
;;         symbol.setTitle(key_pair.v);
;;       }
;;     }

;;     symbols.push(symbol);
;;   }

;;   allSymbols.setSymbolsList(symbols);
;;   return allSymbols;
;; }
