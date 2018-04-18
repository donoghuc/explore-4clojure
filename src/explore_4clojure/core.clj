;; gorilla-repl.fileformat = 1

;; **
;;; # Learn Clojure with 4Closure!
;;; 
;;; Solve 4clojure problems and have solutions/explanations organized in pretty HTML
;;; 
;;; __source code__ https://github.com/donoghuc/explore-4clojure
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

;; **
;;; # Count a Sequence \#22
;;; 
;;; Write a function which returns the total number of elements in a sequence.
;;; 
;;; __Special Restrictions:__ count
;; **

;; @@
;; casadilla
(def casadilla
  (fn [x] (reduce + (map #(if % 1 1) x))))

;; chouser
reduce #(do %2 (+ 1 %)) 0

;;noisesmith
reduce (fn [i _] (inc i)) 0


;; test
(= (casadilla '(1 2 3 3 1)) 5)
(= (reduce #(do %2 (+ 1 %)) 0 '(1 2 3 3 1)) 5)
(= (reduce (fn [i _] (inc i)) 0 '(1 2 3 3 1)) 5)
;; @@

;; **
;;; # Reverse a Sequence #23
;;; Write a function which reverses a sequence.
;;; __Special Restrictions:__ reverse rseq
;; **

;; @@
;; casadilla (recursive)
(def casadilla
  (fn recur-rev
    ([col] (recur-rev col '()))
    ([col acc]
     (if (empty? col)
       acc
       (recur (rest col) (cons (first col) acc))))))

;; cleaner way is to just use into () as elements are added to front


;;test
(= (casadilla [1 2 3 4 5]) [5 4 3 2 1])
(= (into () [1 2 3 4 5]) [5 4 3 2 1])

;; @@

;; **
;;; # Palindrome Detector \#27
;;; Write a function which returns true if the given sequence is a palindrome.
;;; 
;;; Hint: "racecar" does not equal '(\r \a \c \e \c \a \r)
;; **

;; @@
;; casadilla
(def casadilla
  #(every? true? (map (fn [x] (= (first x) (last x))) (map vector % (reverse %)))))

;; other (chouser, cgrand, noisesmith)
(def other
  #(= (seq %) (reverse %)))

(true? (casadilla "racecar"))
(true? (other "racecar"))
;; @@

;; **
;;; # Fibonacci Sequence \#26
;;; Write a function which returns the first X fibonacci numbers
;;; 
;;; __note__ assumes we want to start with [1 1] not [ 1 0]...
;;; 
;;; I found the following website very interesting for solving this
;;; http://blog.klipse.tech/clojurescript/2016/04/20/fibonacci.html
;; **

;; @@
;; iterate solution (casadilla submitted this one)
(def fib-seq-iterate
  (map first (iterate 
               (fn [[a b]] [b (+ a b)]) [0 1])))

;; test iterate
(take 10 fib-seq-iterate)

;; lazy-cat 
(def fib-seq-cat
  (lazy-cat [0 1] (map + (rest fib-seq-cat) fib-seq-cat)))

;; test lazy cat
(take 10 fib-seq-cat)

;; lazy-seq
(def fib-seq-seq
  ((fn fib [a b] 
     (lazy-seq (cons a (fib b (+ a b)))))
   0 1))

;; test lazy seq
(take 10 fib-seq-seq)

;;noisesmith (this one is very cool)
(def noisesmith
  #(->> [1 1] (iterate (juxt last (partial apply +))) (take %) (map first)))

;;chouser, amalloy
;; (fn f [a b n] (if (> n 0) (cons a (f b (+ a b) (- n 1))))) 1 1

;;test
(= ((fn f [a b n] (if (> n 0) (cons a (f b (+ a b) (- n 1))))) 1 1 6) '(1 1 2 3 5 8))
(= (noisesmith 6) '(1 1 2 3 5 8))
;; @@

;; **
;;; # Compress a Sequence \#30
;;; 
;;; Write a function which removes consecutive duplicates from a sequence.
;; **

;; @@
;; casadilla recursive solution
(def casadilla
  (fn recur-sol
  ([col] (recur-sol col []))
  ([col acc]
   (if (empty? col)
     acc
     (recur (rest col) (if-not (= (first col) (second col))
                         (conj acc (first col))
                         acc))))))

;; chouser, cgrand amalloy
(def other
  #(map first (partition-by identity %)))

;; noisesmith (again with the coolest...)
(def noisesmith
  (fn [sq] (reduce (fn [acc el] (if (= (peek acc) el) acc (conj acc el))) [] sq)))

(= (apply str (casadilla "Leeeeeerrroyyy")) "Leroy")
(= (apply str (other "Leeeeeerrroyyy")) "Leroy")
(= (apply str (noisesmith "Leeeeeerrroyyy")) "Leroy")
;; @@

;; **
;;; # Flatten a Sequence \#28
;;; 
;;; Write a function which flattens a sequence.
;;; 
;;; __Special Restrictions:__ flatten
;;; 
;;; __note:__ found the solution on clojure docs for tree-seq https://clojuredocs.org/clojure.core/tree-seq 
;; **

;; @@
;; casadilla (accidentilly found on clojure docs...)
(def casadilla 
  #(filter (complement sequential?) (tree-seq sequential? identity %)))

;; chouser
(def chouser
  (fn f [x] (mapcat #(if (coll? %) (f %) [%]) x)))

;; cgrand
(def cgrand
  (fn f [x] (if (coll? x) (mapcat f x) [x])))

;; noisesmith
(def noisesmith
  (fn flt
  [e]
   (if (and (coll? e) (seq e))
     (concat (flt (first e)) (flt (rest e)))
     (when (not= e ()) [e]))))

;; test
(= (casadilla '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (chouser '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (cgrand '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (noisesmith '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
;; @@

;; **
;;; # Product Digits \#99
;;; Write a function which multiplies two numbers and returns the result as a sequence of its digits.
;; **

;; @@
;; casadilla
(def casadilla
  (fn [a b]
   ((fn recur-soln
      ([value] (recur-soln value '()))
      ([value acc] (let [x (/ value 10)
                         f #(* 10 (- (/ % 10) (int (/ % 10))))]
                     (if-not (>= x 1)
                       (cons (f value) acc)
                       (recur (int x) (cons (f value) acc)))))) (* a b))))


;;chouser
(def chouser
  #(for [c (str (* % %2))] (- (int c) 48)))

;;cgrand
(def cgrand
  #(map (zipmap "0123456789" (range 10)) (str (apply * %&))))

;;amalloy
(def amalloy
  #(map (comp read-string str)
      (str (* % %2))))

;;noisesmith
(def noisesmith
  (fn digits [a b]
  (->> (* a b)
       (repeat 2)
       (iterate (fn chop [[_ pool]]
                  [(rem pool 10) (quot pool 10)]))
       (take-while #(not (every? zero? %)))
       (rest)
       (map first)
       (reverse))))

;; test
(= (noisesmith 99 9) [8 9 1])
(= (chouser 99 9) [8 9 1])
(= (cgrand 99 9) [8 9 1])
(= (amalloy 99 9) [8 9 1])
(= (noisesmith 99 9) [8 9 1])
;; @@

;; **
;;; # Group a Sequence \#63
;;; Given a function f and a sequence s, write a function which returns a map. The keys should be the values of f applied to each item in s. The value at each key should be a vector of corresponding items in the order they appear in s.
;;; 
;;; __Special Restrictions:__ group-by
;;; 
;; **

;; @@
;; first try at recursive (does not work on 4clojure)
(defn casadilla-recur [expr col]
  ((fn [proc acc]
     (if (empty? proc)
       acc
       (let [i (first proc)]
         (if (contains? acc (first i))
           (recur (rest proc) (update acc (first i) conj (last i)))
           (recur (rest proc) (assoc acc (first i) (vector (last i))))))))
   (map vector (map expr col) col) {}))

;; second try after some research
(def casadilla-sub
  (fn [expr col]
  	(apply merge-with concat (map (fn [a b] {a [b]}) (map expr col) col))))

;;chouser
(def chouser
  #(reduce
  (fn [m x] (assoc m (% x) (conj (m (% x) []) x))) 
  {} %2))

;;cgrand
(def cgrand
  #(apply merge-with into (map (fn [x] {(% x) [x]}) %2)))

;;amalloy
(def amalloy
  #(apply merge-with into
        (for [x %2]
          {(% x) [x]})))

;;noisesmith's first
(def noisesmith-1
  #(reduce (fn [m el]
          (let [lookup (%1 el)
                existing (get m lookup [])
                updated (conj existing el)]
            (assoc m lookup updated)))
        {}
        %2))

;;noisesmith's second
(def noisesmith-2
  #(reduce (fn [m el]
           (update-in m [(%1 el)] (fnil conj []) el))
         {}
         %2))

;;test
(= (casadilla-recur #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})
(= (casadilla-sub #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})
(= (chouser #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})
(= (cgrand #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})
(= (amalloy #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})
(= (noisesmith-1 #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})
(= (noisesmith-2 #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})

;; @@

;; @@

;; @@
