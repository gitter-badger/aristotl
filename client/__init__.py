import os
import logging

from flask import Flask, request as req
from flask.ext import assets

app = Flask(__name__)
config_path = os.environ.get("CONFIG_PATH", "client.config.DevelopmentConfig")
app.config.from_object(config_path)

env = assets.Environment(app)
env.load_path = [
    os.path.join(os.path.dirname(__file__), 'static/sass'),
    os.path.join(os.path.dirname(__file__), 'static/coffee'),
    os.path.join(os.path.dirname(__file__), 'static/bower_components'),
]
env.register(
    'js_all',
    assets.Bundle(
        'jquery/dist/jquery.min.js',
        assets.Bundle(
            'all.coffee',
            filters=['coffeescript']
        ),
        output='js_all.js'
    )
)
env.register(
    'css_all',
    assets.Bundle(
        'all.sass',
        filters='sass',
        output='css_all.css'
    )
)

from client.controllers import entries

app.register_blueprint(entries.blueprint)

# Logging code:
app.logger.setLevel(logging.NOTSET)

@app.after_request
def log_response(resp):
    app.logger.info("{} {} {}\n{}".format(
        req.method, req.url, req.data, resp)
    )
    return resp
