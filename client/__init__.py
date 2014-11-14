import os
import logging

from flask import Flask, request as req

app = Flask(__name__)
config_path = os.environ.get("CONFIG_PATH", "client.config.DevelopmentConfig")
app.config.from_object(config_path)

from client.controllers import entries
import filters

app.register_blueprint(entries.blueprint)

# Logging code:
app.logger.setLevel(logging.NOTSET)

@app.after_request
def log_response(resp):
    app.logger.info("{} {} {}\n{}".format(
        req.method, req.url, req.data, resp)
    )
    return resp
