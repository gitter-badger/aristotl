(aristotl.discovery
 "The discovery service."
 (:require [net.cgrand.enlive-html :as html]))

; start with a data structure
(defrecord Article [title pubdate lede toc content])

(def   base-url         "http://plato.stanford.edu/")
(def   index-url        (str base-url "contents.html"))
(defn- make-url  [slug] (str base-url slug))
(defn  fetch     [url]  (html/html-resource (java.net.URL. url)))

(def index-links
  (let [a-tags   (html/select index [:div#content :ul :li :a])
        entries  (map #(get-in % [:attrs :href]) a-tags)
        sharp?   #(re-find #"^#.+" %)
        filtered (remove sharp? entries)
        links    (map make-url filtered)]
    links))
