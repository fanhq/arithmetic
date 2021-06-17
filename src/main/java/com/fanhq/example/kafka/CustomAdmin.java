package com.fanhq.example.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListConsumerGroupOffsetsResult;
import org.apache.kafka.common.config.SaslConfigs;

import java.util.Properties;

/**
 * @author: fanhaiqiu
 * @date: 2021/6/17
 */
public class CustomAdmin {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "172.19.3.194:9092,172.19.3.195:9092,172.19.3.196:9092");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"admin\" password=\"iot@10086\";");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-512");
        props.put("request.timeout.ms", 600000);
        AdminClient adminClient = AdminClient.create(props);
        ListConsumerGroupOffsetsResult result = adminClient.listConsumerGroupOffsets("test");
        System.out.println(result.partitionsToOffsetAndMetadata().get());
    }
}
