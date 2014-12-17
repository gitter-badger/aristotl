(defproject aristotl "0.0.1"
  :description    "A little filosofy thang."
  :url            "http://aristotl.co"
  :source-paths   ["server"]
  :resource-paths ["common"]
  :dependencies   [[org.clojure/clojure        "1.6.0"]
                   [enlive                     "1.1.5"]
                   [environ                    "1.0.0"]
                   [yesql                      "0.4.0"]
                   [org.clojure/java.jdbc      "0.3.6"]
                   [org.postgresql/postgresql  "9.3-1102-jdbc41"]]
  :plugins        [[lein-ring       "0.8.11"]
                   [lein-marginalia "0.8.0"]
                   [lein-environ    "1.0.0"]]
  :ring           {:handler test.handler/app}
  :profiles       {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                        [ring-mock "0.1.5"]]}})
