(ns deemwar.helloweb.env
  (:require
    [clojure.tools.logging :as log]
    [deemwar.helloweb.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[helloweb starting using the development or test profile]=-"))
   :start      (fn []
                 (log/info "\n-=[helloweb started successfully using the development or test profile]=-"))
   :stop       (fn []
                 (log/info "\n-=[helloweb has shut down successfully]=-"))
   :middleware wrap-dev
   :opts       {:profile       :dev
                :persist-data? true}})
