(ns aristotl.db
  "A common namespace for database interactions."
  :require [clojure.java.jdbc :as jdbc]
           [yesql.core :refer [defquery]]
           [environ.core :refer [env])

;; Supplied by the host environment, the db url takes the basic form of:
;;    postgres://user:password@host:port/database
(def ^:dynamic *db-url* (env :database_url))

(defn- make-subname
  "Arguments: 'local' for development, 'remote' for production."
  [x]
  (if (= x "remote")
    (str "postgres://" *host* ":" *port* "/" *database*)
    (if (= x "local")
      (str "//" *host* ":" *port* "/" *database*)
      (thow (Exception.
             "You need to include either 'local' or 'remote' in `make-subname`.")))))


(def dp-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname *db-url*})


(comment
  ;; create SQLs
  (defquery create-citations-table "common/create-citations-table.sql")
  (defquery create-article-table   "common/create-article-table.sql")
  (defquery create-ref-table       "common/create-ref-table.sql")

  ;; select SQLs
  (defquery select-article "common/select-article.sql")
  (defquery select-ref     "common/select-ref.sql")

  ;; find SQLs
  (defquery find-citations-for-article "common/find-citations-for-article.sql")
)


(defn make-queries-from-dirs
  "Takes a variable number of directory paths (which are presumably in the classpath)
  as arguments. For each path, finds the .sql files and calls `defquery` on each,
  turning the filename (without the extension) into the query name. Does not walk
  subdirectories for more .sql files."
  ([src & rest] (map #(make-queries %) (cons src rest)))
  ([src] (let [dir     (clojure.java.io/file src)
               files   (filter #(.isFile %) (file-seq dir))
               names   (map #(.getName %) files)
               sqls    (filter #(re-matches #".*\.sql$" %) names)
               symbols (map #(symbol %) sqls)]
           (loop [i 0
                  c (count files)
                  queries []]
             (if (>= i c)
               (map #(defquery (first %) (second %)) queries)
               (recur (inc i)
                      (count sqls)
                      (conj queries (nth symbols i) (nth sqls i))))))))

(make-queries-from-dirs "common/sql")
