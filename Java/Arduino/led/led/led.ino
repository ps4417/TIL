const int ledPin = A2;

void setup() {
  Serial.begin(9600);
  pinMode(ledPin,OUTPUT);
}

void loop() {
  Serial.println("Ready");
  if(Serial.available()>0){
    String cmd = "";
    cmd = Serial.readString();
    Serial.println(cmd);
    if(cmd =="s"){
      digitalWrite(ledPin,HIGH);
    }else if(cmd =="t"){
      digitalWrite(ledPin,LOW);
  }   
   delay(2000);   

}
}
