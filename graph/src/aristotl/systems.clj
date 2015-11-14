(ns aristotl.systems
  (:require [com.stuartsierra.component :as component]
            [system.core :refer [defsystem]]
            (system.components
             [datomic :refer [new-datomic-db]])
            (aristotl
             [database :as db]
             [service :refer [new-pedestal api-service]]
             [spider :refer [new-itsy sources]]))
  (:import [org.elasticsearch.client.transport TransportClient]
           [org.elasticsearch.common.transport InetSocketTransportAddress]
           [org.elasticsearch.common.settings Settings]))

(defrecord Elasticsearch [addresses settings client]
  component/Lifecycle
  (start [component]
    (let [builder (.. (Settings/settingsBuilder)
                      (put ^java.util.Map settings))
          client (doto (TransportClient/builder)
                   (.addTransportAddresses (into-array addresses)))]
      (assoc component :client client)))
  (stop [component]
    (when client
      (.close ^TransportClient client))
    (assoc component :client nil)))

(defn new-elasticsearch-db
  ([addresses]
   (new-elasticsearch-db addresses {}))
  ([addresses settings]
   (map->Elasticsearch {:addresses (for [[^String host ^int port] addresses]
                                     (InetSocketTransportAddress. host port))
                        :settings settings})))

(defn dev-system []
  (component/system-map
   :datomic (new-datomic-db db/uri)
   ;;:pedestal (component/using (new-pedestal api-service) [:datomic])
   :elasticsearch (new-elasticsearch-db [["localhost" 9300]] {"cluster.name" "elasticsearch"})
   :sep-spider (component/using
                  (new-itsy (get sources :sep))
                  [:elasticsearch :datomic])))
