import yfinance as yf
import pandas as pd
from datetime import datetime, timedelta

# 현재 시간 가져오기
current_time = datetime.now()
# end_date = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
end_date = datetime.now().strftime('%Y-%m-%d')
start_date = (datetime.now() - timedelta(days=1)).strftime('%Y-%m-%d')

# 결과 출력
print("현재 시간:", current_time)
# 환율 티커 심볼 설정
symbols = {
    'USD/KRW': 'KRW=X',  # 미국 달러/한국 원
    'JPY/KRW': 'JPYKRW=X',  # 일본 엔화/한국 원
    'CNY/KRW': 'CNYKRW=X',  # 중국 위안화/한국 원
    }




# 데이터프레임에 각 통화 환율 데이터를 저장
exchange_data = pd.DataFrame()

# 1분 간격 데이터를 가져오기 위해 interval='1m' 설정
for symbol_name, symbol_code in symbols.items():
    data = yf.download(symbol_code, interval='30m', start=start_date,end=end_date)  # 1분 단위, 최근 30일간 데이터
    exchange_data[symbol_name] = data['Close']  # 종가만 가져옴

# 결과 출력
print(exchange_data)
