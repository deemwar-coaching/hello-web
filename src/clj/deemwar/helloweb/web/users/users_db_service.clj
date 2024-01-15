(ns deemwar.helloweb.web.users.users-db-service)

(defn list-all-users [query-fn]
  (query-fn :list-users {}))

(defn add-new-user [added-user query-fn]
  (query-fn :add-new-user! added-user))

(defn update-user [ id password-to-update role-to-update query-fn]
 (if (= nil role-to-update)
   (query-fn :update-user-password!  {:password password-to-update :id id})
   (query-fn :update-user-role!  {:role role-to-update :id id})))

 

(defn delete-user [id query-fn]
  (query-fn :delete-user! {:id 6}))

