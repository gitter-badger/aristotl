import requests

class Fetcher():
    """
    Fetches the HTML code for the given article.
    TODO: add functionality for storing headers
    """

    def __init__(self, url):
        self.url = url
        self.contents = ''
        self.

    def download(self):
        r = requests.get(self.url)
        if r.status_code == 200:

            self.encoding = r.encoding
            self.headers = r.headers
