(ns tokenize.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


(defn digit?
  [ch]
  (let [n (- (int ch) 48)]
    (and (>= n 0) (<= n 9))))

(defn get-digits
  [chars]
  (let [[digits rest] (split-with digit? chars)
        joined (clojure.string/join digits)
        num (read-string joined)
        ]
    (list rest (list :number num))))

(get-digits (seq "123 456"))

(defn tokenize1
  [chars tokens]
  (if (empty? chars) tokens
        (let [[ch & rest] chars]
          (cond (= ch \+) (tokenize1 rest (conj tokens (list :plus ch)))
                (= ch \-) (tokenize1 rest (conj tokens (list :minus ch)))
                (= ch \() (tokenize1 rest (conj tokens (list :lparen ch)))
                (= ch \)) (tokenize1 rest (conj tokens (list :rparen ch)))
                (digit? ch) (let [[rest' token] (get-digits chars)]
                              (tokenize1 rest' (conj tokens token)))
                :else (throw (Exception. (format "unknown char [%s]" ch)))
                )
    )))


(defn tokenize
  [str]
  (tokenize1 (seq str) (vector)))













