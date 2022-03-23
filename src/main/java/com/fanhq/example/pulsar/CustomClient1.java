package com.fanhq.example.pulsar;

import org.apache.pulsar.client.api.*;

/**
 * @author: fanhaiqiu
 * @date: 2022/3/21
 */
public class CustomClient1 {


    public static void main(String[] args) throws Exception {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://172.19.3.194:6650,172.19.3.195:6650,172.19.3.196:6650")
                .authentication(AuthenticationFactory.token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0LXVzZXIifQ.JmSjukYZMsMn-LyL3rjZIjKwSIMuYqA3O0SiyNlOPaw"))
                .build();
        Producer<byte[]> producer = client.newProducer()
                .topic("persistent://tenant2/ns2/tp2")
                .create();

        for (int i = 0; i < 100; i++) {
            MessageId messageId = producer.newMessage().key("key_" + i)
                    .value(("message_" + i).getBytes())
                    .send();
            System.out.println(messageId);
        }
        producer.close();
        client.close();
    }

}
