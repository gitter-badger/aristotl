from flask import render_template

from app import app
from database import session
from models import Article

@app.route("/")
def articles():
    articles = session.query(Article)
    articles = articles.order_by(Article.datetime.desc())
    articles = articles.all()
    return render_template("articles.html", articles=articles)
