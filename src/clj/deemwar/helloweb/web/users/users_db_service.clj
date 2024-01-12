(ns deemwar.helloweb.web.users.users-db-service)

(defn list-all-users [query-fn]
  (query-fn :list-users {}))

(defn add-new-user [added-user query-fn]
  (query-fn :add-new-user! added-user))

(defn update-user-password [password-to-update user-name query-fn]
  (query-fn :update-user-password! password-to-update {:user-name user-name}))

(defn update-user-role [role-to-update user-name query-fn]
  (query-fn :update-user-role! role-to-update {:user-name user-name}))

(defn delete-user [user-name query-fn]
  (query-fn :delete-user! {:user-name user-name}))