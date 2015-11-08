(ns calc.parse-test
  (:require [clojure.test :refer :all]
            [calc.parse :refer :all]))

(deftest parse-test
  (testing "parsing numbers"
    (is (= (parse "123") [[:number 123]]))
    (is (= (parse "123 456") [[:number 123] [:number 456]]))

    )

  (testing "parsing expressions"
    (is (= (parse "(123 4 5 6)") [(list [:number 123] [:number 4] [:number 5] [:number 6])]))
    (is (thrown? Exception (parse "()")))
    )
  )

(deftest parse-unparse-test
  (let [parse-unparse (fn [str] (= (unparse (parse str)) str))]

    (testing "using unparse function"
      (is (parse-unparse "123"))
      (is (parse-unparse "123 456"))
      (is (parse-unparse "(123)"))
      (is (parse-unparse "(+ 1 2 3)"))

      )))