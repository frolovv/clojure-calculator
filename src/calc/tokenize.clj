(ns calc.tokenize)
(use '[clojure.string :only (join split)])

(declare tokenize)

(defn digit?
  [ch]
  (let [n (- (int ch) 48)]
    (and (>= n 0) (<= n 9))))

(defn whitespace?
  [ch]
  (let [n (int ch)]
    (and (>= n 0) (<= n 32))))

(defn get-digits
  [chars]
  (let [[digits rest] (split-with digit? chars)
        joined (clojure.string/join digits)
        num (read-string joined)
        ]
    (list rest (list :number num))))

(defn tokenize1
  [chars tokens]
  (if (empty? chars)
    tokens
    (let [[ch & rest] chars]
      (cond (= ch \+) (tokenize1 rest (conj tokens (list :plus ch)))
            (= ch \-) (tokenize1 rest (conj tokens (list :minus ch)))
            (= ch \() (tokenize1 rest (conj tokens (list :lparen ch)))
            (= ch \)) (tokenize1 rest (conj tokens (list :rparen ch)))
            (whitespace? ch) (tokenize1 rest tokens)
            (digit? ch) (let [[rest' token] (get-digits chars)]
                          (tokenize1 rest' (conj tokens token)))
            :else (throw (Exception. (format "unknown char [%s]" ch)))
            )
      )))


(defn tokenize
  [str]
  (tokenize1 (seq str) (vector)))
