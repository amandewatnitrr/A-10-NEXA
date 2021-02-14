## Hardware Needed
1. Raspberry Pi
2. BMP 180
3. I2C Bus if using more than 1 sensors

(**Point to be noted if you are using I2C Pins on rpi the other pins will remain be shown as not available in that case you will have to figure out some sort of solution online.)

## Software
1. Thony Python IDE

## Libraries
1. Google Firebase
2. python-firebase
3. G-Cloud
4. functools

## For installing all the required libraries:
<ol>
  <li>Clone the repository.</li>
  <li>Move to iot_apscript folder.</li>
  <li>Use the command given below to install the libraries mentioned in requirements.txt:</li>
  <ul>
      <li>python -m pip install -r requirements.txt
  </ul>
</ol>


(** Medical based sensors due to immediate unavailability were not used, though you can use as many sensors as you want along with a Bio-Shield Sensor that allows you to connect bio-sensors easily and read data from them.)

Follow the instructions over here for connecting a BMP 180 and reading data from here ->
https://thepihut.com/blogs/raspberry-pi-tutorials/18025084-sensors-pressure-temperature-and-altitude-with-the-bmp180

## Initial Tests
![Alt](https://github.com/amandewatnitrr/A-10-NEXA/blob/main/iot_apscript/2021-02-07-023358_1920x1080_scrot.png)
![Alt](https://github.com/amandewatnitrr/A-10-NEXA/blob/main/iot_apscript/Setup%20Photos.jpeg)
