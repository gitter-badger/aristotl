(aristotl.discovery
 "The discovery service."
 (:require [net.cgrand.enlive-html :as html]))


(defprotocol DataSource
  (base-url    [this] "The base-url") 
  (index-url   [this] "If available, a complete listing of all the contents of the data source.")
  (index-links [this] "If availalbe, a parsed list of all the links in the index."))


(defprotocol Article
  (url     [this] "Returns the url of the Article")
  (fetch   [this] "Gets the articles and returns the complete HTTP response (for parsing with enlive)")
  (content [this] "Returns the content of the article"))


(defrecord SEP [slug]

  DataSource
  (base-url    [this] "http://plato.stanford.edu/")  
  (index-url   [this] (str base-url "contents.html"))
  (index-links [this]
    (let [a-tags   (html/select (index-url) [:div#content :ul :li :a])
          entries  (map #(get-in % [:attrs :href]) a-tags)
          sharp?   #(re-find #"^#.+" %) ;remove links to subsections
          filtered (remove sharp? entries)
          links    (map make-url filtered)]
    links))
  
  Article
  (url     [this] (str base-url "entry/" (:slug this)))
  (fetch   [this] (html/html-resource (java.net.URL. (url (:slug this)))))
  (content [this] (fetch (:slug this))))


