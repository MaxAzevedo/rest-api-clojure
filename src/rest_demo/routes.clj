(ns rest-demo.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [rest-demo.service :refer :all]))

(defroutes app-routes
           (GET "/people" [] people-handler)
           (GET "/people/:firstname" [] get-person)
           (POST "/people" [] add-person-handler)
           (PUT "/people/:firstname" [] alter-person-handler)
           (DELETE "/people/:firstname" [] delete-person-handler)
           (route/not-found "Error, page not found!"))
