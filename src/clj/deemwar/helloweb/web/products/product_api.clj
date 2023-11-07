(ns deemwar.helloweb.web.products.product-api
  (:require 
   [ring.util.http-response :as http-response]))


; it will have api entry points for 
; list-products
; find-product-by-id
; add-product
; delete-product-by-id

;GET,POST,PUT,DELETE


; GET is for all reading operations
; POST is for all inserts
;put/patch is for all updates
;Delete is for all deletions



(defn list-products[req]
  
  (http-response/ok
  {:products [{:id 1}]}))

(defn product-by-id[req]
  
  (http-response/ok
  {:products [{:id 1}]}))

(defn add-product[req]
  
  ; to get post params
  (println (req :body-params))
  (http-response/ok
    {:products [{:id 1 :name "added product"}]}))


(defn delete-product[req]

  (println (req :path-params))

  (http-response/ok
    {:products [{:id 1 :name "deleted product"}]}))


(defn update-product[req]
    (println (req :body-params))
  (println (req :path-params))
  (http-response/ok
    {:products [{:id 1 :name "update product"}]}))


