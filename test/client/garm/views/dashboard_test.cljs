(ns garm.views.dashboard-test
  (:require [garm.views.dashboard :as sut]
            [cljs.test :refer-macros [deftest testing is]]))


(defn subscription-stub [x]
  (atom
    (case x
      [:query-id] 42)))

(deftest some-test
  (with-redefs [re-frame/subscribe (subscription-stub)]
    (testing "some rendering"
      )))

(deftest dashboard
  (testing "sidebar expandation class"
    ))
