# Aristotl [![Circle CI](https://circleci.com/gh/bsima/aristotl.png?style=badge)](https://circleci.com/gh/bsima/aristotl)

An alternative viewer for the [Stanford Encyclopedia of Philosophy](http://plato.stanford.edu).

**Currently being re-written to Python. Or maybe Clojure. Not sure yet...**

The current prototype can be viewed at [aristotl.herokuapp.com](http://aristotl.herokuapp.com).

## Rationale ##

The SEP is awesome. High-quality philosophy articles worthy of inclusion in any philosophy paper's bibliography. However, navigating and reading the SEP online is a PITA. And the lack of mobile support frustrates me.

Aristotl, when complete, will parse a given SEP page and display the content in a much easier-to-read and easier-to-navigate format. Each article will have a table of contents that scrolls with the page, the text will be bigger and easier to read, background/foreground color contrasts will be better, the navigation sidebars and stuff will hide automatically while reading an article, etc etc. (Many of these ideas are inspired by the Medium.com reading experience.)

Additionally, I want a way to parse the information in the SEP. There is no API, but, after I scrape the info and store the raw content in a database, the next logical thing to do (after displaying it in html as described above) is to expose that information via JSON. This will help me answer such questions as: Which article has the most citations? Which is the longest? Is there a branch of philosophy (say, ethics) that is underrepresented in the SEP? So on and so forth... *And*, when I have a JSON API, I will be able to make a [DuckDuckGo](http://ddg.gg) plugin that parses the SEP for information whenever someone searches for philosophy stuff. That would be so cool :)

## How it works currently ##

Right now, a Python app (in the `client` directory) is hosted on Heroku. Whenever an article is requested, the Python app fetches the content from the SEP, parses it, and stores it in the database. If it already exists in the database, the app simply displays it as HTML.

**Next steps**: Write a backend that can fill out the database for me by crawling the SEP's index (see `TODO.org` file). I started writing this in Clojure (see `server` directory) but didn't get too far yet.

The `common` directory contains declarative data structures for the parser (`.edn`) and SQL database objects (`.sql`). These can be shared between the client and server, accross languages, which is nice.

## Ideas ##

* a sane commenting system would be cool...
* Design inspiration: Medium.com article reading styles, plus @cmod's Bibliotype library. That'd be so sexy.
    * Instapaper.com is another great reading experience
* JavaScript search-and-suggest box on the index page
* Implement an auth system and save user notes? Or at least highlights?
* a social highlighting system like the kindle has. be sweet.
* A night reading style is necessary.... omg flux. i need flux on this site.
* Don't forget to add a GitHub banner in the corner! Get ppl to contribute
* **When someone highlights a term, provide a popup for definitions, links to other SEP articles, links to Wikipedia, etc.**

## TODO ##

* Make a CONTRIBUTING.md
* tighten up the code

## Install and Run ##

TODO...
