from naver_summary import SummaryProcessor
def main():
    # 데이터베이스 연결 설정
    db_config = {
        'dbname': 'postgres',
        'user': 'postgres',
        'password': '1234',
        'host': '203.234.214.63',
        'port': '5432'
    }

    # SummaryProcessor 인스턴스 생성
    processor = SummaryProcessor(db_config)

    try:
        # 데이터 선택
        print("데이터 선택 중...")
        data = processor.select_data()
        print(f"선택된 데이터 수: {len(data)}")

        # 회사 이름 업데이트
        print("회사 이름 업데이트 중...")
        processor.update_company_names()

        # SQL 쿼리 실행
        print("SQL 쿼리 실행 중...")
        processor.execute_queries()

    except Exception as e:
        print(f"오류 발생: {e}")

    finally:
        # 데이터베이스 연결 종료
        print("데이터베이스 연결 종료 중...")
        processor.close()

if __name__ == '__main__':
    main()
