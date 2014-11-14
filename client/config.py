import os

class DevelopmentConfig(object):
    SQLALCHEMY_DATABASE_URI = "sqlite:///aristotl-dev.db"
    DEBUG = True
    SECRET_KEY = os.environ.get("ARISTOTL_SECRET_KEY")
