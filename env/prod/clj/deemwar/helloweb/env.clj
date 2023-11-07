(ns deemwar.helloweb.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[helloweb starting]=-"))
   :start      (fn []
                 (log/info "\n-=[helloweb started successfully]=-"))
   :stop       (fn []
                 (log/info "\n-=[helloweb has shut down successfully]=-"))
   :middleware (fn [handler _] handler)
   :opts       {:profile :prod}})
