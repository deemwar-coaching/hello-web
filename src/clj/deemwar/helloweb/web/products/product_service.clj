(ns deemwar.helloweb.web.products.product-service)
(def products (atom  [{:id 1 :name "shoe" :price 23}]))


(defn list-products []
  @products)

(defn find-product-by-id[input]
   (->> @products
       (filter   #(= input (:id %)))
        first))

(defn add-product [added-product]
  (swap! products  conj  added-product))

(defn update-products-using-id [product-id update-product]
  (swap! products (fn [input-vector]
                    (let [product-with-id (fn [input]
                                            (->> input-vector
                                                 (filter   #(= input (:id %)))))
                          left-over (fn [input]
                                      (->> input-vector
                                           (filter   #(not= input (:id %)))))
                          updated-product (conj (into {} (product-with-id product-id)) update-product)]
                      (conj (vec (left-over product-id)) (into {} updated-product))))))


(defn delete-product [product-id]
  (swap! products (fn [input-vector]
                    (let [product-to-delete (fn [input]
                                              (->> input-vector
                                                   (filter #(not= input (:id %)))))]
                      (product-to-delete product-id)))))


(comment
  (println @products)
  (add-product {:id 2 :name "bag" :price 30})
  (update-products-using-id  1 {:name "purse" :price 100 :review "good"})
(delete-product 2)  
  (list-products)
  (find-product-by-id 3))