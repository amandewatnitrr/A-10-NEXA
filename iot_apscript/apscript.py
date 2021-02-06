import Adafruit_BMP.BMP085 as BMP085 
import json  
import os   
from functools import partial 
sensor = BMP085.BMP085()
from firebase import firebase


print('Temp = {0:0.2f} *C'.format(sensor.read_temperature()))
print('Pressure = {0:0.2f} Pa'.format(sensor.read_pressure()))
print('Altitude = {0:0.2f} m'.format(sensor.read_altitude()))
print('Sealevel Pressure = {0:0.2f} Pa'.format(sensor.read_sealevel_pressure()))

firebase = firebase.FirebaseApplication('https://sanjeevani-apscript-default-rtdb.firebaseio.com/', None)

def update_firebase():  
  
    temp = format(sensor.read_temperature())
    pres = format(sensor.read_pressure())
    alti = format(sensor.read_altitude())
    if temp is not None and pres is not None and alti is not None:  
        #str_temp = ' {0:0.2f} *C '.temp    
        #str_pres  = ' {0:0.2f} %'.pres
        #str_alti = ' {0:0.2f} m'.alti
        print('Temp={0:0.1f}*C  Pressure={1:0.1f}%  Altitude={1:0.1f}m'.format(sensor.read_temperature(), sensor.read_pressure()),sensor.read_altitude())    
              
    else:  
        print('Failed to get reading. Try again!')    
  
    data = {"temp": sensor.read_temperature(), "pressure": sensor.read_pressure(), "altitude": sensor.read_altitude()}  
    firebase.post('/sensor/dht', data)
    
while True:  
        update_firebase()  