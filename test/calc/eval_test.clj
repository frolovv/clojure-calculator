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

(deftest high-nesting
  (letfn [(gen [n] (reduce #(format "(+ %s %s)" %2 %1) "0" (range n) ))]

    (testing "nested expressions"
      (is (= (my-eval (gen 5)) 10))
      (is (= (my-eval (gen 10)) 45))
      (is (= (my-eval (gen 100)) 4950))
      )))
