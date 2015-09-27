(ns aristotl
  "The entrypoint."
  (:require [reloaded.repl :refer [set-init! go]]
            [aristotl.systems :refer [dev-system]]))

(defn -main [& args]
  (let [system (or (first args) #'dev-system)]
    (set-init! system)
    (go)))
