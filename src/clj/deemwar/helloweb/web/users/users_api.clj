(ns deemwar.helloweb.web.users.users-api
 ( :require
  [ring.util.http-response :as http-response]  
  [deemwar.helloweb.web.users.users-db-service :as users-db-service])
  )

 
 ; create ,update ,add and delete the users table

(defn list-all-users [req]
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])] 
       (http-response/ok
        {:users (users-db-service/list-all-users query-fn)})))

 (defn add-new-user [req]
   (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
         added-user (req :body-params)]
     (http-response/ok
      {:add-product (users-db-service/add-new-user added-user query-fn)})))
 
(defn update-user [req]
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
        id (Integer/parseInt (get-in req [:path-params :id]))
       password (get (req :body-params) :password)
      role (get (req :body-params) :role)]
    (http-response/ok
       {:updated-product (users-db-service/update-user id password role   query-fn)})
        ))


(defn delete-user [req]
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
        id (Integer/parseInt (get-in req [:path-params :id]))]
    (http-response/ok
      {:products (users-db-service/delete-user id query-fn)})))