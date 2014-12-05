import requests

class Fetcher():
    """
    Downloads the thing.
    """

    def __init__(self, url):
        self.url = url

    def fetch(self, url):
        """
        Download and return the HTML page content.
        """
        custom_headers = {
            "user-agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36"
        }
        try:

            page     = requests.get(url,headers=custom_headers)
            response = page.status_code
        except:
            print('Bad connection.')
            print(self)
            sys.exit()

        if response == 200:
            return page
        else:
            print('Bad header response: ' + str(response))
            sys.exit()
