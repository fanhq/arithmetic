package com.fanhq.example.pulsar;

import org.apache.pulsar.client.api.AuthenticationFactory;
import org.apache.pulsar.client.api.PulsarClient;

/**
 * @author: fanhaiqiu
 * @date: 2022/3/24
 */
public class AdminClient {

    public static void main(String[] args) throws Exception {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://172.19.3.194:6650,172.19.3.195:6650,172.19.3.196:6650")
                .authentication(AuthenticationFactory.token("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0LXVzZXIifQ.JmSjukYZMsMn-LyL3rjZIjKwSIMuYqA3O0SiyNlOPaw"))
                .build();

    }
}
