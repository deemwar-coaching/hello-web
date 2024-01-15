(ns deemwar.helloweb.web.users.users-api
 ( :require
  [ring.util.http-response :as http-response]  
  [deemwar.helloweb.web.users.users-db-service :as users-db-service]
   [buddy.hashers :as hashers] 
    [clojure.tools.logging :as log])
  )

 
 ; create ,update ,add and delete the users table

(defn list-all-users [req]
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])] 
       (http-response/ok
        {:users (users-db-service/list-all-users query-fn)})))

 (defn add-new-user [req]
   (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
          user_name (get-in req [:body-params :user_name])
         password   (hashers/derive (get-in req [:body-params :password] ))
         role (get-in req [:body-params :role])]
     (http-response/ok
      {:add-product (users-db-service/add-new-user  user_name password role query-fn)})))
 
(defn update-user [req]
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
        id (Integer/parseInt (get-in req [:path-params :id]))
       password (get (req :body-params) :password)
      role (get (req :body-params) :role)]
    (http-response/ok
       {:updated-product (users-db-service/update-user id password role   query-fn)})
        ))

(defn encrypt-password [req]
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
        id (Integer/parseInt (get-in req [:path-params :id]))
        password   (get (req :body-params) :password)  
        encrypted-password (hashers/derive password)]
    (http-response/ok
      {:encrypted-password (users-db-service/encrypt-password id encrypted-password query-fn)})))
 


(defn delete-user [req]
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
        id (Integer/parseInt (get-in req [:path-params :id]))]
    (http-response/ok
      {:products (users-db-service/delete-user id query-fn)})))