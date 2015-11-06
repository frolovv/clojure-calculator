(ns tokenize.core-test
  (:require [clojure.test :refer :all]
            [tokenize.core :refer :all]))

(deftest parse-test
  (testing "parsing numbers"
    (is (= (parse "123") [[:number 123]]))
    (is (= (parse "123 456") [[:number 123] [:number 456]]))

    )

  (testing "parsing expressions"
    (is (= (parse "(123 4 5 6)") [(list [:number 123] [:number 4] [:number 5] [:number 6])]))
    )
  )

(run-tests)
