from datetime import datetime
import pymongo
import pytz

myclient = pymongo.MongoClient("mongodb://192.168.0.124:27017/")
mydb = myclient["pyc"]


class MongoDB:
    def __init__(self, client, db):
        self.client = client
        self.db = db
        self.kst = pytz.timezone('Asia/Seoul')
        self.now = datetime.now(self.kst)
        self.collection_name = self.connect()

    def get_collection_name(self):
        now = datetime.now(self.kst)
        return now.strftime('collection_%Y_%m_%d')


    def connect(self):

        current_collection_name = self.get_collection_name()

        # Check if it's time to switch to a new collection
        now = datetime.now(self.kst)
        current_collection = self.db[current_collection_name]
        last_doc = current_collection.find_one(sort=[('timestamp', pymongo.DESCENDING)])

        if last_doc:
            last_timestamp = last_doc.get('timestamp')
            if last_timestamp:
                last_timestamp = last_timestamp.replace(tzinfo=pytz.UTC).astimezone(self.kst)
                if now.date() > last_timestamp.date():
                    # If the date has changed, switch to the new collection
                    return self.get_collection_name()

        return current_collection_name

    def insert_document(self,document):
        collection = self.db[self.collection_name]
        collection.insert_many(document)
        print(f"Inserted document into collection: {collection.name}")



