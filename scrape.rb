require 'rubygems'
require 'mechanize'

slug = 'montaigne'

# Scrape Plato for `:slug`
# Grab the article content and display it
agent = Mechanize.new
url   = "http://plato.stanford.edu/entries/" + slug + "/"
page  = agent.get(url)

puts page.search("div#aueditable")

# This should be the article content

