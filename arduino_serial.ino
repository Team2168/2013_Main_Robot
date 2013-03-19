#include "LPD8806.h"
#include "SPI.h"
//
// Identify the different areas within the strip by length and starting
// pixel number.
//
int stripLength = 96; // Number of RGB LEDs in strand
int shooterLeftPxZero = 0; // The first LED in the left LED set
int shooterRightPxZero = 8; // The first LED in the right LED set
int shooterLength = 2; // Number of LEDs along the shooter


// For "classic" Arduinos (Uno, Duemilanove,
// etc.), data = pin 11, clock = pin 13. For Arduino Mega, data = pin 51,
// clock = pin 52. For 32u4 Breakout Board+ and Teensy, data = pin B2,
// clock = pin B1. For Leonardo, this can ONLY be done on the ICSP pins.
LPD8806 strip = LPD8806(stripLength);

//Initialize colors
int targetR = 0,
    targetG = 0,
    targetB = 0;
int R_OUT = 0,
    G_OUT = 0,
    B_OUT = 0;

int inputState = 0;
int numDiscs = 0;
boolean shooterToSpeed = false,
        discFired = false,
        againstBar = false,
        endgame = false,
        autonomousMode = false;

int i = 0; //Loop position

int inputValue = 0;
boolean serialComplete = false;

/**
* Initialization code
*/
void setup() {
  // Start up the LED strip
  strip.begin();

  // Update the strip, to start they are all green (just to make sure they work)
  for(int q = 0; q < strip.numPixels(); q++) {
    strip.setPixelColor(q, strip.Color(0,127,0));
  }
  strip.show();
  delay(1000);
  
  //Turn the LEDs back off
  for(int q = 0; q < strip.numPixels(); q++) {
    strip.setPixelColor(q, strip.Color(0,0,0));
  }
  strip.show();
  
  //Start serial communications
  Serial.begin(57600);
}

/**
* Main loop
*/
void loop() {
  // COMMUNICATION PROTOCOL - BITMAP
  // BIT(S) Meaning
  // ------------------------------
  // 0 - 2 # discs (0 - 4)
  // 3 Shooter up to speed
  // 4 Disc fired
  // 5 Against bar
  // 6 Endgame (last 30 sec)
  // 7 Autonomous mode
  
  if (serialComplete) {
    inputState = inputValue;

    //cleanup so the serial event method can start again when new data arrives
    inputValue = 0;
    serialComplete = false;
    i = 0;//If new data, restart the loop


    // Check the status of the bits in from the message:
    shooterToSpeed = inputState & 0x0008;
    discFired = inputState & 0x0010;
    againstBar = inputState & 0x0020;
    endgame = inputState & 0x0040;
    autonomousMode = inputState & 0x0080;

    numDiscs = inputState & 0x0007; //set target strip color based on number of discs
    numDiscs %= 5; //don't let more than 4 discs come through
    
    switch (numDiscs) {
      case 2:
        targetR = 127;
        targetG = 30;
        targetB = 0;
        break;
      case 3:
        targetR = 127;
        targetG = 127;
        targetB = 0;
        break;
      case 4:
        targetR = 0;
        targetG = 127;
        targetB = 0;
        break;
      case 0:
      case 1:
      default:
        targetR = 127;
        targetG = 0;
        targetB = 0;
        break;
    }
  }

  int intensity = fibonacci(i);

  //Ways to augment the shooter LEDs
  if(shooterToSpeed) {
    //Shooter up to speed- flashes between target color and white
    //R_OUT = (targetR + intensity) % 127; //this isn't working for some reason. Doesn't seem like the modulus is operating right
    //G_OUT = intensity;
    //B_OUT = intensity;
  } else if(numDiscs == 0) {
    //if we have no discs, throb on/off
    R_OUT = (int) targetR / intensity;
    G_OUT = (int) targetG / intensity;
    B_OUT = (int) targetB / intensity;
  } else {
    //otherwise just output the targeted color values
    R_OUT = targetR;
    G_OUT = targetG;
    B_OUT = targetB;
  }
  
  //Write data out to the shooter pixels
  for(int j = 0; j < shooterLength; j++){
    //one of these should be opposite the other depending on how it's wired
    // so shots fire in the same direction
    strip.setPixelColor(j + shooterLeftPxZero, strip.Color(R_OUT, G_OUT, B_OUT));
    strip.setPixelColor(j + shooterRightPxZero, strip.Color(R_OUT, G_OUT, B_OUT));
    strip.show();
  }
  delay (50);
  
  i=(i+1)%50;
}
/**************************************************
**************MAIN LOOP ENDS HERE*****************
**************************************************/
/**
* SerialEvent occurs whenever a new data comes in the
* hardware serial RX. This routine is run between each
* time loop() runs, so using delay inside loop can delay
* response. Multiple bytes of data may be available.
* http://arduino.cc/en/Tutorial/SerialEvent
*/
void serialEvent() {
  //Don't run again until the main loop has processed the incoming string
  if (!serialComplete) {
    while (Serial.available()) {
      // get the new byte:
      char inChar = (char)Serial.read();

      // if the incoming character is a newline, we are done
      // set a flag so the main loop can do something about it
      // and convert the string to an integer
      if (inChar == '\n') {
        serialComplete = true;
      } else {
        //otherwise add the number to the running sum
        inputValue = (inputValue * 10) + atoi(&inChar);
      }
    }
  }
}

int seeds[] = {1, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 127, 89, 55, 34, 21, 13, 8, 5, 3, 2, 1, 1, 1};
int fibonacci(int pos) {
  pos %= sizeof(seeds)/sizeof(int);
  return seeds[pos];
}


void printRGB(int r, int g, int b) {
  Serial.print(r);
  Serial.write(", ");
  Serial.print(g);
  Serial.write(", ");
  Serial.println(b);
}
