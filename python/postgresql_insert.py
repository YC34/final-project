import psycopg2
from sqlalchemy import create_engine

class PostgresDatabase:
    def __init__(self, dbname, user, password, host, port):
        self.dbname = dbname
        self.user = user
        self.password = password
        self.host = host
        self.port = port
        self.conn = None
        self.cur = None
        self.engine = None

    def connect(self):
        """ Connect to the PostgreSQL database server """
        try:
            self.conn = psycopg2.connect(
                dbname=self.dbname,
                user=self.user,
                password=self.password,
                host=self.host,
                port=self.port
            )
            self.cur = self.conn.cursor()
            self.engine = create_engine(f'postgresql://{self.user}:{self.password}@{self.host}:{self.port}/{self.dbname}')
            print("Connection to PostgreSQL.")
        except (Exception, psycopg2.DatabaseError) as error:
            print(f"Error while connecting to PostgreSQL: {error}")

    def close(self):
        """ Close the PostgreSQL connection """
        if self.cur is not None:
            self.cur.close()
        if self.conn is not None:
            self.conn.close()
        print("PostgreSQL connection closed.")



    def insert_data(self, table_name, df):
        """ Insert data from a DataFrame into a PostgreSQL table """
        try:
            df.to_sql(table_name, self.engine, if_exists='append', index=False)
            print(f"Data inserted into table '{table_name}'.")
        except Exception as e:
            print(f"Error while inserting data: {e}")


