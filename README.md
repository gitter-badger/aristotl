# Aristotl [![Circle CI](https://circleci.com/gh/bsima/aristotl.png?style=badge)](https://circleci.com/gh/bsima/aristotl)

An alternative viewer for the [Stanford Encyclopedia of Philosophy](http://plato.stanford.edu).

**Currently being re-written to Python. Or maybe Clojure. Not sure yet...**

## Rationale ##

The SEP is awesome. High-quality philosophy articles worthy of inclusion in any philosophy paper's bibliography. However, navigating and reading the SEP online is a PITA. And the lack of mobile support frustrates me.

Aristotl, when complete, will parse a given SEP page and display the content in a much easier-to-read and easier-to-navigate format. Each article will have a table of contents that scrolls with the page, the text will be bigger and easier to read, background/foreground color contrasts will be better, the navigation sidebars and stuff will hide automatically while reading an article, etc etc. (Many of these ideas are inspired by the Medium.com reading experience.)

Additionally, I want a way to parse the information in the SEP. There is no API, but, after I scrape the info with Ruby, the next logical thing to do (after displaying it in html as described above) is to output that information into a JSON file. This will help me answer such questions as: Which article has the most citations? Which is the longest? Is there a branch of philosophy (say, ethics) that is underrepresented in the SEP? So on and so forth... *And*, when I have a JSON API, I will be able to make a [DuckDuckGo](http://ddg.gg) plugin that parses the SEP for information whenever someone searches for philosophy stuff. That would be so cool :)

## Dependencies ##

* python

## Ideas ##

* mmmm... a sane commenting system would be cool...
* Design inspiration: Medium.com article reading styles, plus @cmod's Bibliotype library. That'd be so sexy.
    * Instapaper.com is another great reading experience
* JavaScript search-and-suggest box on the index page
* Implement an auth system and save user notes? Or at least highlights?
* ooohhh a social highlighting system like the kindle has. be sweet.
* A night reading style is necessary.... omg flux. i need flux on this site.
* Don't forget to add a GitHub banner in the corner! Get ppl to contribute
* **When someone highlights a term, provide a popup for definitions, links to other SEP articles, links to Wikipedia, etc.**

## TODO ##

* Make a CONTRIBUTING.md
* tighten up the code
* Get capistrano working
* Write actual, modern JavaScript and not just procedural crap

## Install and Run ##

    git clone https://github.com/bsima/aristotl
    cd aristotl
    bundle install --path vendor
    rackup -p 4567

In your browser, go to `http://localhost:4567`. SEP articles are scraped according to their slug: `http://localhost:4567/entries/[slug]`. For example, `http://plato.stanford.edu/entries/montaigne/` would be mapped to `http://localhost:4567/entries/montaigne/`

I plan to implement a search feature in the future.
