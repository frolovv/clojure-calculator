(ns tokenize.core-test
  (:require [clojure.test :refer :all]
            [tokenize.core :refer :all]))

(deftest eval-test
  (testing "basic eval cases"
    (is (= (my-eval "123") 123))
    (is (= (my-eval "(+ 1)") 1))
    (is (= (my-eval "(+ 1 2 3)") 6))
    )

  (testing "more complex expressions"
    (is (= (my-eval "(+ (+ 1 2 3) 0)") 6))
    )
  )
