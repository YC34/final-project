import pandas as pd
from bs4 import BeautifulSoup
import requests
from urllib.parse import urljoin

class NaverNewsScraper:
    def __init__(self, url):
        self.url = url
        self.soup = None
        self.titles = []
        self.locations = []
        self.contents = []
        self.company_names = []
        self.dates = []
    def fetch_page(self):
        page = requests.get(self.url)
        self.soup = BeautifulSoup(page.content,"html.parser")

    def parse_data(self):
        if self.soup is None:
            raise ValueError("Soup object is not initialized. Call fetch_page() first")
        base_url = self.url
        divs = self.soup.find_all('div', class_='mainNewsList')
        
        for div in divs:
            lis = div.select('ul > li')
            
            for li in lis:
                dds = li.select('dl > dd')
                if len(dds) >=2:
                    a_tag = dds[0].find('a')
                    if a_tag:
                        title = a_tag.get_text(strip=True)
                        location = a_tag['href']
                        self.titles.append(title)
                        self.locations.append(urljoin(base_url, location))


                    spans = dds[1].find_all('span')
                    if len(spans) >=3:
                        content = dds[1].get_text(strip=True)
                        company_name = spans[0].get_text(strip=True)
                        date = spans[2].get_text(strip=True)
                        self.contents.append(content)
                        self.company_names.append(company_name)
                        self.dates.append(date)

    def to_dataframe(self):
        df = pd.DataFrame({
                'title': self.titles,
                'url': self.locations,
                'contents': self.contents,
                'company': self.company_names,
                'news_date': self.dates
            })
        df.index +=1
        return df

    def scrape(self):
        self.fetch_page()
        self.parse_data()
        return self.to_dataframe()


