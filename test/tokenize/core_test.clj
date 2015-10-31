(ns tokenize.core-test
  (:require [clojure.test :refer :all]
            [tokenize.core :refer :all]))

(deftest digit?-test
  (testing "is digit? test"
    (is (= true (digit? \1)))
    (is (= true (digit? \0)))
    (is (= true (digit? \9)))
    (is (= false (digit? \a)))))

(deftest get-digits-test
  (testing "getting chars from char list"
    (is (= (vector (list \space \4 \5 \6) '(:number 123)) (get-digits (seq "123 456"))))
    (is (= (vector '() '(:number 123)) (get-digits (seq "123"))))

    ))


(deftest tokenize-test
  (testing "tokenizing single-token words"
    (is (= (vector '(:plus \+)) (tokenize "+")))
    (is (= (vector '(:minus \-)) (tokenize "-")))
    (is (= (vector '(:lparen \()) (tokenize "(")))
    (is (= (vector '(:rparen \))) (tokenize ")")))
    (is (= (vector '(:number 123)) (tokenize "123"))))

  (testing "tokenizing multi-token words"
    (is (= (vector '(:lparen \() '(:plus \+) '(:number 1) '(:rparen \))) (tokenize "(+ 1)")))

  ))

(run-tests)
