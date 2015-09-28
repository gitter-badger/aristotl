(ns aristotl.systems
  (:require [com.stuartsierra.component :as component]
            [system.core :refer [defsystem]]
            (system.components
             [datomic :refer [new-datomic-db]]
             [elasticsearch :refer [new-elasticsearch-db]])
            (aristotl
             [database :as db]
             [service :refer [new-pedestal api-service]]
             [spider :refer [new-itsy sources]])))

(defn dev-system []
  (component/system-map
   :datomic (new-datomic-db db/uri)
   ;;:pedestal (component/using (new-pedestal api-service) [:datomic])
   :elasticsearch (new-elasticsearch-db [["localhost" 9300]] {"cluster.name" "elasticsearch"})
   :sep-spider (component/using
                  (new-itsy (get sources :sep))
                  [:elasticsearch :datomic])))
