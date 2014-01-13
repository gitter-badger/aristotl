require 'mechanize'
require 'sinatra/base'
require 'haml'


# Var matey!
#
# Site variables go 'ere
$site_title   = "Aristotl"
$site_tagline = "A better reading experience."

# A class is needed to make
# Mechanize play nice with Sinatra.
class Aristotl < Sinatra::Base
    set :haml, :format => :html5

    # @TODO handle 404's and other errors

    get '/' do
        haml :index # @TODO make an index page
    end

    get '/about' do
        haml :about # @TODO make an about page
    end

    

    get '/entries/:entry' do
        # Load the page
        agent = Mechanize.new
        url  = "http://plato.stanford.edu/entries/" + params[:entry] + "/"
        page  = agent.get(url)

        $outlink = "View on the <a target='_blank' href='" + url + "'>Stanford Encyclopedia of Philosophy</a>"

        # @TODO Build error handling for when the requested entry doesn't exist on the SEP

        # Let's do some page scraping.
        #
        # @TODO I need to edit the `$content` (using regex's?) on `img src=` to
        #       properly hotlink images back to the plato.stanford.edu website.
        #       Or, just remove the image if it's unnecessary.
        $entry_title = page.search("h1")
        # $entry_title.gsub(%r{</?[^>]+?}, '')
        $entry_title = $entry_title.inner_text()
        $entry_pub   = page.search("div#pubinfo")

        # @TODO This is gonna be a bit harder. I need every `p` element before the
        #       first `hr` element.
        #$entry_intro = page.search("")

        #$entry_toc = page.search("ul:first-of-type")

        # @TODO This ain't gonna be easier either. I now need all of the content
        #       after the `hr` element.
        $entry_content = page.search("div#aueditable")

        # Generate the page!
        haml :entry
    end

    get '/api/:entry' do
        # Planned feature: An API for accessing the SEP info in JSON, XML, etc format
        #
        # I have no idea how I'm gonna do this, but whatevs.
    end
end

