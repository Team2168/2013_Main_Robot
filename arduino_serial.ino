//#include <SoftwareSerial.h>

//SoftwareSerial mySerial(2, 3);

void setup()  
{
  Serial.begin(57600);
  Serial.println("Goodnight moon!");
  pinMode(13, OUTPUT);   

  //et the data rate for the SoftwareSerial port
  //mySerial.begin(4800);
  //mySerial.println("Hello, world?");
}

void loop() // run over and over
{
//  if (mySerial.available())
//    Serial.write(mySerial.read());
  if (Serial.available()){
    char in = Serial.read();

    if (in=='a'){
      digitalWrite(13, HIGH);
    }
    if (in=='b'){
      digitalWrite(13, LOW);
    }
    if (in=='c'){
      digitalWrite(13, HIGH);
      delay(1000);
      digitalWrite(13, LOW);
    }
  }
}
