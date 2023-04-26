import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
from conn import databaseLink

conn = credentials.Certificate("AccountKey.json")

firebase_admin.initialize_app(conn, {
    'databaseURL' : databaseLink
})

def readData():
    ref = db.reference("message/permission")
    data = ref.get()
    print(data)

readData()