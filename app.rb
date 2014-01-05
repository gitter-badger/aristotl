require 'mechanize'
require 'sinatra/base'
require 'haml'


class Aristotl < Sinatra::Base
    set :haml, :format => :html5

    get '/' do
        haml :index # @TODO make an index page
    end

    get '/about' do
        haml :about # @TODO make an about page
    end

    get '/entry/:entry' do
        # Scrape Plato for `:slug`
        # Grab the article content and display it
        agent    = Mechanize.new
        url      = "http://plato.stanford.edu/entries/" + params[:entry] + "/"
        page     = agent.get(url)

        # @TODO I need to edit the `$content` using regex's on `img src=` to
        #       properly hotlink images back to the plato.stanford.edu website.
        #       Or, just remove the image if it's unnecessary.
        $content = page.search("div#aueditable")
        haml :entry
    end
end
