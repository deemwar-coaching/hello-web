(ns deemwar.helloweb.web.products.product-db-service
  (:require   
   [deemwar.helloweb.web.routes.utils :as utils]
   [clojure.tools.logging :as log]))

 (defn list-products [query-fn]
       (query-fn :list-all-products {}))

  (defn product-by-id [id , query-fn]    
        (query-fn :product-by-id {:id id}))

(defn add-product [ product-to-be-added query-fn]
  (log/info product-to-be-added)
     (query-fn :add-product! product-to-be-added))
  