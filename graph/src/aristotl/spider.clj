(ns aristotl.spider
  "The Spider component crawls encyclopedia pages. The custom handler
  stores the content into ElasticSearch and the metadata into
  Datomic."
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [adzerk.env :as env]
            [itsy.core :as itsy]
            [itsy.handlers.elasticsearch :refer [make-es-handler]]
            [net.cgrand.enlive-html :as html]
            [clojure.string :as str]))

(env/def
  ES_URL "http://localhost:9200/")

(defn log-handler [{:keys [url body]}]
  (log/info url "has a count of" (count body)))

(defn elasticsearch-handler [es-component]
  (make-es-handler {:es-url (:es-url es-component)}))

;; TODO
(defn datomic-handler [{:keys [url body]}]
  nil)

(defn master-handler
  "Comp handlers for ElasticSearch and Datomic.
   Handlers take a map with :url and :body keys"
  [{:keys [url body] :as m}]
  (log/debug "Handling" url)
  (comp
   (datomic-handler m)
   (elasticsearch-handler m)))

(def sources
  "Schema: {<3-letter name> <itsy crawl settings>}"
  {:sep {:url "http://plato.stanford.edu/contents.html"
         :handler elasticsearch-handler
         :workers 5
         :url-limit -1
         :url-extractor itsy/extract-all ;; FIXME: study itsy/extract-all and make my own implementation ??
         :http-opts {}
         :host-limit true
         :polite? true}})

;; An itsy component
(defrecord Itsy [crawl-settings spider elasticsearch datomic]
  component/Lifecycle
  (start [this]
    (log/info "Starting Itsy component")
    (assoc this :spider (itsy/crawl crawl-settings)))
  (stop [this]
    (log/info "Stopping Itsy component")
    (itsy/stop-workers (:spider this))
    (assoc this :spider nil)))

(defn new-itsy
  [crawl-settings]
  (map->Itsy {:crawl-settings crawl-settings}))
