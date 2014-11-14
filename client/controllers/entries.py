from flask import render_template, Blueprint, request

from client.models import Article

blueprint = Blueprint('entries', __name__)

@blueprint.route("/entry/<slug>")
def article(slug):
    c = Article(slug).content
    return render_template("article.html", content=c)
