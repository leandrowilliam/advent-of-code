(ns day03.core
  (:require [clojure.string :as str]))

(def sample
  "00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010")

(defn greater [{x \0, y \1}]
  (if (> x y) \0 \1))

(defn lower [{x \0, y \1}]
  (if (<= x y) \0 \1))

(defn gamma [input]
  (->> input
       (str/split-lines)
       (apply map vector)
       (map frequencies)
       (map greater)
       (str/join)))

(defn epsilon [gamma]
  (str/join (map {\0 \1
                  \1 \0} gamma)))

(defn solve [input]
  (let [gamma (gamma input)
        epsilon (epsilon gamma)]
    (* (Integer/parseInt gamma 2)
       (Integer/parseInt epsilon 2))))

(solve sample)

(solve (slurp "inputs/day03.txt"))

;; part 2
(->> sample
     (str/split-lines)
     (apply map vector)
     (map frequencies)
     #_(map greater)
     #_(str/join))



(defn moda [coll i]
  (->> coll
       (map #(nth % i))
       (frequencies)))

(defn oxygen [coll i]
  (greater (moda coll i)))

(defn co2 [coll i]
  (lower (moda coll i)))

(defn filter-by-ith [i moda coll]
  (->> coll
       (filter #(= (nth % i) moda))))



(loop [coll (str/split-lines sample)
       i 0]
  (if (= 1 (count coll))
    (first coll)
    (recur (filter-by-ith i (oxygen coll i) coll)
           (inc i))))

(loop [coll (str/split-lines sample)
       i 0]
  (if (= 1 (count coll))
    (first coll)
    (recur (filter-by-ith i (co2 coll i) coll)
           (inc i))))

(defn calculate [coll criteria]
  (loop [coll coll
         i 0]
    (if (= 1 (count coll))
      (first coll)
      (recur (filter-by-ith i (criteria coll i) coll)
             (inc i)))))
(calculate (str/split-lines sample) co2)

(defn solve2 [input]
  (let [coll (str/split-lines input)
        oxygen (calculate coll oxygen)
        co2 (calculate coll co2)]
    (* (Integer/parseInt oxygen 2)
       (Integer/parseInt co2 2))))

(solve2 sample)

(solve2 (slurp "inputs/day03.txt"))



