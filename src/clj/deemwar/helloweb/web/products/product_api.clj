(ns deemwar.helloweb.web.products.product-api
  (:require 
   [ring.util.http-response :as http-response]
   [deemwar.helloweb.web.products.product-db-service :as product-db-service]
   [deemwar.helloweb.web.routes.utils :as utils]
     [clojure.tools.logging :as log]
   ))


; it will have api entry points for 
; list-products =>
; find-product-by-id
; add-product
; delete-product-by-id

;GET,POST,PUT,DELETE


; GET is for all reading operations
; POST is for all inserts
;put/patch is for all updates
;Delete is for all deletions

(defn product-by-id [req]
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
        id (Integer/parseInt (get-in req [:path-params :id]))]
    (http-response/ok
   {:products (product-db-service/product-by-id id query-fn)})))

(defn list-products [req]
   (let [query-fn (get-in req [:reitit.core/match :data :query-fn])  ]
  (http-response/ok 
   {:products (product-db-service/list-products query-fn) })))

 (defn add-product [req]
     (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
           product-request (req :body-params) ]
       (http-response/ok
       {:added-product (product-db-service/add-product product-request query-fn)})))

 

(defn update-product [req]
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn]) 
        id (Integer/parseInt (get-in req [:path-params :id]))
        price (get (req :body-params) :price)
        count (get (req :body-params) :count)]
    (if (= nil price)
      (http-response/ok
     {:updated-product (product-db-service/update-product-count id count query-fn)})
      (http-response/ok
       {:updated-product (product-db-service/update-product-price id  price query-fn)}))
       )) 




(defn delete-product [req]
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
        id (Integer/parseInt (get-in req [:path-params :id]))]
    (http-response/ok
     {:products (product-db-service/delete-product id query-fn)})))


