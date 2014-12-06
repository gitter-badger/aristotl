(set-env! :dependencies '[[leiningen-core "2.5.0"]])
(use 'leiningen.core.project)

(eval (read-string (slurp "project.clj")))

(set-env!
 :src-paths (:source-paths project)
 :rsc-paths (:resource-paths project)
 :dependencies (:dependencies project))

(deftask dev-repl
  "development repl"
  []
  (repl :server))

(require '[spider :as s])

(deftask start-spider
  "crawl it"
  []
  (s/run))

