(ns aristotl.spider
  (:require [net.cgrand.enlive-html :as html]
            [clojure.string :as str]
            [clojure.pprint :refer :all]))

;; The Spider will crawl the index page and, for each article in the SEP, parse the
;; HTML and input it into a Postgres database. The database connection configuration
;; is located in the `aristotl.db` namespace.

;; First, I'll need the base url for forming the rest.
(def base-url (str "http://plato.stanford.edu/"))

;; Then I'll need the url of the index page. This will serve as the starting
;; point for a spider to fill the database.
(def index-url (str base-url "contents.html"))

;; I have all of the article's enlive selectors stored as an edn file. This allows
;; (and forces) me to build the parser in a general way. That is, the parser should
;; be able to take any amount of selectors. This way, when I eventually move on to
;; other encyclopedias, I will be able to just pop in other selector files and
;; eveything will work hunky-dory.
(def selectors (read-string (slurp "common/sep_enlive.edn")))

;; A helper function for taking slugs from the index page and feeding them to a
;; fetcher or a spider.
(defn- make-url [slug] (str base-url slug))

;; Download the page with enlive
(defn fetch [url]
  (html/html-resource (java.net.URL. url)))

;; Go and grab the index page.
(def index
  (fetch index-url))

;; Parse the index file for all of it's links.
(def index-links
  (let [a-tags   (html/select index [:div#content :ul :li :a])
        entries  (map #(get-in % [:attrs :href]) a-tags)
        sharp?   #(re-find #"^#.+" %)
        filtered (remove sharp? entries)
        links    (map make-url filtered)]
    links))

(defn remove-newline-chars [root]
  "Walks the data structure and removes newline characters.
  Only works with SEP enlive nodes."
  (clojure.walk/prewalk
   (fn [x] (if (= clojure.lang.LazySeq (type x))
             (if (= java.lang.String (type (first x)))
               (clojure.string/replace (first x) #"\n" "")))
     x)
   root))

;;; working
(comment

  (prewalk
   (fn [x]
     (if (= clojure.lang.LazySeq (type x))
       (if (= java.lang.String (type (first x)))
         (print (clojure.string/replace (first x) #"\n" "")))
       (prn (type x) '- x))
     x)
   (:lede g))


  (prewalk
   (fn [x]
     (if (= clojure.lang.LazySeq
            (type x))
       (if (= java.lang.String
              (type (first x)))
         (do (print (clojure.string/replace (first x) #"\n" ""))
             (print )
             ))
       (prn (type x) '- x))
     x)
   (:lede g))

)



;; Here we actually do the fun stuff. This is a parser for the enlive node
;; of an article. It is meant to be called like so: `(parse article (fetch url))`
;; where `url` is the full URL of the article to be parsed. I'm doing it this way
;; to keep it as composable as possible. I don't want to assume that the parser will
;; always download the article, so I require `fetch` to be called explicitly.
;;
;; However, I am assuming that newline characters are unnecessary, and I strip them
;; out. Eventually I will be emitting the enlive node as HTMl (with enlives' `emit*`
;; function), and this prevents the newline characters from showing up as a plain
;; text in the final HTML. See `display-article` for more.

;; I need to do a depth first search and remove all of the newline chars from
;; every node
(defn parse-article
  "Takes an enlive node as an argument and returns the raw data.
  Also serves as the spider handler function, when using itsy."
  [article]
  (let [contents (map #(first (html/select article (% selectors))) (keys selectors))]
    (zipmap (keys selectors) contents)))


(defn run-and-parse
  []
  (let [x-node (fetch (first index-links))
        ;x-nodes (map fetch index-links)
        page (map #(html/select x-node (% selectors)))
        ;pages (map #(html/select x-node (% selectors)))
        ]
    (pprint page)
    (pprint (parse-article (fetch (first index-links))))))


;; By default, `emit*` interpolates the HTML tags as strings around the content.
;; The output is a sequence of many small strings. `p` tags come out as a string
;; of one character, for example. Try running `emit*` to see what I mean. So, I need
;; to `apply str` to the output of `emit*` to get it into a single, valid HTML
;; structure. As a result of this, any newline character in the source are converted
;; to "\n" and show up in the rendered HTML. To prevent this, I strip the newline
;; characters, as mentioned above `parse-article`.
;;
;; `display-article` is meant to be used like so:
;;
;;     (display-article (parse-article (fetch url)))
;;
;; Or, with the thread macro:
;;
;;    (-> url
;;        (fetch)
;;        (parse-article)
;;        (display-article))
;;
(defn display-article
  "Takes a parsed article and returns the html for display."
  [article]
  (apply str (map #(html/emit* (% article)) (keys article))))

;; This should really be a lazy-recur type of function. It would really save
;; CPU cycles
(defn run-spider []
  (let [article-list (map fetch index-links)
        parsed-articles (map parse-article article-list)])
  ; Need a database handler to go here
  )

                           
(defn run [] (print"Nothing to see here, move along."))
