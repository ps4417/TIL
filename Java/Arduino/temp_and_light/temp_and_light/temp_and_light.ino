const int tempPin = A0;
const int ledPin = A2;
boolean tp = false;

void setup() {
  pinMode(ledPin,OUTPUT); 
//  analogReference(INTERNAL);

}

void loop() {
//  Serial.println("Ready");
   int data = analogRead(tempPin);
  float temp = (500.0*data)/1024.0;
  
  
  if(Serial.available()>0){
    String cmd = "";
    cmd = Serial.readString();
    Serial.println(cmd);
    if(cmd == "s"){
      tp = true;
      
    }else if(cmd == "t"){
      tp = false;
    }
   
  }
  if(tp){
    Serial.println(temp);
  
  }
   if(temp>33.0){
      digitalWrite(ledPin,HIGH);
    }else{
      digitalWrite(ledPin,LOW);  
    }

}
