(ns aristotl.db
  "Datomic bootstrap and Datomic + Pedestal interceptor"
  (:require [datomic.api :as d]
            [io.pedestal.interceptor :refer [interceptor]]
            [io.rkn.conformity :as c]
            [environ.core :refer [env]))


(defonce uri (get env :datomic-uri (str "datomic:mem://" (d/squuid))))

(defn bootstrap!
  "Bootstrap schema into the database."
  [uri]
  (d/create-database uri)
  (let [conn (d/connect uri)]
    ;; TODO:     v Create resources/<your-schema.edn> and add "<your-schema>.edn" to this vector
    (doseq [rsc [ ]]
      (let [norms (c/load-schema-rsc rsc)]
        (c/ensure-conforms conn norms)))))

 (def insert-datomic
   "Provide a Datomic conn and db in all incoming requests"
   (interceptor
     {:name ::insert-datomic
      :enter (fn [context]
               (let [conn (d/connect uri)]
                 (-> context
                     (assoc-in [:request :conn] conn)
                     (assoc-in [:request :db] (d/db conn)))))}))


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
