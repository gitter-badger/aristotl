# Aristotl #

An alternative viewer for the [Stanford Encyclopedia of Philosophy](http://plato.stanford.edu).

## Dependencies ##

* mechanize (though I might be able to switch to nokogiri... I'm not sure)
* sinatra
* haml

## Libraries I'm Using ##

* Semantic-UI
* jQuery

## Install and Run ##

    git clone https://github.com/bsima/aristotl
    bundle install --path vendor
    rackup -p 4567

In your browser, go to `http://localhost:4567`. SEP articles are scraped according to their slug: `http://localhost:4567/entry/[slug]`. I plan to implement a search feature in the future.
