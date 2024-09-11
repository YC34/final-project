from crawler_naver import NaverNewsScraper
from postgresql_insert import PostgresDatabase
from datetime import datetime

def main():
    url = "https://finance.naver.com/news/mainnews.naver"
    scraper = NaverNewsScraper(url)
    df = scraper.scrape()

    # Initialize the database connection
    db = PostgresDatabase(
        dbname="postgres",
        user="postgres",
        password="1234",
        host="203.234.214.63",
        port="5432"
    )

    db.connect()
    # Insert data into the database
    db.insert_data("naver_news", df)
    db.close()

    now = datetime.now()

    # 원하는 형식으로 출력
    formatted_time = now.strftime("%Y-%m-%d %H:%M:%S")
    print("수집 완료 시간 :", formatted_time)

if __name__ == "__main__":
    main()
