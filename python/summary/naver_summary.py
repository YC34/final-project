import psycopg2

class SummaryProcessor:
    def __init__(self, db_config):
        self.conn = psycopg2.connect(
            dbname=db_config['dbname'],
            user=db_config['user'],
            password=db_config['password'],
            host=db_config['host'],
            port=db_config['port']
        )
        self.cur = self.conn.cursor()

    def select_data(self):
        query = """
            SELECT * FROM naver_news
            WHERE summary_yn = 'N';
        """
        self.cur.execute(query)
        return self.cur.fetchall()

    def update_company_names(self):
        """ 회사 이름 업데이트 """
        update_queries = [
            "UPDATE naver_news SET company = '연합뉴스' WHERE company = 'ПЌЧеДКНК';",
            "UPDATE naver_news SET company = '중앙일보' WHERE company = 'ИгДЯХѕЕЅРЬ';"
        ]

        try:
            for query in update_queries:
                self.cur.execute(query)
            self.conn.commit()  # 변경 사항을 커밋합니다.
            print("회사 이름이 업데이트되었습니다.")
        except Exception as e:
            print(f"업데이트 중 오류 발생: {e}")
            self.conn.rollback()  # 오류 발생 시 롤백합니다.

    def execute_queries(self):
        """ SQL 쿼리를 차례대로 실행하고 로그를 남김 """
        try:
             # 1. 전체 총 카운트 업데이트 (naver_news에서 가져오기)
            overall_count_query = """
                                  SELECT COUNT(*) FROM naver_news
                                  WHERE create_at <= CURRENT_DATE - INTERVAL '1 day' + INTERVAL '23 hours 59 minutes 59 seconds'
                                  AND summary_yn = 'N';
                              """
            self.cur.execute(overall_count_query)
            overall_count_result = self.cur.fetchone()[0]

            # 2 .전체 총 카운트를 업데이트
            update_total_count_query = """
                                  UPDATE summary
                                  SET total_count = total_count + %s
                                  WHERE flag = 1;
                              """
            self.cur.execute(update_total_count_query, (overall_count_result,))
            self.conn.commit()
            print("전체 총 카운트가 업데이트되었습니다.")

            # 3. 일별 총 카운트 삽입
            insert_query2 = """
                INSERT INTO summary (flag, date, company, total_count, data_type)
                SELECT 2 AS flag, DATE(create_at) AS date, NULL AS company, COUNT(*) AS total_count, 'naver_news' AS data_type
                FROM naver_news
                WHERE create_at <= CURRENT_DATE - INTERVAL '1 day' + INTERVAL '23 hours 59 minutes 59 seconds'
                AND summary_yn = 'N'
                GROUP BY DATE(create_at);
            """
            self.cur.execute(insert_query2)
            self.conn.commit()
            print("일별 총 카운트가 summary 테이블에 삽입되었습니다.")


            # 4. 회사별 총 카운트 업데이트
            company_count_query = """
                                SELECT company, COUNT(*) 
                                FROM naver_news
                                WHERE create_at <= CURRENT_DATE - INTERVAL '1 day' + INTERVAL '23 hours 59 minutes 59 seconds'
                                AND summary_yn = 'N'
                                GROUP BY company;
                            """
            self.cur.execute(company_count_query)
            company_counts = self.cur.fetchall()

            # 5 .각 회사별로 total_count 업데이트
            for company, count in company_counts:
                update_query = """
                                    UPDATE summary
                                    SET total_count = total_count + %s
                                    WHERE flag = 3 AND company = %s;
                                """
                self.cur.execute(update_query, (count, company))

            self.conn.commit()
            print("회사별 총 카운트가 업데이트되었습니다.")


            # 6. 회사별 일별 총 카운트 삽입
            insert_query4 = """
                INSERT INTO summary (flag, date, company, total_count, data_type)
                SELECT 4 AS flag, DATE(create_at) AS date, company AS company, COUNT(*) AS total_count, 'naver_news' AS data_type
                FROM naver_news
                WHERE create_at <= CURRENT_DATE - INTERVAL '1 day' + INTERVAL '23 hours 59 minutes 59 seconds'
                AND summary_yn = 'N'
                GROUP BY DATE(create_at), company;
            """
            self.cur.execute(insert_query4)
            self.conn.commit()
            print("회사별 일별 총 카운트가 summary 테이블에 삽입되었습니다.")


            # 7. summary_yn 업데이트
            update_query = """
                UPDATE naver_news
                SET summary_yn = 'Y'
                WHERE create_at <= CURRENT_DATE - INTERVAL '1 day' + INTERVAL '23 hours 59 minutes 59 seconds';
            """
            self.cur.execute(update_query)
            self.conn.commit()
            print("naver_news 테이블에서 summary_yn이 'Y'로 업데이트되었습니다.")

        except Exception as e:
            self.conn.rollback()
            print(f"오류 발생: {e}")

    def close(self):
        """ 데이터베이스 연결 종료 """
        self.cur.close()
        self.conn.close()
        print("데이터베이스 연결이 종료되었습니다.")
