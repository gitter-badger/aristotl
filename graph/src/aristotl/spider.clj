(ns aristotl.spider
  "The Spider component crawls encyclopedia pages. The custom handler
  stores the content into ElasticSearch and the metadata into
  Datomic."
  (:require [com.stuartsierra.component :as component]
            [adzerk.env :as env]
            [itsy.core :as itsy]
            [itsy.handlers.elasticsearch :refer [make-es-handler]]
            [net.cgrand.enlive-html :as html]
            [clojure.string :as str]))

(env/def
  ES_URL "http://localhost:9200/")

(defn my-handler [{:keys [url body]}]
  (println url "has a count of" (count body)))

(def es-handler
  ""
  (make-es-handler {:es-url ES_URL
                         :es-index "crawl"
                         :es-type "page"
                         :es-index-settings {:settings
                                             {:index
                                              {:number_of_shards 2
                                               :number_of_replicas 0}}}
                         :http-opts {}}))

(def sources
  "Schema: {<3-letter name> <itsy crawl settings>}"
  {:sep {:url "http://plato.stanford.edu/contents.html"
         :handler es-handler
         :workers 5
         :url-limit -1
         :url-extractor itsy/extract-all ;; FIXME: study itsy/extract-all and make my own implementation ??
         :http-opts {}
         :host-limit true
         :polite? true}})

;; An itsy component
(defrecord Itsy [crawl-settings]
  component/Lifecycle
  (start [component]
    (println "Starting the spider")
    (let [spider (itsy/crawl crawl-settings)]
      (assoc component :spider spider)))

  (stop [component]
    (itsy/stop-workers (:spider component))
    (assoc component :spider nil)))

(defn new-itsy
  [crawl-settings]
  (itsy/crawl crawl-settings))
