(ns deemwar.helloweb.web.users.users-db-service)

(defn list-all-users [query-fn]
  (query-fn :list-users {}))

(defn add-new-user [username password role query-fn]
  (query-fn :add-new-user!  {:username username :password password :role role} ))

(defn update-user [ id password-to-update role-to-update query-fn]
 (if (= nil role-to-update)
   (query-fn :update-user-password!  {:password password-to-update :id id})
   (query-fn :update-user-role!  {:role role-to-update :id id})))

(defn find-user [username query-fn]
  (query-fn :find-user! {:username username}))
(defn logged-in-user [username query-fn]
  (query-fn :logged-in-user! {:username username}))
 (defn encrypt-password [id password query-fn]
   (query-fn :encrypt-password! {:password password :id id}))

(defn delete-user [id query-fn]
  (query-fn :delete-user! {:id id}))

