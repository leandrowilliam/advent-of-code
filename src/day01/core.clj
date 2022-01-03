(ns day01.core
  (:require [clojure.string :refer [split-lines]]))

(def sample [199 200 208 210 200 207 240 269 260 263])


(defn solve1 [input]
  (first (reduce (fn [[count prev] current]
                   [(if (> current prev) (inc count) count) current])
                 [0 (first input)]
                 input)))
sample
(count (filter (fn [[a b]]
                 (> b a)) (partition 2 1 sample)))
(count (filter #(apply < %) (partition 2 1 sample)))

(defn solve2 [coll]
  (->> coll
       (partition 2 1)
       (filter #(apply < %))
       (count)))





(solve1 sample)


(solve1 (map parse-long
             (split-lines
              (slurp "inputs/day01.txt"))))


(->> (slurp "inputs/day01.txt")
     (split-lines)
     (map parse-long)
     (solve2))



;; part II



(defn solve3 [coll]
  (->> coll
       (partition 2 1)
       (filter #(apply < %))
       (count)))

(defn suma [x]
  (apply + x))

(suma [1 2 3])

(map #(apply + %) (partition 3 1 sample))

(map (partial apply +) (partition 3 1 sample))

;; (count (filter (partial apply <) (map (partial apply +) (partition 3 1 sample))))
(defn solve4 [input]
  (->> input
       (partition 3 1)
       (map (partial apply +))
       (solve2)))



(->> (slurp "inputs/day01.txt")
     (split-lines)
     (map parse-long)
     (solve4))

