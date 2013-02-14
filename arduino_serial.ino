#include "LPD8806.h"
#include "SPI.h"

// Example to control LPD8806-based RGB LED Modules in a strip

/*****************************************************************************/

// Number of RGB LEDs in strand:
int nLEDs = 28;
int r1 = 127;
int g1 = 0;
int b1 = 0;

// You can optionally use hardware SPI for faster writes, just leave out
// the data and clock pin parameters.  But this does limit use to very
// specific pins on the Arduino.  For "classic" Arduinos (Uno, Duemilanove,
// etc.), data = pin 11, clock = pin 13.  For Arduino Mega, data = pin 51,
// clock = pin 52.  For 32u4 Breakout Board+ and Teensy, data = pin B2,
// clock = pin B1.  For Leonardo, this can ONLY be done on the ICSP pins.
LPD8806 strip = LPD8806(nLEDs);

//boolean newData = false;
//int oldDiskNumber = 0;
//boolean actionTakenDuringThisLoop = false;
int i = 0; //Loop position

void setup() {
  // Start up the LED strip
  strip.begin();

  // Update the strip, to start they are all 'off'
  for(int q=0; q<strip.numPixels(); q++) strip.setPixelColor(q, strip.Color(0,127,0));
  strip.show();
  delay(2000);
  //Start serial
  Serial.begin(57600);
  Serial.println("Goodnight moon!");
  

}




void loop() {
//    if (Serial.available())
//    {
//      char inputState = Serial.read();
////      newData = true;
//      int i = 0;
//      
//    }
//    actionTakenDuringThisLoop = false;
    
//    if((inputState & 8) && actionTakenDuringThisLoop == false) //Shooter up to speed
    if (false)
    { 


      float intensity = sin(0.63662*(i))/2+.5;
        int newR= (int) r1;
        int newG= (int) 127 * intensity;
        int newB= (int) 127 * intensity;
      for(int j=0; j<28; j++){
        strip.setPixelColor(j, strip.Color(newR,newG,newB));
        strip.show();
      }
      delay (20);
//      actionTakenDuringThisLoop = true; 
    i=(i+1)%30;
    }
    if(true){
      for(int j=0; j<6; j++){
        
//        if ((j+i)>6){
          float intensity = sin(0.63662*(j+i-6));
          int newR= (int) (127-(127-r1) * intensity);
          int newG= (int) (127-(127-g1) * intensity);
          int newB= (int) (127-(127-b1) * intensity);
          strip.setPixelColor(j+i, strip.Color(newR,newG,newB));
          strip.show();
//        }
        strip.setPixelColor(i, strip.Color(127,0,0));
        strip.show();
        delay(2);
      }
      i++;
    }
    
//newData = false;
}


void colorPulse(int r, int g, int b)
{
  int i;
  int j;
  while (true){
    for(i=0; i<30; i++){
      for(j=0; j<strip.numPixels(); j++){
        float intensity = (sin(0.63662*(j+i)))/2+.5;
        int newR= (int) (r * intensity);
        int newG= (int) (g * intensity);
        int newB= (int) (b * intensity);
        strip.setPixelColor(j, strip.Color( newR, newG, newB));
        strip.show();
        
        }
        delay (15);
      }
    
    }
}
void whitePulse(int r, int g, int b)
{
  int i;
  int j;
  while (true){
    for(i=0; i<30; i++){
      for(j=0; j<strip.numPixels(); j++){
        float intensity = sin((0.63662*(j+i)))/2+.5;
        int newR= (int) (127-(127-r) * intensity);
        int newG= (int) (127-(127-g) * intensity);
        int newB= (int) (127-(127-b) * intensity);
        strip.setPixelColor(j, strip.Color( newR, newG, newB));
        strip.show();
        }
        delay (15);
      }
    
    }
}

