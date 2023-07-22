package com.tvd12.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;

public class MqttExample {

    public static void main(String[] args) {
        String broker = "tcp://127.0.0.1:1883";
        String clientId = "JavaExample";
        String topic = "mytopic";

        try {
            // Create MQTT client
            MqttClient mqttClient = new MqttClient(broker, clientId);

            // Connect to MQTT broker
            mqttClient.connect();

            // Subscribe to a topic
            mqttClient.subscribe(topic);

            // Create a callback for message arrival
            mqttClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost!");
                }

                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Received message: " + new String(message.getPayload()));
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Not used in this example
                }
            });

            // Publish a message
            String payload = "Hello, MQTT!";
            MqttMessage message = new MqttMessage(payload.getBytes());
            mqttClient.publish(topic, message);

            // Disconnect from the MQTT broker
//            mqttClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}


