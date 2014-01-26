APP = "aristotl"

$:.unshift(File.dirname(__FILE__))

task :default => [:run]

# # Tests to run #
#
# * Test whether a few SEP articles return HTTP code 200.
# * Test 404 handling.
# * Future: Test API

# Temporary stuff to get travis CI working
desc "Temp"
task :run => "Gemfile.lock" do
    # need to touch Gemfile.lock as bundle doesn't touch
    # the file if there is no change
    file "Gemfile.lock" => "Gemfile" do
        sh "bundle && touch Gemfile.lock"
    end
end

