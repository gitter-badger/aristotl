(ns aristotl.routes
  (:require [io.pedestal.http.route.definition :refer [defroutes]]
            [datomic.api :as d]
            [ring.util.response :as ring-resp]
            [aristotl.db :as db]))

(defn hello-world
  [request]
  (ring-resp/response "Hello, World!"))

(defroutes routes
  [[["/" {:get hello-world}
     ^:interceptors [db/insert-datomic]]]])
