(ns rest-demo.service
  (:require [clojure.data.json :as json]
            [rest-demo.db :as db]))

(defn get-body [req param-key]
  (get (:body req) param-key))

(defn get-params [req param-key]
  (get (:params req) param-key))

(defn people-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (str (json/write-str (db/get-people)))})

(defn get-person [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (-> (let [fun-get-params (partial get-params req)]
                  (str (json/write-str
                         (db/get-person-by-name (fun-get-params :firstname))))))})

(defn add-person-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (-> (let [func-get-body (partial get-body req)]
                  (str (json/write-str
                         (db/add-person (func-get-body "firstname")
                                        (func-get-body "lastname"))))))})

(defn alter-person-handler [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (-> (let [func-get-body (partial get-body req)
                      func-get-params (partial get-params req)]
                  (str (json/write-str
                         (db/alter-person
                           (func-get-params :firstname)
                           (func-get-body "firstname"))))))})

(defn delete-person-handler [req]
  {:status  204
   :headers {"Content-Type" "text/json"}
   :body    (let [fun-get-params (partial get-params req)]
              (str (json/write-str (db/delete-person (fun-get-params :firstname)))))})