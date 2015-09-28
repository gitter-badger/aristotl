(ns aristotl.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [aristotl.routes :refer [routes]]
            [aristotl.database :as db]
            [adzerk.env :as env]))

;; Dev environment stuff. This will be overridden on prod
(env/def
  HOST "0.0.0.0"
  PORT "8080")

(def api-service
  {::http/host HOST
   ::http/port (Integer/parseInt PORT)
   ::http/type :jetty
   ::http/join? false
   ::http/resource-path "/public"
   ::http/routes routes})

(defrecord Pedestal [config datomic]
  component/Lifecycle

  (start [component]
    (let [service (http/create-server config)]
      (log/info "Starting Pedestal component")
      ;;(db/bootstrap! db/uri)
      (http/start service)
      (assoc component :service service)))

  (stop [component]
    (log/info "Stopping Pedestal component")
    (update-in component [:service] http/stop)))

(defn new-pedestal [config]
  (map->Pedestal config))
