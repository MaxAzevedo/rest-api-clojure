(ns rest-demo.db
  (:require [clojure.string :as str]))

(def people-collection (atom []))

(defn add-person [first-name last-name]
  (swap! people-collection conj {:firstname (str/capitalize first-name) :lastname (str/capitalize last-name)}))

(defn get-people [] @people-collection)

(defn clear-db [] (reset! people-collection []))

(defn get-person-by-name [first-name]
  (filter #(= (:firstname %) first-name) @people-collection))

(defn delete-person [first-name]
  (swap! people-collection
         (fn [peoples]
           (into [] (remove #(= (:firstname %) first-name) peoples)))))

(defn alter-person [old-first-name new-first-name]
  (swap! people-collection
         (fn [peoples]
           (map (fn [people]
                  (update people :firstname #(if (= old-first-name %) new-first-name %))) peoples))))
