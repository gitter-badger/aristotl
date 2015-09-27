(set-env! :source-paths   #{"src" "test"}
          :resource-paths #{"resources" "config"}
          :dependencies   '[[org.clojure/clojure "1.7.0"]
                            [org.clojure/core.typed "0.3.11"]
                            [org.clojure/core.match "0.3.0-alpha4"]
                            [adzerk/boot-test "1.0.4" :scope "test"]

                            [com.stuartsierra/component "0.3.0"]
                            [org.danielsz/system "0.1.9"] ;; also provides reloaded.repl
                            [io.pedestal/pedestal.service "0.4.0"]
                            [io.pedestal/pedestal.jetty   "0.4.0"]
                            
                            [itsy "0.1.1"
                             :exclusions [org.slf4j/slf4j-log4j12]]
                            [clj-http   "2.0.0"]
                            [adzerk/env "0.2.0"]
                            [enlive     "1.1.6"]

                            ;; ElasticSearch
                            [org.elasticsearch/elasticsearch "1.6.0"
                                   :exclusions [org.antlr/antlr-runtime
                                                org.apache.lucene/lucene-analyzers-common
                                                org.apache.lucene/lucene-grouping
                                                org.apache.lucene/lucene-highlighter
                                                org.apache.lucene/lucene-join
                                                org.apache.lucene/lucene-memory
                                                org.apache.lucene/lucene-misc
                                                org.apache.lucene/lucene-queries
                                                org.apache.lucene/lucene-queryparser
                                                org.apache.lucene/lucene-sandbox
                                                org.apache.lucene/lucene-spatial
                                                org.apache.lucene/lucene-suggest
                                                org.ow2.asm/asm
                                                org.ow2.asm/asm-commons]]

                            ;; Datomic
                            [com.datomic/datomic-free "0.9.5302"
                             :exclusions [joda-time
                                          org.slf4j/slf4j-nop
                                          org.slf4j/slf4j-log4j12]]
                            [io.rkn/conformity "0.3.5"
                             :exclusions [com.datomic/datomic-free]]

                            ;; Logging
                            [ch.qos.logback/logback-classic "1.1.3"
                             :exclusions [org.slf4j/slf4j-api]]
                            [org.slf4j/jul-to-slf4j "1.7.12"]
                            [org.slf4j/jcl-over-slf4j "1.7.12"]
                            [org.slf4j/log4j-over-slf4j "1.7.12"]])

(def version "0.0.1-SNAPSHOT")
(task-options! pom {:project 'aristotl
                    :version (str version "-standalone")
                    :description "FIXME: write description"
                    :license {"License Name" "All Rights Reserved"}})

;; == Cider REPL =========================================

(require 'boot.repl)
(swap! boot.repl/*default-dependencies*
       concat '[[cider/cider-nrepl "0.8.2"]])

(swap! boot.repl/*default-middleware*
       conj 'cider.nrepl/cider-middleware)

;; == Datomic =============================================
(load-data-readers!)

(deftask bootstrap
  "Bootstrap the Datomic database"
  []
  (require '[aristotl.database :as db])
  ((resolve 'db/bootstrap!) @(resolve 'aristotl.db/uri)))

;; == Server Tasks =========================================

(require '[reloaded.repl :refer [init start stop go reset]]
         '[aristotl.systems :refer [dev-system]]
         '[system.boot :refer [system run]])

(deftask dev []
  (comp
   (watch :verbose true)
   (speak :theme "ordinance")
   (system :sys #'dev-system :auto-start true :hot-reload true
           :files ["database.clj" "routes.clj" "spider.clj" "service.clj"])
   (repl :server true)))


(deftask build
  "Build my project."
  []
  (comp (aot :namespace '#{aristotl})
        (pom)
        (uber)
        (jar :main 'aristotl)))



