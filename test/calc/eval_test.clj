(ns calc.eval-test
  (:require [clojure.test :refer :all]
            [calc.tokenize :refer :all]
            [calc.parse :refer :all]
            [calc.eval :refer :all]
            ))

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
(deftest bad-expressions
  (testing "bad expressions"
    (is (thrown? Exception (my-eval "+++")))
    )
  )