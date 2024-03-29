package com.fanhq.example.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.config.SaslConfigs;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * @author: fanhaiqiu
 * @date: 2021/7/12
 */
public class AdminConsumer {

    public static void main(String[] args) throws Exception{
       // Properties props = new Properties();
       // props.setProperty("bootstrap.servers", "172.19.3.194:9093,172.19.3.197:9093,172.19.3.196:9093");
       //// props.setProperty("group.id", "admin-group");
       //// props.setProperty("enable.auto.commit", "true");
       //// props.setProperty("auto.commit.interval.ms", "1000");
       // props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
       // props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
       // props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"admin\" password=\"iot@10086\";");
       // props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
       // props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-512");
       // KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
       // TopicPartition topicPartition = new TopicPartition("test002", 0);
       // Map<TopicPartition, Long> topicPartitionLongMap = consumer.endOffsets(Collections.singleton(topicPartition));
       // System.out.println(topicPartitionLongMap.get(topicPartition));

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "172.19.3.194:9092,172.19.3.195:9092,172.19.3.196:9092");
        props.setProperty("group.id", "grp001");
        props.setProperty("enable.auto.commit", "true");

        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"OAcqaSZd\" password=\"yDkprRaqhXREkOIz\";");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-512");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("HBVe_test0003"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records){
                System.out.printf("partition =%d offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
            }
        }
    }
}
