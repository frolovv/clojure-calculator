(ns tokenize.core-test
  (:require [clojure.test :refer :all]
            [tokenize.core :refer :all]))

(deftest eval-test
  (testing "basic eval cases"
    (is (= (eval "123") 123))
    (is (= (eval "(+ 1)") 1))
    (is (= (eval "(+ 1 2 3)") 6))
    ))
