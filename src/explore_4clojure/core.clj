;; gorilla-repl.fileformat = 1

;; **
;;; # Learn Clojure with 4Closure!
;;; 
;;; Solve 4clojure problems and have solutions/explanations organized in pretty HTML
;;; 
;;; __source code__ https://github.com/donoghuc/explore-4clojure
;; **

;; **
;;; # Gorilla REPL 
;;; Below is my attempt to play with the gorilla repl plotting lib
;;; http://gorilla-repl.org/
;; **

;; @@
(ns explore-4clojure
  (:require [gorilla-plot.core :as plot]))

;; resolution is frequency of points on a line
(def res 10)

(defn line 
  "build points in a line of length len at frequency res"
  [len res]
  (take (inc (* len res)) (iterate #(+ % (/ 1 res)) 0)))

(defn horizontal
  "draw horiz line of length len at y coord y with resolution res"
  [len y res]
  (map vector (line len res) (repeat y)))

(defn vertical
  "draw vert line of length len at x coord x with resolution res"
  [len x res]
  (map vector (repeat x) (line len res)))

(defn shift
  "shift coordinate by x y (additive)"
  [x y]
  (fn [[a b]] (identity [(+ x a) (+ y b)])))

(defn space
  "space letters"
  [letter offset]
  (map (shift (first offset) (last offset)) letter))

(defn space-iterator
  "space between characters"
  [start-x start-y spacing]
  (iterate  (fn [[x y]] (identity [(+ x spacing) y])) [start-x start-y]))

;; build letters of my avatar 
(def letters {
     :c (concat (horizontal 1 0 res)
                (horizontal 1 2 res)
                (vertical 2 0 res))
     :a (concat (horizontal 1 2 res)
                (horizontal 1 1 res)
                (vertical 2 0 res)
                (vertical 2 1 res))
     :s (concat (horizontal 1 0 res)
                (horizontal 1 1 res)
                (horizontal 1 2 res)
                (vertical 1 1 res)
                (map (shift 0 1) (vertical 1 0 res)))
     :d (concat (horizontal 1 0 res)
                (horizontal 1 2 res)
                (vertical 2 0 res)
                (vertical 2 1 res))
     :i (concat (horizontal 1 0 res)
                (horizontal 1 2 res)
                (map (shift (/ 1 2) 0) (vertical 2 0 res)))
     :l (concat (horizontal 1 0 res)
                (vertical 2 0 res))
     })

;; build list of cords for my avatar
(def casadilla 
  (mapcat space (into [] ((juxt :c :a :s :a :d :i :l :l :a) letters))
          (space-iterator 1 1 (/ 3 2))))


;; expects list of vector with x y [[x y] [x1 y 1] ...] 
;; plot range is [[x range] [ y range]]
(plot/list-plot casadilla :plot-range [[0 15] [0 4]])
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"top":10,"left":55,"bottom":40,"right":10},"data":[{"name":"9a0d5e20-1dc2-4746-bce5-a070a16decd8","values":[{"x":1,"y":1},{"x":1.1,"y":1},{"x":1.2,"y":1},{"x":1.3,"y":1},{"x":1.4,"y":1},{"x":1.5,"y":1},{"x":1.6,"y":1},{"x":1.7,"y":1},{"x":1.8,"y":1},{"x":1.9,"y":1},{"x":2,"y":1},{"x":1,"y":3},{"x":1.1,"y":3},{"x":1.2,"y":3},{"x":1.3,"y":3},{"x":1.4,"y":3},{"x":1.5,"y":3},{"x":1.6,"y":3},{"x":1.7,"y":3},{"x":1.8,"y":3},{"x":1.9,"y":3},{"x":2,"y":3},{"x":1,"y":1},{"x":1,"y":1.1},{"x":1,"y":1.2},{"x":1,"y":1.3},{"x":1,"y":1.4},{"x":1,"y":1.5},{"x":1,"y":1.6},{"x":1,"y":1.7},{"x":1,"y":1.8},{"x":1,"y":1.9},{"x":1,"y":2},{"x":1,"y":2.1},{"x":1,"y":2.2},{"x":1,"y":2.3},{"x":1,"y":2.4},{"x":1,"y":2.5},{"x":1,"y":2.6},{"x":1,"y":2.7},{"x":1,"y":2.8},{"x":1,"y":2.9},{"x":1,"y":3},{"x":2.5,"y":3},{"x":2.6,"y":3},{"x":2.7,"y":3},{"x":2.8,"y":3},{"x":2.9,"y":3},{"x":3,"y":3},{"x":3.1,"y":3},{"x":3.2,"y":3},{"x":3.3,"y":3},{"x":3.4,"y":3},{"x":3.5,"y":3},{"x":2.5,"y":2},{"x":2.6,"y":2},{"x":2.7,"y":2},{"x":2.8,"y":2},{"x":2.9,"y":2},{"x":3,"y":2},{"x":3.1,"y":2},{"x":3.2,"y":2},{"x":3.3,"y":2},{"x":3.4,"y":2},{"x":3.5,"y":2},{"x":2.5,"y":1},{"x":2.5,"y":1.1},{"x":2.5,"y":1.2},{"x":2.5,"y":1.3},{"x":2.5,"y":1.4},{"x":2.5,"y":1.5},{"x":2.5,"y":1.6},{"x":2.5,"y":1.7},{"x":2.5,"y":1.8},{"x":2.5,"y":1.9},{"x":2.5,"y":2},{"x":2.5,"y":2.1},{"x":2.5,"y":2.2},{"x":2.5,"y":2.3},{"x":2.5,"y":2.4},{"x":2.5,"y":2.5},{"x":2.5,"y":2.6},{"x":2.5,"y":2.7},{"x":2.5,"y":2.8},{"x":2.5,"y":2.9},{"x":2.5,"y":3},{"x":3.5,"y":1},{"x":3.5,"y":1.1},{"x":3.5,"y":1.2},{"x":3.5,"y":1.3},{"x":3.5,"y":1.4},{"x":3.5,"y":1.5},{"x":3.5,"y":1.6},{"x":3.5,"y":1.7},{"x":3.5,"y":1.8},{"x":3.5,"y":1.9},{"x":3.5,"y":2},{"x":3.5,"y":2.1},{"x":3.5,"y":2.2},{"x":3.5,"y":2.3},{"x":3.5,"y":2.4},{"x":3.5,"y":2.5},{"x":3.5,"y":2.6},{"x":3.5,"y":2.7},{"x":3.5,"y":2.8},{"x":3.5,"y":2.9},{"x":3.5,"y":3},{"x":4,"y":1},{"x":4.1,"y":1},{"x":4.2,"y":1},{"x":4.3,"y":1},{"x":4.4,"y":1},{"x":4.5,"y":1},{"x":4.6,"y":1},{"x":4.7,"y":1},{"x":4.8,"y":1},{"x":4.9,"y":1},{"x":5,"y":1},{"x":4,"y":2},{"x":4.1,"y":2},{"x":4.2,"y":2},{"x":4.3,"y":2},{"x":4.4,"y":2},{"x":4.5,"y":2},{"x":4.6,"y":2},{"x":4.7,"y":2},{"x":4.8,"y":2},{"x":4.9,"y":2},{"x":5,"y":2},{"x":4,"y":3},{"x":4.1,"y":3},{"x":4.2,"y":3},{"x":4.3,"y":3},{"x":4.4,"y":3},{"x":4.5,"y":3},{"x":4.6,"y":3},{"x":4.7,"y":3},{"x":4.8,"y":3},{"x":4.9,"y":3},{"x":5,"y":3},{"x":5,"y":1},{"x":5,"y":1.1},{"x":5,"y":1.2},{"x":5,"y":1.3},{"x":5,"y":1.4},{"x":5,"y":1.5},{"x":5,"y":1.6},{"x":5,"y":1.7},{"x":5,"y":1.8},{"x":5,"y":1.9},{"x":5,"y":2},{"x":4,"y":2},{"x":4,"y":2.1},{"x":4,"y":2.2},{"x":4,"y":2.3},{"x":4,"y":2.4},{"x":4,"y":2.5},{"x":4,"y":2.6},{"x":4,"y":2.7},{"x":4,"y":2.8},{"x":4,"y":2.9},{"x":4,"y":3},{"x":5.5,"y":3},{"x":5.6,"y":3},{"x":5.7,"y":3},{"x":5.8,"y":3},{"x":5.9,"y":3},{"x":6,"y":3},{"x":6.1,"y":3},{"x":6.2,"y":3},{"x":6.3,"y":3},{"x":6.4,"y":3},{"x":6.5,"y":3},{"x":5.5,"y":2},{"x":5.6,"y":2},{"x":5.7,"y":2},{"x":5.8,"y":2},{"x":5.9,"y":2},{"x":6,"y":2},{"x":6.1,"y":2},{"x":6.2,"y":2},{"x":6.3,"y":2},{"x":6.4,"y":2},{"x":6.5,"y":2},{"x":5.5,"y":1},{"x":5.5,"y":1.1},{"x":5.5,"y":1.2},{"x":5.5,"y":1.3},{"x":5.5,"y":1.4},{"x":5.5,"y":1.5},{"x":5.5,"y":1.6},{"x":5.5,"y":1.7},{"x":5.5,"y":1.8},{"x":5.5,"y":1.9},{"x":5.5,"y":2},{"x":5.5,"y":2.1},{"x":5.5,"y":2.2},{"x":5.5,"y":2.3},{"x":5.5,"y":2.4},{"x":5.5,"y":2.5},{"x":5.5,"y":2.6},{"x":5.5,"y":2.7},{"x":5.5,"y":2.8},{"x":5.5,"y":2.9},{"x":5.5,"y":3},{"x":6.5,"y":1},{"x":6.5,"y":1.1},{"x":6.5,"y":1.2},{"x":6.5,"y":1.3},{"x":6.5,"y":1.4},{"x":6.5,"y":1.5},{"x":6.5,"y":1.6},{"x":6.5,"y":1.7},{"x":6.5,"y":1.8},{"x":6.5,"y":1.9},{"x":6.5,"y":2},{"x":6.5,"y":2.1},{"x":6.5,"y":2.2},{"x":6.5,"y":2.3},{"x":6.5,"y":2.4},{"x":6.5,"y":2.5},{"x":6.5,"y":2.6},{"x":6.5,"y":2.7},{"x":6.5,"y":2.8},{"x":6.5,"y":2.9},{"x":6.5,"y":3},{"x":7,"y":1},{"x":7.1,"y":1},{"x":7.2,"y":1},{"x":7.3,"y":1},{"x":7.4,"y":1},{"x":7.5,"y":1},{"x":7.6,"y":1},{"x":7.7,"y":1},{"x":7.8,"y":1},{"x":7.9,"y":1},{"x":8,"y":1},{"x":7,"y":3},{"x":7.1,"y":3},{"x":7.2,"y":3},{"x":7.3,"y":3},{"x":7.4,"y":3},{"x":7.5,"y":3},{"x":7.6,"y":3},{"x":7.7,"y":3},{"x":7.8,"y":3},{"x":7.9,"y":3},{"x":8,"y":3},{"x":7,"y":1},{"x":7,"y":1.1},{"x":7,"y":1.2},{"x":7,"y":1.3},{"x":7,"y":1.4},{"x":7,"y":1.5},{"x":7,"y":1.6},{"x":7,"y":1.7},{"x":7,"y":1.8},{"x":7,"y":1.9},{"x":7,"y":2},{"x":7,"y":2.1},{"x":7,"y":2.2},{"x":7,"y":2.3},{"x":7,"y":2.4},{"x":7,"y":2.5},{"x":7,"y":2.6},{"x":7,"y":2.7},{"x":7,"y":2.8},{"x":7,"y":2.9},{"x":7,"y":3},{"x":8,"y":1},{"x":8,"y":1.1},{"x":8,"y":1.2},{"x":8,"y":1.3},{"x":8,"y":1.4},{"x":8,"y":1.5},{"x":8,"y":1.6},{"x":8,"y":1.7},{"x":8,"y":1.8},{"x":8,"y":1.9},{"x":8,"y":2},{"x":8,"y":2.1},{"x":8,"y":2.2},{"x":8,"y":2.3},{"x":8,"y":2.4},{"x":8,"y":2.5},{"x":8,"y":2.6},{"x":8,"y":2.7},{"x":8,"y":2.8},{"x":8,"y":2.9},{"x":8,"y":3},{"x":8.5,"y":1},{"x":8.6,"y":1},{"x":8.7,"y":1},{"x":8.8,"y":1},{"x":8.9,"y":1},{"x":9,"y":1},{"x":9.1,"y":1},{"x":9.2,"y":1},{"x":9.3,"y":1},{"x":9.4,"y":1},{"x":9.5,"y":1},{"x":8.5,"y":3},{"x":8.6,"y":3},{"x":8.7,"y":3},{"x":8.8,"y":3},{"x":8.9,"y":3},{"x":9,"y":3},{"x":9.1,"y":3},{"x":9.2,"y":3},{"x":9.3,"y":3},{"x":9.4,"y":3},{"x":9.5,"y":3},{"x":9,"y":1},{"x":9,"y":1.1},{"x":9,"y":1.2},{"x":9,"y":1.3},{"x":9,"y":1.4},{"x":9,"y":1.5},{"x":9,"y":1.6},{"x":9,"y":1.7},{"x":9,"y":1.8},{"x":9,"y":1.9},{"x":9,"y":2},{"x":9,"y":2.1},{"x":9,"y":2.2},{"x":9,"y":2.3},{"x":9,"y":2.4},{"x":9,"y":2.5},{"x":9,"y":2.6},{"x":9,"y":2.7},{"x":9,"y":2.8},{"x":9,"y":2.9},{"x":9,"y":3},{"x":10,"y":1},{"x":10.1,"y":1},{"x":10.2,"y":1},{"x":10.3,"y":1},{"x":10.4,"y":1},{"x":10.5,"y":1},{"x":10.6,"y":1},{"x":10.7,"y":1},{"x":10.8,"y":1},{"x":10.9,"y":1},{"x":11,"y":1},{"x":10,"y":1},{"x":10,"y":1.1},{"x":10,"y":1.2},{"x":10,"y":1.3},{"x":10,"y":1.4},{"x":10,"y":1.5},{"x":10,"y":1.6},{"x":10,"y":1.7},{"x":10,"y":1.8},{"x":10,"y":1.9},{"x":10,"y":2},{"x":10,"y":2.1},{"x":10,"y":2.2},{"x":10,"y":2.3},{"x":10,"y":2.4},{"x":10,"y":2.5},{"x":10,"y":2.6},{"x":10,"y":2.7},{"x":10,"y":2.8},{"x":10,"y":2.9},{"x":10,"y":3},{"x":11.5,"y":1},{"x":11.6,"y":1},{"x":11.7,"y":1},{"x":11.8,"y":1},{"x":11.9,"y":1},{"x":12,"y":1},{"x":12.1,"y":1},{"x":12.2,"y":1},{"x":12.3,"y":1},{"x":12.4,"y":1},{"x":12.5,"y":1},{"x":11.5,"y":1},{"x":11.5,"y":1.1},{"x":11.5,"y":1.2},{"x":11.5,"y":1.3},{"x":11.5,"y":1.4},{"x":11.5,"y":1.5},{"x":11.5,"y":1.6},{"x":11.5,"y":1.7},{"x":11.5,"y":1.8},{"x":11.5,"y":1.9},{"x":11.5,"y":2},{"x":11.5,"y":2.1},{"x":11.5,"y":2.2},{"x":11.5,"y":2.3},{"x":11.5,"y":2.4},{"x":11.5,"y":2.5},{"x":11.5,"y":2.6},{"x":11.5,"y":2.7},{"x":11.5,"y":2.8},{"x":11.5,"y":2.9},{"x":11.5,"y":3},{"x":13,"y":3},{"x":13.1,"y":3},{"x":13.2,"y":3},{"x":13.3,"y":3},{"x":13.4,"y":3},{"x":13.5,"y":3},{"x":13.6,"y":3},{"x":13.7,"y":3},{"x":13.8,"y":3},{"x":13.9,"y":3},{"x":14,"y":3},{"x":13,"y":2},{"x":13.1,"y":2},{"x":13.2,"y":2},{"x":13.3,"y":2},{"x":13.4,"y":2},{"x":13.5,"y":2},{"x":13.6,"y":2},{"x":13.7,"y":2},{"x":13.8,"y":2},{"x":13.9,"y":2},{"x":14,"y":2},{"x":13,"y":1},{"x":13,"y":1.1},{"x":13,"y":1.2},{"x":13,"y":1.3},{"x":13,"y":1.4},{"x":13,"y":1.5},{"x":13,"y":1.6},{"x":13,"y":1.7},{"x":13,"y":1.8},{"x":13,"y":1.9},{"x":13,"y":2},{"x":13,"y":2.1},{"x":13,"y":2.2},{"x":13,"y":2.3},{"x":13,"y":2.4},{"x":13,"y":2.5},{"x":13,"y":2.6},{"x":13,"y":2.7},{"x":13,"y":2.8},{"x":13,"y":2.9},{"x":13,"y":3},{"x":14,"y":1},{"x":14,"y":1.1},{"x":14,"y":1.2},{"x":14,"y":1.3},{"x":14,"y":1.4},{"x":14,"y":1.5},{"x":14,"y":1.6},{"x":14,"y":1.7},{"x":14,"y":1.8},{"x":14,"y":1.9},{"x":14,"y":2},{"x":14,"y":2.1},{"x":14,"y":2.2},{"x":14,"y":2.3},{"x":14,"y":2.4},{"x":14,"y":2.5},{"x":14,"y":2.6},{"x":14,"y":2.7},{"x":14,"y":2.8},{"x":14,"y":2.9},{"x":14,"y":3}]}],"marks":[{"type":"symbol","from":{"data":"9a0d5e20-1dc2-4746-bce5-a070a16decd8"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"fill":{"value":"steelblue"},"fillOpacity":{"value":1}},"update":{"shape":"circle","size":{"value":70},"stroke":{"value":"transparent"}},"hover":{"size":{"value":210},"stroke":{"value":"white"}}}}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":[0,15]},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":[0,4]}],"axes":[{"type":"x","scale":"x"},{"type":"y","scale":"y"}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:top 10, :left 55, :bottom 40, :right 10}, :data [{:name \"9a0d5e20-1dc2-4746-bce5-a070a16decd8\", :values ({:x 1, :y 1} {:x 11/10, :y 1} {:x 6/5, :y 1} {:x 13/10, :y 1} {:x 7/5, :y 1} {:x 3/2, :y 1} {:x 8/5, :y 1} {:x 17/10, :y 1} {:x 9/5, :y 1} {:x 19/10, :y 1} {:x 2N, :y 1} {:x 1, :y 3} {:x 11/10, :y 3} {:x 6/5, :y 3} {:x 13/10, :y 3} {:x 7/5, :y 3} {:x 3/2, :y 3} {:x 8/5, :y 3} {:x 17/10, :y 3} {:x 9/5, :y 3} {:x 19/10, :y 3} {:x 2N, :y 3} {:x 1, :y 1} {:x 1, :y 11/10} {:x 1, :y 6/5} {:x 1, :y 13/10} {:x 1, :y 7/5} {:x 1, :y 3/2} {:x 1, :y 8/5} {:x 1, :y 17/10} {:x 1, :y 9/5} {:x 1, :y 19/10} {:x 1, :y 2N} {:x 1, :y 21/10} {:x 1, :y 11/5} {:x 1, :y 23/10} {:x 1, :y 12/5} {:x 1, :y 5/2} {:x 1, :y 13/5} {:x 1, :y 27/10} {:x 1, :y 14/5} {:x 1, :y 29/10} {:x 1, :y 3N} {:x 5/2, :y 3} {:x 13/5, :y 3} {:x 27/10, :y 3} {:x 14/5, :y 3} {:x 29/10, :y 3} {:x 3N, :y 3} {:x 31/10, :y 3} {:x 16/5, :y 3} {:x 33/10, :y 3} {:x 17/5, :y 3} {:x 7/2, :y 3} {:x 5/2, :y 2} {:x 13/5, :y 2} {:x 27/10, :y 2} {:x 14/5, :y 2} {:x 29/10, :y 2} {:x 3N, :y 2} {:x 31/10, :y 2} {:x 16/5, :y 2} {:x 33/10, :y 2} {:x 17/5, :y 2} {:x 7/2, :y 2} {:x 5/2, :y 1} {:x 5/2, :y 11/10} {:x 5/2, :y 6/5} {:x 5/2, :y 13/10} {:x 5/2, :y 7/5} {:x 5/2, :y 3/2} {:x 5/2, :y 8/5} {:x 5/2, :y 17/10} {:x 5/2, :y 9/5} {:x 5/2, :y 19/10} {:x 5/2, :y 2N} {:x 5/2, :y 21/10} {:x 5/2, :y 11/5} {:x 5/2, :y 23/10} {:x 5/2, :y 12/5} {:x 5/2, :y 5/2} {:x 5/2, :y 13/5} {:x 5/2, :y 27/10} {:x 5/2, :y 14/5} {:x 5/2, :y 29/10} {:x 5/2, :y 3N} {:x 7/2, :y 1} {:x 7/2, :y 11/10} {:x 7/2, :y 6/5} {:x 7/2, :y 13/10} {:x 7/2, :y 7/5} {:x 7/2, :y 3/2} {:x 7/2, :y 8/5} {:x 7/2, :y 17/10} {:x 7/2, :y 9/5} {:x 7/2, :y 19/10} {:x 7/2, :y 2N} {:x 7/2, :y 21/10} {:x 7/2, :y 11/5} {:x 7/2, :y 23/10} {:x 7/2, :y 12/5} {:x 7/2, :y 5/2} {:x 7/2, :y 13/5} {:x 7/2, :y 27/10} {:x 7/2, :y 14/5} {:x 7/2, :y 29/10} {:x 7/2, :y 3N} {:x 4N, :y 1} {:x 41/10, :y 1} {:x 21/5, :y 1} {:x 43/10, :y 1} {:x 22/5, :y 1} {:x 9/2, :y 1} {:x 23/5, :y 1} {:x 47/10, :y 1} {:x 24/5, :y 1} {:x 49/10, :y 1} {:x 5N, :y 1} {:x 4N, :y 2} {:x 41/10, :y 2} {:x 21/5, :y 2} {:x 43/10, :y 2} {:x 22/5, :y 2} {:x 9/2, :y 2} {:x 23/5, :y 2} {:x 47/10, :y 2} {:x 24/5, :y 2} {:x 49/10, :y 2} {:x 5N, :y 2} {:x 4N, :y 3} {:x 41/10, :y 3} {:x 21/5, :y 3} {:x 43/10, :y 3} {:x 22/5, :y 3} {:x 9/2, :y 3} {:x 23/5, :y 3} {:x 47/10, :y 3} {:x 24/5, :y 3} {:x 49/10, :y 3} {:x 5N, :y 3} {:x 5N, :y 1} {:x 5N, :y 11/10} {:x 5N, :y 6/5} {:x 5N, :y 13/10} {:x 5N, :y 7/5} {:x 5N, :y 3/2} {:x 5N, :y 8/5} {:x 5N, :y 17/10} {:x 5N, :y 9/5} {:x 5N, :y 19/10} {:x 5N, :y 2N} {:x 4N, :y 2} {:x 4N, :y 21/10} {:x 4N, :y 11/5} {:x 4N, :y 23/10} {:x 4N, :y 12/5} {:x 4N, :y 5/2} {:x 4N, :y 13/5} {:x 4N, :y 27/10} {:x 4N, :y 14/5} {:x 4N, :y 29/10} {:x 4N, :y 3N} {:x 11/2, :y 3} {:x 28/5, :y 3} {:x 57/10, :y 3} {:x 29/5, :y 3} {:x 59/10, :y 3} {:x 6N, :y 3} {:x 61/10, :y 3} {:x 31/5, :y 3} {:x 63/10, :y 3} {:x 32/5, :y 3} {:x 13/2, :y 3} {:x 11/2, :y 2} {:x 28/5, :y 2} {:x 57/10, :y 2} {:x 29/5, :y 2} {:x 59/10, :y 2} {:x 6N, :y 2} {:x 61/10, :y 2} {:x 31/5, :y 2} {:x 63/10, :y 2} {:x 32/5, :y 2} {:x 13/2, :y 2} {:x 11/2, :y 1} {:x 11/2, :y 11/10} {:x 11/2, :y 6/5} {:x 11/2, :y 13/10} {:x 11/2, :y 7/5} {:x 11/2, :y 3/2} {:x 11/2, :y 8/5} {:x 11/2, :y 17/10} {:x 11/2, :y 9/5} {:x 11/2, :y 19/10} {:x 11/2, :y 2N} {:x 11/2, :y 21/10} {:x 11/2, :y 11/5} {:x 11/2, :y 23/10} {:x 11/2, :y 12/5} {:x 11/2, :y 5/2} {:x 11/2, :y 13/5} {:x 11/2, :y 27/10} {:x 11/2, :y 14/5} {:x 11/2, :y 29/10} {:x 11/2, :y 3N} {:x 13/2, :y 1} {:x 13/2, :y 11/10} {:x 13/2, :y 6/5} {:x 13/2, :y 13/10} {:x 13/2, :y 7/5} {:x 13/2, :y 3/2} {:x 13/2, :y 8/5} {:x 13/2, :y 17/10} {:x 13/2, :y 9/5} {:x 13/2, :y 19/10} {:x 13/2, :y 2N} {:x 13/2, :y 21/10} {:x 13/2, :y 11/5} {:x 13/2, :y 23/10} {:x 13/2, :y 12/5} {:x 13/2, :y 5/2} {:x 13/2, :y 13/5} {:x 13/2, :y 27/10} {:x 13/2, :y 14/5} {:x 13/2, :y 29/10} {:x 13/2, :y 3N} {:x 7N, :y 1} {:x 71/10, :y 1} {:x 36/5, :y 1} {:x 73/10, :y 1} {:x 37/5, :y 1} {:x 15/2, :y 1} {:x 38/5, :y 1} {:x 77/10, :y 1} {:x 39/5, :y 1} {:x 79/10, :y 1} {:x 8N, :y 1} {:x 7N, :y 3} {:x 71/10, :y 3} {:x 36/5, :y 3} {:x 73/10, :y 3} {:x 37/5, :y 3} {:x 15/2, :y 3} {:x 38/5, :y 3} {:x 77/10, :y 3} {:x 39/5, :y 3} {:x 79/10, :y 3} {:x 8N, :y 3} {:x 7N, :y 1} {:x 7N, :y 11/10} {:x 7N, :y 6/5} {:x 7N, :y 13/10} {:x 7N, :y 7/5} {:x 7N, :y 3/2} {:x 7N, :y 8/5} {:x 7N, :y 17/10} {:x 7N, :y 9/5} {:x 7N, :y 19/10} {:x 7N, :y 2N} {:x 7N, :y 21/10} {:x 7N, :y 11/5} {:x 7N, :y 23/10} {:x 7N, :y 12/5} {:x 7N, :y 5/2} {:x 7N, :y 13/5} {:x 7N, :y 27/10} {:x 7N, :y 14/5} {:x 7N, :y 29/10} {:x 7N, :y 3N} {:x 8N, :y 1} {:x 8N, :y 11/10} {:x 8N, :y 6/5} {:x 8N, :y 13/10} {:x 8N, :y 7/5} {:x 8N, :y 3/2} {:x 8N, :y 8/5} {:x 8N, :y 17/10} {:x 8N, :y 9/5} {:x 8N, :y 19/10} {:x 8N, :y 2N} {:x 8N, :y 21/10} {:x 8N, :y 11/5} {:x 8N, :y 23/10} {:x 8N, :y 12/5} {:x 8N, :y 5/2} {:x 8N, :y 13/5} {:x 8N, :y 27/10} {:x 8N, :y 14/5} {:x 8N, :y 29/10} {:x 8N, :y 3N} {:x 17/2, :y 1} {:x 43/5, :y 1} {:x 87/10, :y 1} {:x 44/5, :y 1} {:x 89/10, :y 1} {:x 9N, :y 1} {:x 91/10, :y 1} {:x 46/5, :y 1} {:x 93/10, :y 1} {:x 47/5, :y 1} {:x 19/2, :y 1} {:x 17/2, :y 3} {:x 43/5, :y 3} {:x 87/10, :y 3} {:x 44/5, :y 3} {:x 89/10, :y 3} {:x 9N, :y 3} {:x 91/10, :y 3} {:x 46/5, :y 3} {:x 93/10, :y 3} {:x 47/5, :y 3} {:x 19/2, :y 3} {:x 9N, :y 1} {:x 9N, :y 11/10} {:x 9N, :y 6/5} {:x 9N, :y 13/10} {:x 9N, :y 7/5} {:x 9N, :y 3/2} {:x 9N, :y 8/5} {:x 9N, :y 17/10} {:x 9N, :y 9/5} {:x 9N, :y 19/10} {:x 9N, :y 2N} {:x 9N, :y 21/10} {:x 9N, :y 11/5} {:x 9N, :y 23/10} {:x 9N, :y 12/5} {:x 9N, :y 5/2} {:x 9N, :y 13/5} {:x 9N, :y 27/10} {:x 9N, :y 14/5} {:x 9N, :y 29/10} {:x 9N, :y 3N} {:x 10N, :y 1} {:x 101/10, :y 1} {:x 51/5, :y 1} {:x 103/10, :y 1} {:x 52/5, :y 1} {:x 21/2, :y 1} {:x 53/5, :y 1} {:x 107/10, :y 1} {:x 54/5, :y 1} {:x 109/10, :y 1} {:x 11N, :y 1} {:x 10N, :y 1} {:x 10N, :y 11/10} {:x 10N, :y 6/5} {:x 10N, :y 13/10} {:x 10N, :y 7/5} {:x 10N, :y 3/2} {:x 10N, :y 8/5} {:x 10N, :y 17/10} {:x 10N, :y 9/5} {:x 10N, :y 19/10} {:x 10N, :y 2N} {:x 10N, :y 21/10} {:x 10N, :y 11/5} {:x 10N, :y 23/10} {:x 10N, :y 12/5} {:x 10N, :y 5/2} {:x 10N, :y 13/5} {:x 10N, :y 27/10} {:x 10N, :y 14/5} {:x 10N, :y 29/10} {:x 10N, :y 3N} {:x 23/2, :y 1} {:x 58/5, :y 1} {:x 117/10, :y 1} {:x 59/5, :y 1} {:x 119/10, :y 1} {:x 12N, :y 1} {:x 121/10, :y 1} {:x 61/5, :y 1} {:x 123/10, :y 1} {:x 62/5, :y 1} {:x 25/2, :y 1} {:x 23/2, :y 1} {:x 23/2, :y 11/10} {:x 23/2, :y 6/5} {:x 23/2, :y 13/10} {:x 23/2, :y 7/5} {:x 23/2, :y 3/2} {:x 23/2, :y 8/5} {:x 23/2, :y 17/10} {:x 23/2, :y 9/5} {:x 23/2, :y 19/10} {:x 23/2, :y 2N} {:x 23/2, :y 21/10} {:x 23/2, :y 11/5} {:x 23/2, :y 23/10} {:x 23/2, :y 12/5} {:x 23/2, :y 5/2} {:x 23/2, :y 13/5} {:x 23/2, :y 27/10} {:x 23/2, :y 14/5} {:x 23/2, :y 29/10} {:x 23/2, :y 3N} {:x 13N, :y 3} {:x 131/10, :y 3} {:x 66/5, :y 3} {:x 133/10, :y 3} {:x 67/5, :y 3} {:x 27/2, :y 3} {:x 68/5, :y 3} {:x 137/10, :y 3} {:x 69/5, :y 3} {:x 139/10, :y 3} {:x 14N, :y 3} {:x 13N, :y 2} {:x 131/10, :y 2} {:x 66/5, :y 2} {:x 133/10, :y 2} {:x 67/5, :y 2} {:x 27/2, :y 2} {:x 68/5, :y 2} {:x 137/10, :y 2} {:x 69/5, :y 2} {:x 139/10, :y 2} {:x 14N, :y 2} {:x 13N, :y 1} {:x 13N, :y 11/10} {:x 13N, :y 6/5} {:x 13N, :y 13/10} {:x 13N, :y 7/5} {:x 13N, :y 3/2} {:x 13N, :y 8/5} {:x 13N, :y 17/10} {:x 13N, :y 9/5} {:x 13N, :y 19/10} {:x 13N, :y 2N} {:x 13N, :y 21/10} {:x 13N, :y 11/5} {:x 13N, :y 23/10} {:x 13N, :y 12/5} {:x 13N, :y 5/2} {:x 13N, :y 13/5} {:x 13N, :y 27/10} {:x 13N, :y 14/5} {:x 13N, :y 29/10} {:x 13N, :y 3N} {:x 14N, :y 1} {:x 14N, :y 11/10} {:x 14N, :y 6/5} {:x 14N, :y 13/10} {:x 14N, :y 7/5} {:x 14N, :y 3/2} {:x 14N, :y 8/5} {:x 14N, :y 17/10} {:x 14N, :y 9/5} {:x 14N, :y 19/10} {:x 14N, :y 2N} {:x 14N, :y 21/10} {:x 14N, :y 11/5} {:x 14N, :y 23/10} {:x 14N, :y 12/5} {:x 14N, :y 5/2} {:x 14N, :y 13/5} {:x 14N, :y 27/10} {:x 14N, :y 14/5} {:x 14N, :y 29/10} {:x 14N, :y 3N})}], :marks [{:type \"symbol\", :from {:data \"9a0d5e20-1dc2-4746-bce5-a070a16decd8\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 1}}, :update {:shape \"circle\", :size {:value 70}, :stroke {:value \"transparent\"}}, :hover {:size {:value 210}, :stroke {:value \"white\"}}}}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain [0 15]} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain [0 4]}], :axes [{:type \"x\", :scale \"x\"} {:type \"y\", :scale \"y\"}]}}"}
;; <=

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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

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
;;; # Maximum value \#38
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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

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
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

;; **
;;; # Infix Calculator \#135
;;; Your friend Joe is always whining about Lisps using the prefix notation for math. Show him how you could easily write a function that does math using the infix notation. Is your favorite language that flexible, Joe? Write a function that accepts a variable length mathematical expression consisting of numbers and the operations +, -, *, and /. Assume a simple calculator that does not do precedence and instead just calculates left to right.
;;; 
;;; __note__ the solutions between the different users are particularly interesting for comparison
;; **

;; @@
;;casadilla
(def casadilla 
  (fn [& stack]
    ((fn recur-calc
       [col acc]
       (if (= 2 (count col))
         ((first col) acc (last col))
         (recur (drop 1 (rest col)) ((first col) acc (nth col 1)))))
     (rest stack) (first stack))))

;;chouser
(def chouser 
  (fn f
    ([a] a)
    ([a b c & m]
     (apply f (b a c) m))))

;;cgrand
(def cgrand
  (fn [x & xs]
    (reduce (fn [x [f y]] (f x y)) x
      (partition 2 xs))))

;;amalloy
(def amalloy
  (fn c [x f y & r]
    ((if r
       #(apply c % r) +)
     (f x y))))

;;noisesmith
(def noisesmith 
  (fn infixr
    [& steps]
    (loop [[a op b & remaining] steps]
      (let [step (op a b)]
        (if (empty? remaining)
          step
          (recur (cons step remaining)))))))

;;test
(= 72 (casadilla 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))
(= 72 (chouser 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))
(= 72 (cgrand 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))
(= 72 (amalloy 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))
(= 72 (noisesmith 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

;; **
;;; #Re-implement Map \#118
;;; 
;;; Map is one of the core elements of a functional programming language. Given a function f and an input sequence s, return a lazy sequence of (f x) for each element x in s.
;;; 
;;; __Special Restrictions:__ map map-indexed mapcat for
;; **

;; @@
;; first thought was simple recursion. blows stack with unit test 3
(def casadilla-recur
  (fn recur-map
    ([f col] (recur-map f col []))
    ([f col acc]
     (if (empty? col)
       acc
       (recur f (rest col) (conj acc (f (first col))))))))

;; casadilla using reductions
(def casadilla
  (fn [f col]
  (drop 1
        (reductions (fn ([a b] (f b))) nil col))))

;;chouser
(def chouser
  (fn l [f [a & m]]
  (lazy-seq
    (cons (f a) (if m (l f m))))))

;;cgrand/amalloy
(def cgramalloy 
  (fn m [f s]
  (lazy-seq
    (when-let [[h & t] (seq s)]
      (cons (f h) (m f t))))))

;;noisesmith
(def noisesmith
  (fn MAP [f s]
  (if (empty? s)
    nil
    (lazy-seq
     (cons (f (first s))
           (MAP f (rest s)))))))

;; easy tests
(= [3 4 5 6 7] (casadilla-recur inc [2 3 4 5 6]))
(= [3 4 5 6 7] (casadilla inc [2 3 4 5 6]))
(= [3 4 5 6 7] (chouser inc [2 3 4 5 6]))
(= [3 4 5 6 7] (cgramalloy inc [2 3 4 5 6]))
(= [3 4 5 6 7] (noisesmith inc [2 3 4 5 6]))

;;stack killer test w/recursion
(= [1000000 1000001]
   (->> (casadilla inc (range))
        (drop (dec 1000000))
        (take 2)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>true</span>","value":"true"}
;; <=

;; @@

;; @@
