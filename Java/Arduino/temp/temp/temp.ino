const int tempPin =A0;
void setup() {
//  analogReference(INTERNAL);

}

void loop() {
  delay(2000);
  int data = analogRead(tempPin);
  float temp = (500.0*data)/1024.0;
  Serial.println(temp);
}
