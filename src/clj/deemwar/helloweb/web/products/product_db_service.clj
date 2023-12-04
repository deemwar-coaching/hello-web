(ns deemwar.helloweb.web.products.product-db-service
  (:require   
   [deemwar.helloweb.web.routes.utils :as utils]
   [clojure.tools.logging :as log]))

 (defn list-products [query-fn]
       (query-fn :list-all-products {}))

  (defn product-by-id [id , query-fn]    
        (query-fn :product-by-id  {:id id}))

(defn add-product [ product-to-be-added query-fn]
  (query-fn :add-product! product-to-be-added))
 
 (defn update-product-price [id price-to-update query-fn]
   (query-fn :update-product-price! price-to-update { :id id}) )
 
  (defn update-product-count [id count-to-update query-fn]
   (query-fn :update-product-count! count-to-update {  :id id}))
 
 (defn delete-product [id  query-fn]
   (query-fn :product-by-id {:id id}))
 

  