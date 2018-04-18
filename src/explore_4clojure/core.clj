;; gorilla-repl.fileformat = 1

;; **
;;; # Learn Clojure with 4Closure!
;;; 
;;; Solve 4clojure problems and have solutions/explanations organized in pretty HTML
;; **

;; **
;;; ## Reference Materials
;;; These examples have been helpful for me to reference while solving more complex problems
;; **

;; @@
;; Intro to Functions
(= 8 ((fn add-five [x] (+ x 5)) 3))
(= 8 ((fn [x] (+ x 5)) 3))
(= 8 (#(+ % 5) 3))
(= 8 ((partial + 5) 3))

;; Regular Expressions
(= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))

;; simple recursion example
(defn recur-sum
  ([vals] (recur-sum vals 0))
  ([vals acc]
   (if (empty? vals)
     acc
     (recur-sum (rest vals) (+ (first vals) acc)))))

(recur-sum [1 2 3])

;; Simple recursion
((fn foo [x] 
   (when (> x 0) 
     (conj (foo (dec x)) x)))
 5)

;; -> macro
(= (last (sort (rest (reverse [2 5 4 1 3 6]))))
   (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (last))
   5)

;; ->> macro
(= (reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (reduce +))
   11)

;; Recurring Theme
(= [7 6 5 4 3]
  (loop [x 5
         result []]
    (if (> x 0)
      (recur (dec x) (conj result (+ 2 x)))
      result)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

;; @@

;; @@
