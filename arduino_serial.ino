#include "LPD8806.h"
#include "SPI.h"
//
// Identify the different areas within the strip by length and starting
// pixel number.
//
int stripLength        = 96; // Number of RGB LEDs in strand
int shooterLeftPxZero  =  0; // The first LED in the left LED set
int shooterRightPxZero = 20; // The first LED in the right LED set
int shooterLength      = 14; // Number of LEDs along the shooter
int drivetrainLength   = 48; // Number of LEDs on drivetrain
int drivetrainPxZero   = 45; //The first LED in the drivetrain strand

// For "classic" Arduinos (Uno, Duemilanove,
// etc.), data = pin 11, clock = pin 13.  For Arduino Mega, data = pin 51,
// clock = pin 52.  For 32u4 Breakout Board+ and Teensy, data = pin B2,
// clock = pin B1.  For Leonardo, this can ONLY be done on the ICSP pins.
LPD8806 strip = LPD8806(stripLength);

//Initialize colors
int targetR = 0,
    targetG = 0,
    targetB = 0,
    driveTargetR = 0,
    driveTargetG = 0,
    driveTargetB = 0;
int R_OUT = 0,
    G_OUT = 0,
    B_OUT = 0;

int inputState = 0;
int numDiscs = 0;
boolean shooterToSpeed = false,
        discFired = false,
        lastDiscFired = false,
        againstBar = false,
        endgame = false,
        autonomousMode = false;
int shotFiredCount = -1,
    i = 0, //Loop position
    j = 0;

int inputValue = 0;
boolean serialComplete = false;

int shots[] = {55, 89, 127};

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
  // BIT(S)     Meaning
  // ------------------------------
  // 0 - 2      # discs (0 - 4)
  //   3        Shooter up to speed
  //   4        Disc fired
  //   5        Against bar
  //   6        Endgame (last 30 sec)
  //   7        Autonomous mode
  
  if (serialComplete) {
    inputState = inputValue;

    //cleanup so the serial event method can start again when new data arrives
    inputValue = 0;
    serialComplete = false;
    i = 0;//If new data, restart the loop


    // Check the status of the bits in from the message:
    shooterToSpeed = inputState & 0x0008;
    discFired      = inputState & 0x0010;
    againstBar     = inputState & 0x0020;
    endgame        = inputState & 0x0040;
    autonomousMode = inputState & 0x0080;

    numDiscs = inputState & 0x0007; //set target strip color based on number of discs
    numDiscs %= 5; //don't let more than 4 discs come through
    
    
    if(discFired && !lastDiscFired) {
      //Reset the count the first time we see a shot fired
      shotFiredCount = 0;
    }
    lastDiscFired = discFired;
    
    switch (numDiscs) {
      case 2:
        //yellow
        targetR = 127;
        targetG = 60;
        targetB = 0;
        break;
      case 3:
        //yellow green
        targetR = 80;
        targetG = 80;
        targetB = 0;
        break;
      case 4:
        //green
        targetR = 0;
        targetG = 127;
        targetB = 0;
        break;
      case 1:
        //light orange
        targetR = 120;
        targetG = 20;
        targetB = 0;
        break;
      case 0:
      default:
        //red
        targetR = 127;
        targetG = 0;
        targetB = 0;
        break;
    }
    
    //Determine drivetrain color
    if(againstBar) {
      //hot pink
      driveTargetR = 48;
      driveTargetG = 128;
      driveTargetB = 20;
    } else if(endgame) {
      //red
      driveTargetR = 100;
      driveTargetG = 0;
      driveTargetB = 0;
    } else if(autonomousMode) {
      //light white
      driveTargetR = 50;
      driveTargetG = 50;
      driveTargetB = 50;
    } else {
      //off
      driveTargetR = 0;
      driveTargetG = 0;
      driveTargetB = 0;
    }
  }

  //get the latest value for brightness for patterns that change intensity
  int intensity = fibonacci(i);
  
  ////////////////////////////////////////
  //Augment the shooter LEDs
  ////////////////////////////////////////
  if(shooterToSpeed && !discFired) {
    //Shooter up to speed- flashes between target color and white 
    R_OUT = min(targetR + intensity, 127);
    G_OUT = min(targetG + intensity, 127);
    B_OUT = min(targetB + intensity, 127);
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
  for(j = 0; j < shooterLength; j++){
    if(j == shotFiredCount) {
      //light a pixel white, overriding the normal color
      strip.setPixelColor(j + shooterLeftPxZero, strip.Color(127, 127, 127));
      strip.setPixelColor((shooterLength - 1 + shooterRightPxZero) - j, strip.Color(127, 127, 127));
    } else {
      strip.setPixelColor(j + shooterLeftPxZero, strip.Color(R_OUT, G_OUT, B_OUT));
      //Write Right side in the reverse order as the left, so patterns flow in the same direction (shots)
      strip.setPixelColor((shooterLength - 1 + shooterRightPxZero) - j, strip.Color(R_OUT, G_OUT, B_OUT));
    }
    strip.show();
  }
  
  if(shotFiredCount >= 0 && shotFiredCount <= shooterLength) {
    shotFiredCount++;
  }
  
  //////////////////////////////////////////////
  //Augment target drivetrain colors
  //////////////////////////////////////////////
  R_OUT = driveTargetR;
  G_OUT = driveTargetG;
  B_OUT = driveTargetB;
  
  //Write data out to the drivetrain pixels
  for(j = 0; j < drivetrainLength; j++) {
    strip.setPixelColor(j + drivetrainPxZero, strip.Color(R_OUT, G_OUT, B_OUT));
  }

  //delay (5);
  
  i = (i + 1) % 50;
}
/**************************************************
 **************MAIN LOOP ENDS HERE*****************
 **************************************************/
/**
 * SerialEvent occurs whenever a new data comes in the
 * hardware serial RX.  This routine is run between each
 * time loop() runs, so using delay inside loop can delay
 * response.  Multiple bytes of data may be available.
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

//Note, the number of elements in this array should be an even multiple of the loop itterations run in main
// otherwise the pattern won't loop cleanly
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
