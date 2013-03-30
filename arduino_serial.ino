#include "LPD8806.h"
#include "SPI.h"
//
// Identify the different areas within the strip by length and starting
// pixel number.
//
int stripLength        = 96; // Number of RGB LEDs in strand
int shooterLeftPxZero  = 15; // The first LED in the left LED set
int shooterRightPxZero = 60; // The first LED in the right LED set
int shooterLength      = 12; // Number of LEDs along the shooter
int drivetrainLength   = 10; // Number of LEDs on drivetrain
int drivetrainPxZero   = 71; //The first LED in the drivetrain strand
int hangerLength       = 14; //ALSO CHANGE ARRAY BELOW. Number of LEDs on the bar under the hanger
int hangerPxZero       = 0; //First LED in the hanger strand
int hangerState[14];           //For Knight Rider animation
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

int numDiscs = 0;
boolean shooterToSpeed = false,
        discFired = false,
        lastDiscFired = false,
        againstBar = false,
        endgame = false,
        autonomousMode = false;
int shotFiredCount = -1,
    i = 0, //Loop position
    j = 0,
    k = 0, //Hanger strip loop position
    s = 0; //Hanger only runs every other loop

int inputValue = 0,
    lastInputValue = 0;

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
  
  //Initialize Knight Rider array
  for(int q = 0; q < hangerLength; q++){
    hangerState[q] = 0;
  }
  
  //Set port D (digital pins 3-10) as inputs
  pinMode(3, INPUT);
  pinMode(4, INPUT);
  pinMode(5, INPUT);
  pinMode(6, INPUT);
  pinMode(7, INPUT);
  pinMode(8, INPUT);
  pinMode(9, INPUT);
//  pinMode(10, INPUT); //why pin 10 no work?!@?@!?

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
  
  lastInputValue = inputValue;
  inputValue = 0;
  //calculate number of discs
  if(digitalRead(3) == HIGH) {
    inputValue += 1;
  }
  if(digitalRead(4) == HIGH) {
    inputValue += 2;
  }
  if(digitalRead(5) == HIGH) {
    inputValue += 4;
  }
  
  //check the remaining fields in the message
  if(digitalRead(6) == HIGH) {
    inputValue += 8;
  }
  if(digitalRead(7) == HIGH) {
    inputValue += 16;
  }
  if(digitalRead(8) == HIGH) {
    inputValue += 32;
  }
  if(digitalRead(9) == HIGH) {
    inputValue += 64;
  }
  if(digitalRead(10) == HIGH) {
    inputValue += 128;
  }
  
  //Serial.println(inputValue);
  
  if (inputValue != lastInputValue) {
    i = 0;//If new data, restart the loop

    // Check the status of the bits in from the message:
    shooterToSpeed = inputValue & 0x0008;
    discFired      = inputValue & 0x0010;
    againstBar     = inputValue & 0x0020;
    endgame        = inputValue & 0x0040;
    autonomousMode = inputValue & 0x0080;

    numDiscs = inputValue & 0x0007; //set target strip color based on number of discs
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
    for(j = 0; j < hangerLength; j++) {
      strip.setPixelColor(j + hangerPxZero, strip.Color(min(targetR + intensity, 127), min(targetG + intensity, 127), min(targetB + intensity, 127)));
    }
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
  
  //////////////////////////////////////////////
  //Knight Rider animation
  //////////////////////////////////////////////
  
  //Decrement every value by 16, but don't go negative. Gives us 8 pixels decreasing in intensity
  int m;
  for(m = 0; m < hangerLength; m++){ 
    hangerState[m] = max(0, (hangerState[m]-8)); //Decrement every pixel's brightness
  }
  
  int currentPixel = -abs(hangerLength - k) + hangerLength; //This swings back and forth between the two sides
  hangerState[currentPixel] = 127; //Set most recent pixel to be bright
  if (!shooterToSpeed){
    //Send out pixel values. Stick with red for now
    for(j = 0; j < hangerLength; j++) {
      strip.setPixelColor(j + hangerPxZero, strip.Color(hangerState[j], 0, 0));
    }
  }
  strip.show();
          
  i = (i + 1) % 50; //This value needs to be a multiple of the array used by the fibonacci function below
  
  s++;
  if (s == 2){ 
    k = (k + 1) % (2*hangerLength); // For Knight Rider
    s = 0;
  }
}
/**************************************************
 **************MAIN LOOP ENDS HERE*****************
 **************************************************/

//Note, the number of elements in this array should be an even multiple of the loop itterations run in main
// otherwise the pattern won't loop cleanly
int seeds[] = {1, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 127, 89, 55, 34, 21, 13, 8, 5, 3, 2, 1, 1, 1};
int fibonacci(int pos) {
  pos %= sizeof(seeds)/sizeof(int);
  return seeds[pos];
}
