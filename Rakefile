APP = "aristotl"

$:.unshift(File.dirname(__FILE__))

task :default => [:run]

desc "Run app locally"
task :run => "Gemfile.lock" do
    require 'app'
    Sinatra::Application.run!
end

# need to touch Gemfile.lock as bundle doesn't touch
# the file if there is no change
file "Gemfile.lock" => "Gemfile" do
    sh "bundle && touch Gemfile.lock"
end

namespace :vmc do
    desc "Update cloud foundry deployment"
    task :update => "Gemfile.lock" do
        sh "vmc update #{APP}"
    end

    desc "Get application status"
    task :status do
        sh "vmc stats #{APP}"
    end
end
