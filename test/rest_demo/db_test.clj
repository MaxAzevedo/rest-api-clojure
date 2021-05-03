(ns rest-demo.db-test
  (:require [clojure.test :refer :all]
            [rest-demo.core :refer :all]
            [rest-demo.db :as db]))

(defn create-db []
  (db/add-person "max" "azevedo")
  (db/add-person "theo" "azevedo")
  (db/add-person "joão" "azevedo"))

(defn setup-db [f]
  (create-db)
  (f)
  (db/clear-db))

(use-fixtures :each setup-db)

(deftest add-person-test
  (testing "Test the add of a person"
    (is (= (db/add-person "pedro" "azevedo") [{:firstname "Max" :lastname "Azevedo"}
                                              {:firstname "Theo" :lastname "Azevedo"}
                                              {:firstname "João" :lastname "Azevedo"}
                                              {:firstname "Pedro" :lastname "Azevedo"}]))))

(deftest get-people-test
  (testing "Test to list all persons"
    (is (= (db/get-people) [{:firstname "Max" :lastname "Azevedo"}
                            {:firstname "Theo" :lastname "Azevedo"}
                            {:firstname "João" :lastname "Azevedo"}]))))

(deftest get-person-by-name-test
  (testing "Test to get a person by name"
    (is (= (db/get-person-by-name "Theo") '({:firstname "Theo" :lastname "Azevedo"})))))

(deftest edit-people-test
  (testing "Test the edition of a person"
    (is (= (db/alter-person "Theo" "Valentin") [{:firstname "Max" :lastname "Azevedo"}
                                                {:firstname "Valentin" :lastname "Azevedo"}
                                                {:firstname "João" :lastname "Azevedo"}]))))

(deftest delete-person-test
  (testing "Test to delete a person"
    (is (= (db/delete-person "João") [{:firstname "Max" :lastname "Azevedo"}
                                      {:firstname "Theo" :lastname "Azevedo"}]))))
