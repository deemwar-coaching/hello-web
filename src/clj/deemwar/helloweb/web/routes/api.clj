(ns deemwar.helloweb.web.routes.api
  (:require
    [deemwar.helloweb.web.controllers.health :as health]
    [deemwar.helloweb.web.middleware.exception :as exception]
    [deemwar.helloweb.web.middleware.formats :as formats]
    [deemwar.helloweb.web.products.product-api :as product-api]
    [integrant.core :as ig]
    [reitit.coercion.malli :as malli]
    [reitit.ring.coercion :as coercion]
    [reitit.ring.middleware.muuntaja :as muuntaja]
    [reitit.ring.middleware.parameters :as parameters]
    [reitit.swagger :as swagger]
   [ring.util.http-response :as http-response]
   ))

(def route-data
  {:coercion   malli/coercion
   :muuntaja   formats/instance
   :swagger    {:id ::api}
   :middleware [;; query-params & form-params
                parameters/parameters-middleware
                  ;; content-negotiation
                muuntaja/format-negotiate-middleware
                  ;; encoding response body
                muuntaja/format-response-middleware
                  ;; exception handling
                coercion/coerce-exceptions-middleware
                  ;; decoding request body
                muuntaja/format-request-middleware
                  ;; coercing response bodys
                coercion/coerce-response-middleware
                  ;; coercing request parameters
                coercion/coerce-request-middleware
                  ;; exception handling
                exception/wrap-exception]})

 (defn hello-handler  [req]
 
   (http-response/ok
    {:message "hi"
     :name  (get-in req [:params :msg])}))
 
 (defn square-handler  [req]
   (let [number (Integer/parseInt (get-in req [:params :number]))]
     (http-response/ok
      {:number (get-in req [:params :number])
       :square-of-the-number   (* number number)})))
 
 (defn addition-handler [req]
   (let [num-1 (Integer/parseInt (get-in req [:params :number1]))
         num-2 (Integer/parseInt (get-in req [:params :number2]))]
     (http-response/ok
      {:number-1 (get-in req [:params :number1])
       :number-2 (get-in req [:params :number2])
       :added-value (+ num-1 num-2)})))
 
 (defn is-prime? [num]
   (reduce (fn [acc current]
             (-> (not= 0 (mod num current))
                 (and acc))) true (range 2 num)))
 
 (defn only-prime [x]
   (when (is-prime? x)
     (println x)))
 
 (filter only-prime (range 1 100))
 (defn prime-finder [req]
   (let [num-1 (Integer/parseInt (get-in req [:params :number]))]
     (if (= true (is-prime? num-1))
       (http-response/ok
        {:number  (get-in req [:params :number])
         :result   "number is prime"})
       (http-response/ok
        {:number  (get-in req [:params :number])
         :result "number is not prime"}))))
 
 (defn prime-list-handler [req]
   (let [num (Integer/parseInt (get-in req [:params :number]))
         result (filter is-prime? (range 1 num))]
     (http-response/ok
      {:prime-numbers-up-to  (get-in req [:params :number])
       :result  result})))
 
 (defn average [num]
   (-> (reduce + (range 1 (inc num)))
       (/ num)))
 
 (defn average-handler [req]
   (let [num (Integer/parseInt (get-in req [:params :number]))
         result (average num)]
     (http-response/ok
      {:number (get-in req [:params :number])
       :average-of-the-number result})))
 
 (defn find-leap [num]
   (if (->> (and (not= 0 (mod num 100))
                 (= 0 (mod num 4)))
            (or (= 0 (mod num 400))))
     true
     false))
 
 
 (defn leap-finder [req]
   (let [num (Integer/parseInt (get-in req [:params :number]))]
     (if (= true (find-leap num))
       (http-response/ok
        {:year (get-in req [:params :number])
         :result "year is leap"})
       (http-response/ok
        {:year (get-in req [:params :number])
         :result "year is not leap"}))))
 
 
 (defn conversion-of-F-to-C [degrees]
   (-> (- degrees 32)
       (* 5)
       (/ 9)))
 (defn conversion-of-C-to-F [degrees]
   (-> (* degrees 9)
       (/ 5)
       (+ 32)))
 (defn temperature-convertor [degree unit]
   (def F) (def C)
   (cond
     (= unit F) (conversion-of-F-to-C degree)
     (= unit C)   (conversion-of-C-to-F degree)))
 
 (temperature-convertor 250 C)
 
 (defn temperature-handler [req]
   (let [temperature (Integer/parseInt (get-in req [:params :temperature]))
         unit  (get-in req [:params :unit])]
 
     (if (= unit "F")
       (http-response/ok
        {:msg "converting from fahrenheit to celsius"
         :temperature (get-in req [:params :temperature])
         :unit (get-in req [:params :unit])
         :result (conversion-of-F-to-C temperature)})
       (http-response/ok
        {:msg "converting from celsius to fahrenheit"
         :temperature (get-in req [:params :temperature])
         :unit (get-in req [:params :unit])
         :result  (conversion-of-C-to-F temperature)}))))
 
 (defn reverse-string [x]
   (apply str (reverse x)))
 
 (defn str-reverse-handler [req]
   (let [string (get-in req  [:params :string])
         result (reverse-string string)]
     (http-response/ok
      {:string (get-in req [:params :string])
       :reversed-string result})))
 
 (defn fib [num]
   (reduce (fn [{:keys [a b c]} , curr]
             (let [added-value (+ a b)
                   result     {:a b :b added-value :c added-value}]  result))   {:a 0 :b 1 :c 0}
           (range 2 (inc num))))
 
 
 
 (map :b (map fib (range 1 100)))
 
 
 
 
 
 (defn fibanocii-handler [req]
   (let [num (Integer/parseInt (get-in req [:params :number]))
         result   (map :b (map fib (range 1 num)))]
     (http-response/ok
      {:number (get-in req [:params :number])
       :fibonacci-series result})))
 
 
 
 
 
 
 ;; Routes
 (defn api-routes [_opts]
   [["/swagger.json"
     {:get {:no-doc  true
            :swagger {:info {:title "deemwar.helloweb API"}}
            :handler (swagger/create-swagger-handler)}}]
    ["/health"
     {:get health/healthcheck!}]
    ["/hello"
     {:get hello-handler}]
    ["/square"
     {:get square-handler}]
    ["/add"
     {:get addition-handler}]
    ["/prime"
     {:get prime-finder}]
    ["/prime-list"
     {:get prime-list-handler}]
    ["/average"
     {:get average-handler}]
    ["/leap"
     {:get leap-finder}]
    ["/convert-temperature"
     {:get temperature-handler}]
    ["/string-reverse"
     {:get str-reverse-handler}]
    
    
    ["/products/:id"
     {:get product-api/product-by-id,
     :put product-api/update-product
     :delete product-api/delete-product}]
["/products"
 {:get product-api/list-products
  :post product-api/add-product
  }
 ]
    

    ["/fibonacci"
     {:get (fn[req]
             (fibanocii-handler req)
             )}]])
 
 

 (derive :reitit.routes/api :reitit/routes)
 
 (defmethod ig/init-key :reitit.routes/api
   [_ {:keys [base-path]
       :or   {base-path ""}
       :as   opts}]
   [base-path route-data (api-routes opts)])