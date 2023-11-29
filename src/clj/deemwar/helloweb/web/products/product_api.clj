(ns deemwar.helloweb.web.products.product-api
  (:require 
   [ring.util.http-response :as http-response]
   [deemwar.helloweb.web.products.product-service :as products]
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
  (let [id (Integer/parseInt (get-in req [:path-params :id]))]
    (http-response/ok
   {:products (products/find-product-by-id id)})))

(defn list-products []
  (http-response/ok 
   {:products (products/list-products) }))

 (defn add-product [req]
   (products/add-product (req :body-params))
   (http-response/ok
    {:products  (req :body-params)}))


(defn delete-product [req]
  
  (let [id (Integer/parseInt (get-in req [:path-params :id]))]
    (products/delete-product id)
    (http-response/ok
     {:products   (products/find-product-by-id id)})))


(defn update-product [req]
 (let [id (Integer/parseInt (get-in req [:path-params :id]))
       product-to-update (req :body-params)]   
   (products/update-products-using-id id product-to-update)
  (http-response/ok
    {:products   (products/find-product-by-id id) })))


