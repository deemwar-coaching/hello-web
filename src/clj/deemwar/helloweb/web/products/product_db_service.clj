(ns deemwar.helloweb.web.products.product-db-service
  (:require
   [ring.util.http-response :as http-response]
   [deemwar.helloweb.web.routes.utils :as utils]
   [clojure.tools.logging :as log]))

 (defn list-products [req]
    (let [query-fn (get-in req [:reitit.core/match :data :query-fn])]
      (http-response/ok (query-fn :list-all-products {}))))

  (defn product-by-id [req]
    (let [id (Integer/parseInt (get-in req [:path-params :id]))
          query-fn (get-in req [:reitit.core/match :data :query-fn])]
      (http-response/ok   (query-fn :product-by-id {:id id}))))

(defn add-product [req]

  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
        name (get  (req :body-params) :name)
        price (get  (req :body-params) :price)
        category (get (req :body-params) :category)
        count (get (req :body-params) :count)]
    (http-response/ok
     (query-fn :add-product! {:name name :price price :category category :count count}))))
  
(defn update-product [req] 
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
        id (Integer/parseInt (get-in req [:path-params :id]))
        name (get  (req :body-params) :name)
        price (get  (req :body-params) :price)
        category (get (req :body-params) :category)
        count (get (req :body-params) :count)]
    (http-response/ok
     (query-fn :update-product! {:name name :price price :category category :count count :id id}))))

  (defn delete-product [req]
    (let [id (Integer/parseInt (get-in req [:path-params :id]))
          query-fn (get-in req [:reitit.core/match :data :query-fn])]
      (http-response/ok (query-fn :delete-product! {:id id}))))      