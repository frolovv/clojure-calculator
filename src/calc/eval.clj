(ns calc.eval
  (:require [calc.tokenize :refer :all]
            [calc.parse :refer :all]
            ))

(declare eval1)
(declare my-eval)

(def operations {:plus +, :minus -})

(defn eval1
  [expr]
  (cond (vector? expr) (let [[kind value] expr]
                         (if (= kind :number)
                           value
                           (throw (Exception. (format "invalid expression %s" expr)))))
        (list? expr) (let [[[operator _] & args] expr
                           numbers (map eval1 args)]
                       (reduce (operator operations) 0 numbers))))

(defn my-eval
  [str]
  (let [exprs (parse str)
        results (map eval1 exprs)]
    (if (= (count results) 1)
      (first results)
      results)))