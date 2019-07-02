#include <ESP8266WiFi.h>

WiFiClient client;
WiFiServer server(80);

const char* ssid = "mostafa";
const char* password = "pro192016";

String  data = "";
void setup() {
  Serial.begin(9600);
  pinMode(D0, OUTPUT);
  WiFi.begin(ssid, password);
  while (!(WiFi.status() == WL_CONNECTED)) {
    delay(100);
    digitalWrite(D0, LOW);
  }
  digitalWrite(D0, HIGH);
  server.begin();
}

void loop() {
  /* If the server available, run the "checkClient" function */
  client = server.available();
  if (!client) return;
  data = checkClient ();

  if (data == "0") off();
  else if (data == "1") on1();
  else if (data == "2") on2();
}
void off(void) {
  digitalWrite(D0, HIGH);
  Serial.print(0);

}
void on1(void) {
  digitalWrite(D0, LOW);
  Serial.print(1);
  
}

void on2(void) {
  digitalWrite(D0, LOW);
  Serial.print(2);

}

/********************************** RECEIVE DATA FROM the APP ******************************************/
String checkClient (void) {
  while (!client.available()) delay(1);
  String request = client.readStringUntil('\r');
  request.remove(0, 5);
  request.remove(request.length() - 9, 9);
  return request;
}
