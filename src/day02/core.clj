(ns day02.core
  (:require [clojure.string :as str]))

(def sample
  "forward 5
down 5
forward 8
up 3
down 8
forward 2")


(defn parse-line [s]
  (let [[d v] (str/split s #" ")]
    [(keyword d) (parse-long v)]))

(defn parse [input]
  (map parse-line (str/split-lines input)))

(def parsed-data (parse sample))

parsed-data


(defn move [[x y] [dir amount]]
  (case dir
    :forward [(+ x amount) y]
    :down    [x (+ y amount)]
    :up      [x (- y amount)]
    [x y]))



(reduce move [0 0] parsed-data)

(defn solve [input]
  (->> input
       (parse)
       (reduce move [0 0])
       (apply *)))

(solve (slurp "inputs/day02.txt"))

(defn move2 [[x y aim] [dir amount]]
  (case dir
    :forward [(+ x amount) (+ y (* aim amount)) aim]
    :down    [x y (+ aim amount)]
    :up      [x y (- aim amount)]
    [x y]))

(defn solve2 [input]
  (->> input
       (parse)
       (reduce move2 [0 0 0])
       (take 2)
       (apply *)))

(solve2 sample)
(solve2 (slurp "inputs/day02.txt"))
