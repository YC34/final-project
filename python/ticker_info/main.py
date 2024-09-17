import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from ticker_info import get_filtered_df
from postgresql_insert import PostgresDatabase

def main():
    df = get_filtered_df()
    print(df.head())
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
    db.insert_data("ticker_info", df)
    db.close()


if __name__ == "__main__":
    main()                                      
