(ns spider
  (:require ;[itsy.core :refer-all true]
            [net.cgrand.enlive-html :as html]))

;;; I need to scrape every page from the index
;;; and parse appropriately

(def base-url "http://plato.stanford.edu/entry/")

(defn make-url [slug] (str base-url slug))

(defn fetch-sep [title]
  (html/html-resource (java.net.URL. (make-url title))))

(defn parse-sep
  "Takes an enlive node as an argument and returns the raw data."
  [article])

(defn run
  []
  (print "I don't do anything yet."))
