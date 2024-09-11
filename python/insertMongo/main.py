import sys,os
from api_call import BtcPer1min
import pymongo
sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))

from mongodb_insert import MongoDB


def main():
    # BtcPer1min 클래스의 인스턴스 생성 및 데이터 정리
    btc_data = BtcPer1min()
    cleaned_data = btc_data.data_cleansing()

    if cleaned_data:
        print("정리된 데이터:", cleaned_data)
        print("======================================================================================")

        # MongoDB 클라이언트 및 데이터베이스 설정
        client = pymongo.MongoClient("mongodb://192.168.0.124:27017/")
        db = client["pyc"]
        mongo_db = MongoDB(client, db)
        mongo_db.insert_document(cleaned_data)

    else:
        print("데이터가 없습니다.")
        print("======================================================================================")


if __name__ == "__main__":
    main()