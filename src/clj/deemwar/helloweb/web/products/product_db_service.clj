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
 
 (defn update-product  [id price-to-update count-to-update query-fn]
  ( if (= nil count-to-update)
   (query-fn :update-product-price!  {:price price-to-update :id id})
   (query-fn :update-product-count!  {:count count-to-update :id id})
   ) )
 
   

 (defn delete-product [id  query-fn]
   (query-fn :delete-product! {:id id}))
 
  
 

  