# data_module.py
import pandas as pd
import requests as rq
from io import BytesIO
import zipfile
import xmltodict
import json

def get_filtered_df():
    # API URL 및 API 키 설정
    codezip_url = 'https://opendart.fss.or.kr/api/corpCode.xml?crtfc_key=a0d21b33a75353395a9de13d40785a4dec208059'

    # 파일 다운로드
    response = rq.get(codezip_url)

    # 상태 코드 확인
    print(response.status_code)

    # ZIP 파일을 BytesIO로 읽기
    zip_file = zipfile.ZipFile(BytesIO(response.content))

    # ZIP 파일 내 파일 읽기
    code_data = zip_file.read('CORPCODE.xml').decode('utf-8')

    # xml to dict
    data_odict = xmltodict.parse(code_data)
    data_dict = json.loads(json.dumps(data_odict))
    data = data_dict.get('result').get('list')
    corp_list = pd.DataFrame(data)

    # 필터링
    filtered_df = corp_list[corp_list['stock_code'].notna()]
    return filtered_df
