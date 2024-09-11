import pandas as pd
import FinanceDataReader as fdr
from datetime import datetime,timedelta


class Stock_call():
    pd.set_option('display.max_columns', 10)
    def __init__(self,code,start_date,end_date=None):
        self.code = code
        self.start_date = start_date
        self.end_date = end_date if end_date else (datetime.now() - timedelta(days=1)).strftime('%Y-%m-%d')
        self.dates = []
        self.opens =[]
        self.highs =[]
        self.lows =[]
        self.closes =[]
        self.volumes =[]
        self.changes =[]
        print(f"오늘 날짜는 : {datetime.now()}")
        print(f"수집할 코드는 : {self.code}")

    # 주식이름으로 코드 찾아내는 메소드
    def get_stock_code_by_name(name):
        # 주식 이름으로 주식 코드를 검색
        try:
            stock_list = fdr.StockListing('KRX')  # KRX 상장 주식 목록 가져오기
            result = stock_list[stock_list['Name'].str.contains(name, case=False)]
            if not result.empty:
                print(f"'{name}'에 대한 주식 코드:")
                print(result[['Code', 'Name']])
                return result.Code.values[0]
            else:
                print(f"'{name}'에 대한 주식 코드가 없습니다.")
                return pd.DataFrame()
        except Exception as e:
            print(f"주식 코드 검색 오류: {e}")
            return pd.DataFrame()

    def collect_stock_data(self):
        try:
            stock_data = fdr.DataReader(self.code, self.start_date, self.end_date)
            self.dates = stock_data.index.to_list()
            self.opens = stock_data['Open'].to_list()
            self.highs = stock_data['High'].to_list()
            self.lows = stock_data['Low'].to_list()
            self.closes = stock_data['Close'].to_list()
            self.volumes = stock_data['Volume'].to_list()
            self.changes = (stock_data['Close'] - stock_data['Open']).to_list()

            result_df = pd.DataFrame({
                'code': self.code,
                'e_type' :'stock',
                'stock_date': self.dates,
                'open_vol': self.opens,
                'high_vol': self.highs,
                'low_vol': self.lows,
                'close_vol': self.closes,
                'volume': self.volumes,
                'change_vol': self.changes
            })
            print("데이터 수집 완료")
            return result_df

        except Exception as e:
            print(f"데이터 수집 오류: {e}")
            return pd.DataFrame()  # 빈 DataFrame 반환

    def collect_economic_indicates(self):
        try:
            self.end_date =  (datetime.now() - timedelta(days=2)).strftime('%Y-%m-%d')
            economic_indicate = fdr.DataReader(self.code, self.start_date, self.end_date)
            self.dates = economic_indicate.index.to_list()
            self.opens = economic_indicate['Open'].to_list()
            self.highs = economic_indicate['High'].to_list()
            self.lows = economic_indicate['Low'].to_list()
            self.closes = economic_indicate['Close'].to_list()
            self.volumes = economic_indicate['Volume'].to_list()
            self.changes = (economic_indicate['Close'] - economic_indicate['Open']).to_list()

            result_df = pd.DataFrame({
                'code': self.code,
                'e_type' :'economic_indicate',
                'stock_date': self.dates,
                'open_vol': self.opens,
                'high_vol': self.highs,
                'low_vol': self.lows,
                'close_vol': self.closes,
                'volume': self.volumes,
                'change_vol': self.changes
            })
            print("데이터 수집 완료")
            return result_df

        except Exception as e:
            print(f"데이터 수집 오류: {e}")
            return pd.DataFrame()  # 빈 DataFrame 반환


code =Stock_call.get_stock_code_by_name('LG')
print(code)
# stock_call = Stock_call(code,'2024-09-03','2024-09-04')
# print(stock_call)
# print(stock_call.collect_stock_data())
# economic_indicate = Stock_call('CNY/KRW','2023')
# print(economic_indicate.collect_economic_indicates())


# price_df = fdr.DataReader('069500, 229200')
# fdr.Data

# df_krx = fdr.StockListing('KRX')
# df_btc = fdr.StockListing('BTC')
# print(df_krx.head())
# Stock_call.collect_stock_data()
# Stock_call.get_stock_code_by_name('삼성전자')
# print(fdr.DataReader('005930','2023'))
# print(len(df_krx))
# print(fdr.DataReader('USD/KRW','2024-02-01','2024-02-02'))
#
# # 환율 코드
# # USD/KRW	달러당 원화 환율
# # USD/EUR	달러당 유로화 환율
# # USD/JPY	달러당 엔화 환율
# # CNY/KRW	위엔화 원화 환율
# # EUR/USD	유로화 달러 환율
# # USD/JPY	달러 엔화 환율
# # JPY/KRW	엔화 원화 환율
# # AUD/USD	오스트레일리아 달러 환율
# # EUR/JPY	유로화 엔화 환율
# # USD/RUB	달러 루블화
#
# # print(df_btc.head())
# print(fdr.DataReader('BTC/KRW','2023'))
#
# # 코인 code
# # BTC/KRW	비트코인 원화 가격
# # ETH/KRW	이더리움 원화 가격
# # XRP/KRW	리플 원화 가격
# # BCH/KRW	비트코인 캐시 원화 가격
# # EOS/KRW	이오스 원화 가격
# # LTC/KRW	라이트 코인 원화 가격
# # XLM/KRW	스텔라 원화 가격