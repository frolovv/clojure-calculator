(ns tokenize.core)


(defn parse1
  [tokens succ fail]
  (if (empty? tokens)
    (succ (list) (list))
    (let [[token & rest] tokens
          [kind value] token]
      (cond (kind (set [:number :plus :minus])) (succ [kind value] rest)
            (= kind :lparen) (parse-many rest
                                         (fn [exprs even-more-tokens]
                                           (let [[[last-type _] & tail] even-more-tokens]
                                             (if (= last-type :rparen)
                                               (succ exprs tail)
                                               (fail))))
                                         fail)
            :else (fail))

      )
    ))


(defn parse-many
  [tokens succ fail]
  (if (empty? tokens)
    (succ (list) (list))
    (parse1 tokens
            (fn [expr more-tokens]
              (parse-many more-tokens
                          (fn [exprs even-more-tokens] (succ (conj exprs expr) even-more-tokens))
                          (fn [] (succ (list expr) more-tokens))))
            fail)))

(defn parse
  [str]
  (parse-many (tokenize (seq str))
              (fn [exprs tokens] (if (empty? tokens) exprs (throw (Exception. (format "got extra tokens %s" tokens)))))
              (fn [] 123)))
