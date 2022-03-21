package com.fanhq.example.pulsar;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;

/**
 * @author: fanhaiqiu
 * @date: 2022/3/21
 */
public class CustomClient2 {

    public static void main(String[] args) throws Exception{
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://172.19.3.194:6650,172.19.3.195:6650,172.19.3.196:6650")
                .build();

        Consumer consumer = client.newConsumer()
                .topic("my-topic")
                .subscriptionName("my-subscription")
                .subscribe();

        while (true) {
            // Wait for a message
            Message msg = consumer.receive();

            try {
                // Do something with the message
                System.out.println("Message received: " + new String(msg.getData()));

                // Acknowledge the message so that it can be deleted by the message broker
                consumer.acknowledge(msg);
            } catch (Exception e) {
                // Message failed to process, redeliver later
                consumer.negativeAcknowledge(msg);
            }
        }
    }
}
