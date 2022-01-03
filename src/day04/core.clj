(ns day04.core
  (:require [clojure.string :as str]))

(def sample-input (slurp "src/day04/sample.txt"))

(defn parse-line [s]
  (->> s
       (re-seq #"\d+")
       (map parse-long)))

(defn parse-body [body]
  (->> body
       (partition-by empty?)
       (remove #(= (count %) 1))
       (map #(map parse-line %))))

(defn parse [input]
  (let [[header & body] (str/split-lines input)]
    [(parse-line header) (parse-body body)]))

(defn mark [n board]
  (map #(replace {n nil} %) board))

(defn bingo? [board]
  (let [cols (apply map vector board)
        all (concat board cols)]
    (some #(every? nil? %) all)))

(defn score [board last-number]
  (->> board
       flatten
       (remove nil?)
       (apply +)
       (* last-number)))

(defn draw [boards n]
  (map #(mark n %) boards))

(defn solve [input]
  (let [[header boards] (parse input)]
    (loop [boards boards
           [x & xs] header
           n x]
      (if-let [bingo-board (first (filter bingo? boards))]
        (score bingo-board n)
        (recur (draw boards x) xs x)))))

(solve (slurp "inputs/day04.txt"))





