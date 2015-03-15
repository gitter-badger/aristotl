# WIP - Aristotl [![Circle CI](https://circleci.com/gh/bsima/aristotl.png?style=badge)](https://circleci.com/gh/bsima/aristotl)

An API for philosophy data.

> Sun Mar 15: The current prototype can be viewed at [aristotl.herokuapp.com](http://aristotl.herokuapp.com), but this is an old version, like 30 commits ago. It's just a Python scraper for the [SEP](http://plato.stanford.edu)

## Rationale

There are many great sources for philosophy papers and readings:

* Extremely high quality articles on the [Stanford Encyclopedia of Philosophy](http://plato.stanford.edu/)
* More high quality articles on the [University of Tennessee Martin](http://www.iep.utm.edu/)
* [JSTOR](http://www.jstor.org/) has a ton of papers available, some free, some through institutional access.
* [Philpapers](http://philpapers.org) has a huge database of contemporary philosophy papers.
* Project Gutenberg has a [philosophy bookshelf](http://www.gutenberg.org/wiki/Philosophy_(Bookshelf))
* and many more...

These are free, academic resources for high-quality philosophy. On the other hand, popular culture---[especially Silicon Valley tech culture](https://news.ycombinator.com/item?id=8709597)---does not seem to value academic philosophy. Certain figures have become popular, such as Ayn Rand or Alan Turing, but the history of philosophy is much richer than pop culture might realize.

How can philosophy become as ubiquitous and accessible as computer science? My answer to this question is to make philosophy even more accessible, in a medium that is familiar to computer scientists and software engineers. 

Aristotl will accomplish this by exposing a [REST API](https://en.wikipedia.org/wiki/Representational_state_transfer) first, and later by creating a web app that will provide access to all of the data wrapped in an easy-to-use GUI. The API will contain endpoints for every philosopher, concept, and discipline in philosophy. Each endpoint will provide bibliographies of references collected from multiple sources, full-text articles where available, and recommendations for similar entries (calculated by the Aristotl system). This data will be persisted in a Postgres database (transacted by Datomic) and supplied by scraping encyclopedias or pulling from the Philpapers API.

## Contributing

I take notes on [Trello](https://trello.com/b/jKgHwGHA/aristotl-notes) and in .org files in this repo. I plan features and todos in Trello and GitHub issues.

This is a WIP, but if you would like to get involved, you can contact me via email or [twitter](https://twitter.com/bensima), or just start working on issues and Trello cards. I generally try to follow [GitHub Flow](https://guides.github.com/introduction/flow/).
