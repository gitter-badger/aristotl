(ns aristotl.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route.definition :refer [defroutes]]
            [com.stuartsierra.component :as component]
            [aristotl.routes :refer [routes]]
            [aristotl.database :as db]
            [adzerk.env :as env]))

;; Dev environment stuff. This will be overridden on prod
(env/def
  HOST "0.0.0.0"
  PORT "8080")

(def service
  {::http/host HOST
   ::http/port (Integer/parseInt PORT)
   ::http/type :jetty
   ::http/join? false
   ::http/resource-path "/public"
   ::http/routes routes})

(defonce server nil)

(defn start-service []
  (db/bootstrap! db/uri)
  (http/start server))

(defn stop-service []
  (http/stop server)
  (alter-var-root #'server (constantly nil)))

(defn restart-service []
  (stop-service)
  (start-service))

(defrecord Pedestal [service-map]
  component/Lifecycle
  (start [component]
    (let [server-instance start-service]
      (assoc component :service server-instance)))
  (stop [component]
    (update-in component [:service] stop-service)))

(defn new-pedestal [service-map]
  (map->Pedestal service-map))
