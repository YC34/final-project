
import requests
from datetime import datetime, timedelta,timezone
import pytz


class BtcPer1min:
    def __init__(self):
        print("======================================================================================")
        self.kst = pytz.timezone('Asia/Seoul')
        self.now_kst = datetime.now(self.kst)
        self.one_minute_ago_kst = self.now_kst - timedelta(minutes=1)
        self.iso_datetime_str = self.one_minute_ago_kst.strftime('%Y-%m-%dT%H:%M:%SZ')
        self.url = 'https://api.upbit.com/v1/candles/minutes/1?market=KRW-BTC&to='
        print(f"수집 시간 : {self.iso_datetime_str}")
        print(self.url)

    def call_api(self):
        response = requests.get(self.url)
        code = response.status_code
        if code == 200:
            result = response.json()
            return result
        else:
            print("잘못된 요청 입니다. 코드를 확인하세요.")
            return None

    def data_cleansing(self):
        raw_data = self.call_api()
        print(f"raw data :{raw_data} ")
        if raw_data is None:
            return None

        result = []
        for data in raw_data:
            # `candle_date_time_utc` 값을 문자열에서 datetime 객체로 변환
            kst_time_str = data['candle_date_time_kst']
            kst_datetime = datetime.fromisoformat(kst_time_str.replace('Z', '+00:00'))

            # PostgreSQL timestamp 형식으로 변환 (YYYY-MM-DD HH:MM:SS)
            postgresql_timestamp = kst_datetime.strftime('%Y-%m-%d %H:%M:%S')
            current_time_int = int(self.now_kst.strftime('%Y%m%d%H%M'))

            # 변환된 데이터 딕셔너리 생성
            transformed_item = {
                '_id': current_time_int,
                'market': data['market'],
                'candle_date_time': postgresql_timestamp,
                'opening_price': data['opening_price'],
                'high_price': data['high_price'],
                'low_price': data['low_price'],
                'trade_price': data['trade_price']
            }

            result.append(transformed_item)
        print(result)
        return result


