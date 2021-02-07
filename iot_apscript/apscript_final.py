import Adafruit_BMP.BMP085 as BMP085 
import pyrebase
sensor = BMP085.BMP085()
from firebase import firebase


#config = {     
#  "apiKey": "AIzaSyAFSpKgJezvGQO4Y9G7UDdipyG4XW8J3jc",
#  "authDomain": "676389535628-77usa6d7l532k5ogh685nsq2id8rrqt6.apps.googleusercontent.com",
#  "databaseURL": "https://sanjeevani-apscript-default-rtdb.firebaseio.com",
#  "storageBucket": "sanjeevani-apscript.appspot.com"
#}

#firebase = pyrebase.initialize_app(config)

from firebase.firebase import FirebaseAuthentication
DSN = 'https://sanjeevani-apscript-default-rtdb.firebaseio.com' # 'https://myapp.firebaseio.com/'
SECRET = 'mail_id_password' # 'secretkey'
EMAIL ='mail_id' # 'prateekrai266@gmail.com'

authentication = FirebaseAuthentication(SECRET,EMAIL, True, True)

firebase = firebase.FirebaseApplication('https://sanjeevani-apscript-default-rtdb.firebaseio.com/',authentication)

def update_firebase():  
  
    temp = format(sensor.read_temperature())
    pres = format(sensor.read_pressure())
    alti = format(sensor.read_altitude())
    if temp is not None and pres is not None and alti is not None:  
        #str_temp = ' {0:0.2f} *C '.temp    
        #str_pres  = ' {0:0.2f} %'.pres
        #str_alti = ' {0:0.2f} m'.alti
        print('Temp = {0:0.2f} *C'.format(sensor.read_temperature()))
        print('Pressure = {0:0.2f} Pa'.format(sensor.read_pressure()))
        print('Altitude = {0:0.2f} m'.format(sensor.read_altitude()))
        print('Sealevel Pressure = {0:0.2f} Pa'.format(sensor.read_sealevel_pressure()))
        print('\n')
              
    else:  
        print('Failed to get reading. Try again!')    
  
    data = {"temp": sensor.read_temperature(), "pressure": sensor.read_pressure(), "altitude": sensor.read_altitude()}  
    firebase.post('/sensor/dht', data)
    
while True:  
        update_firebase()  
