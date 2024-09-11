from fontTools.misc.plistlib import end_date
import sys
import os
from api_call import Stock_call
import pandas as pd
from datetime import datetime
import argparse

sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))

from postgresql_insert import PostgresDatabase

exchange_codes = [
    'USD/KRW', 'USD/EUR', 'USD/JPY', 'CNY/KRW', 'EUR/USD',
    'USD/JPY', 'JPY/KRW', 'AUD/USD', 'EUR/JPY', 'USD/RUB'
]

crypto_codes = [
    'BTC/KRW', 'ETH/KRW', 'XRP/KRW', 'BCH/KRW', 'EOS/KRW',
    'LTC/KRW', 'XLM/KRW'
]

pd.set_option('display.max_columns', 10)


def validate_date(date_str):
    try:
        datetime.strptime(date_str, '%Y-%m-%d')
        return True
    except ValueError:
        return False


def insert_data_in_batches(df, table_name, batch_size=1000):
    db = PostgresDatabase(
        dbname="postgres",
        user="postgres",
        password="1234",
        host="203.234.214.63",
        port="5432"
    )
    db.connect()

    try:
        # 데이터프레임을 배치로 나누어 삽입
        for start in range(0, len(df), batch_size):
            end = start + batch_size
            batch_df = df.iloc[start:end]
            db.insert_data(table_name, batch_df)
    except Exception as e:
        print(f"데이터 삽입 중 오류 발생: {e}")
    finally:
        db.close()


def main():
    parser = argparse.ArgumentParser(description='데이터 수집 및 데이터베이스 삽입')
    parser.add_argument('code', type=str, help='환율 또는 암호화폐 코드 (예: BTC/KRW)')
    parser.add_argument('start_date', type=str, help='시작 날짜 (형식: YYYY-MM-DD)')
    parser.add_argument('--end_date', type=str, help='끝 날짜 (형식: YYYY-MM-DD)', default=None)

    args = parser.parse_args()
    code = args.code
    start_date = args.start_date
    end_date = args.end_date if args.end_date else datetime.today().strftime('%Y-%m-%d')

    if not validate_date(start_date):
        print("시작 날짜의 형식이 올바르지 않습니다. 형식은 YYYY-MM-DD입니다.")
        return

    if end_date and not validate_date(end_date):
        print("끝 날짜의 형식이 올바르지 않습니다. 형식은 YYYY-MM-DD입니다.")
        return

    stock = Stock_call(code, start_date, end_date)
    try:
        if code in exchange_codes or code in crypto_codes:
            result_df = stock.collect_economic_indicates()
        else:
            result_df = stock.collect_stock_data()
    except Exception as e:
        print(f"데이터 수집 중 오류 발생: {e}")
        return

    try:
        insert_data_in_batches(result_df, "economic_raw_data")
    except Exception as e:
        print(f"데이터베이스 삽입 중 오류 발생: {e}")
        return

    now = datetime.now()
    formatted_time = now.strftime("%Y-%m-%d %H:%M:%S")
    print("수집 완료 시간 :", formatted_time)


if __name__ == "__main__":
    main()
