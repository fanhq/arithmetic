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
 * @date: 2021/6/17
 */
public class CustomConsumer {

    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "10.12.11.65:9092,10.12.11.66:9092,10.12.11.67:9092");
        props.setProperty("group.id", "ccc");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"bkneSJJN\" password=\"EDwYzUjTYxgEiIkk\";");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-512");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("HXTb_delete"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records){
                System.out.printf("partition =%d offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
            }
        }
        //Properties props = new Properties();
        //props.setProperty("bootstrap.servers", "183.230.65.38:9092,183.230.65.38:9093,183.230.65.38:9094");
        //props.setProperty("group.id", "GROUP1");
        //props.setProperty("enable.auto.commit", "true");
        //props.setProperty("auto.commit.interval.ms", "1000");
        //props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"VmoDNjty\" password=\"PVClgmJfzPBEYwKj\";");
        //props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        //props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-512");
        //KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //consumer.subscribe(Arrays.asList("fUjJ_TOPIC1"));
        //while (true) {
        //    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        //    for (ConsumerRecord<String, String> record : records){
        //        System.out.printf("partition =%d offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), record.value());
        //    }
        //}
    }


}
