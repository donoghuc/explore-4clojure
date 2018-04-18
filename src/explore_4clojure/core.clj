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

;; for loops
(= '(1 5 9 13 17 21 25 29 33 37) (for [x (range 40)
            :when (= 1 (rem x 4))]
        x))

(= '(1 5 9 13 17 21 25 29 33 37) (for [x (iterate #(+ 4 %) 0)
            :let [z (inc x)]
            :while (< z 40)]
        z))

(= '(1 5 9 13 17 21 25 29 33 37) (for [[x y] (partition 2 (range 20))]
        (+ x y)))
;; @@

;; **
;;; # Note about 4clojure and solutions format
;;; 
;;; #### 4clojure gives you a koans-like "fill in the blank"
;;; 
;;; __example__ 
;;; 
;;; (= true (zero? __))
;;; 
;;; #### all solututions are aliased as their 4clojure author
;;; 
;;; __example__
;;; 
;;; (def casadilla
;;;   0)
;;; 
;;; #### this makes filling in the example test easy
;;; 
;;; __example__
;;; 
;;; (= true (zero? casadilla))
;; **

;; **
;;; ## Maximum value \#38
;;; Write a function which takes a variable number of parameters and returns the maximum value.
;;; 
;;; __Special Restrictions:__ 
;;; max
;;; max-key
;; **

;; @@
;; casadilla 
(def casadilla 
  #(reduce (fn [a b] (if (> a b) a b)) %&))

;; cgrand
(def cgrand
  (comp last sort list))

;; test
(= (casadilla 1 8 3 4) 8)
(= (cgrand 1 8 3 4) 8)
;; @@

;; **
;;; # Interleave Two Seqs \#39
;;; 
;;; Write a function which takes two sequences and returns the first item from each, then the second item from each, then the third, etc.
;;; __Special Restrictions:__ interleave
;; **

;; @@
;; casadilla recursive
(def casadilla-recur 
  (fn recur-soln
  ([col-1 col-2] (recur-soln col-1 col-2 []))
  ([col-1 col-2 acc]
   (if (or (empty? col-1) (empty? col-2))
     acc
     (recur (rest col-1) (rest col-2) (conj acc (first col-1) (first col-2)))))))

;; casadilla mapcat
(def casadilla
  #(mapcat vector %1 %2))

;; test
(= (casadilla-recur [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
(= (casadilla [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
;; @@

;; **
;;; # Map Defaults \#156
;;; When retrieving values from a map, you can specify default values in case the key is not found:
;;; 
;;; (= 2 (:foo {:bar 0, :baz 1} 2))
;;; 
;;; However, what if you want the map itself to contain the default values? Write a function which takes a default value and a sequence of keys and constructs a map.
;;; 
;; **

;; @@
;;casadilla
(def casadilla
  #(zipmap %2 (repeat %)))

;;chouser
(def chouser
  #(into {} (map vector %2 (repeat %))))

;; amalloy
(def amalloy
  #(into {}
       (for [k %2]
         [k %])))

;; test
(= (casadilla 0 [:a :b :c]) {:a 0 :b 0 :c 0})
(= (chouser 0 [:a :b :c]) {:a 0 :b 0 :c 0})
(= (amalloy 0 [:a :b :c]) {:a 0 :b 0 :c 0})
;; @@

;; @@

;; @@
