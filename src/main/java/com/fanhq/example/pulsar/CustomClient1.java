package com.fanhq.example.pulsar;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;

import javax.xml.bind.SchemaOutputResolver;

/**
 * @author: fanhaiqiu
 * @date: 2022/3/21
 */
public class CustomClient1 {


    public static void main(String[] args) throws Exception {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://172.19.3.194:6650,172.19.3.195:6650,172.19.3.196:6650")
                .build();
        Producer<byte[]> producer = client.newProducer()
                .topic("my-topic")
                .create();

        MessageId messageId = producer.send("My message".getBytes());

        producer.close();
        client.close();
    }

}
