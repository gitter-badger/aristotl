(ns aristotl.systems
  (:require [com.stuartsierra.component :as component]
            [system.core :refer [defsystem]]
            (system.components
             [datomic :refer [new-datomic-db]]
             [elasticsearch :refer [new-elasticsearch-db]])
            (aristotl
             [database :as db]
             [service :refer [new-pedestal service]]
             [spider :refer [new-itsy sources]])))

(defsystem dev-system
  [:datomic (new-datomic-db db/uri)
   :pedestal (new-pedestal service)
   :sep-spider (new-itsy (get sources :sep))])
