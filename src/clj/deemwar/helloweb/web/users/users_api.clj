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
          username (get-in req [:body-params :username])
         password   (hashers/derive (get-in req [:body-params :password] ))
         role (get-in req [:body-params :role])]
     (http-response/ok
      {:add-product (users-db-service/add-new-user  username password role query-fn)})))
 
(defn update-user [req]
  (let [query-fn (get-in req [:reitit.core/match :data :query-fn])
        id (Integer/parseInt (get-in req [:path-params :id]))
       password (get (req :body-params) :password)
      role (get (req :body-params) :role)]
    (http-response/ok
       {:updated-product (users-db-service/update-user id password role   query-fn)})
        ))

(defn login-user [req]
 ( let [query-fn (get-in req [:reitit.core/match :data :query-fn])
       username (get-in req [:body-params :username])
        entered-password (get-in req [:body-params :password])
        password (get  (users-db-service/find-user username query-fn) :password)]
   (if (= (hashers/check entered-password password) true )
     (http-response/ok
       {:user-logged-in ( users-db-service/logged-in-user username query-fn)})
     (http-response/ok
       {:login-failed "401 Unauthorized"  }))))

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