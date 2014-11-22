(set-env!
 :src-paths #{"server"}
 :rsc-paths #{"common"}
 :dependencies (read-string (slurp "deps.edn")))

(comment
  (require '[spider :as s])

  (deftask start-spider
    "crawl it"
    (s/run))
)
