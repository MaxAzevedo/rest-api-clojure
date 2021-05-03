(ns rest-demo.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :refer [wrap-json-body]]
            [rest-demo.service :refer :all]
            [rest-demo.routes :refer :all])
  (:gen-class))

(defn -main
  "This is our main entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    ; Run the server with Ring.defaults middleware
    (server/run-server (->(wrap-defaults #'app-routes api-defaults)
                          (wrap-json-body)) {:port port})
    ; Run the server without ring defaults
    ;(server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))
