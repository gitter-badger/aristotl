(defproject aristotl        "0.0.1"
  :description    "A little filosofy thang."
  :url            "http://aristotl.co"
  :source-paths   ["server"]
  :resource-paths ["common"]
  :dependencies   [[org.clojure/clojure "1.6.0"]
                   [enlive     "1.1.5"]
                   [bsima/itsy "0.1.2"]
                   [yesql      "0.4.0"]
                   [cider/cider-nrepl "0.8.1"]
                   [org.postgresql/postgresql "9.3-1102-jdbc41"]
                   [rhizome "0.2.1"]]
  :plugins        [[lein-ring "0.8.11"]]
  :ring           {:handler test.handler/app}
  :repl-options {:nrepl-middleware
                 [cider.nrepl.middleware.apropos/wrap-apropos
                  cider.nrepl.middleware.classpath/wrap-classpath
                  cider.nrepl.middleware.complete/wrap-complete
                  cider.nrepl.middleware.info/wrap-info
                  cider.nrepl.middleware.inspect/wrap-inspect
                  cider.nrepl.middleware.macroexpand/wrap-macroexpand
                  cider.nrepl.middleware.ns/wrap-ns
                  cider.nrepl.middleware.resource/wrap-resource
                  cider.nrepl.middleware.stacktrace/wrap-stacktrace
                  cider.nrepl.middleware.test/wrap-test
                  cider.nrepl.middleware.trace/wrap-trace
                  cider.nrepl.middleware.undef/wrap-undef]}
  :profiles       {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                        [ring-mock "0.1.5"]]}})
