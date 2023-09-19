package com.fanhq.example.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.config.SaslConfigs;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author: fanhaiqiu
 * @date: 2022/10/21
 */
public class CommonProducer {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.19.19.190:9092,172.19.19.191:9092,172.19.19.192:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        Producer<String, byte[]> producer = new KafkaProducer<>(props);
        long start = System.currentTimeMillis();
        String data = "{\n" +
                "\t\"actionParam\": \"63510b83911a5060f3a1b83a\",\n" +
                "\t\"traceId\": \"62e28106984e125687288dda\",\n" +
                "\t\"ruleId\": 64,\n" +
                "\t\"sequenceId\": \"62dbc6355a41e50be376bce7\",\n" +
                "\t\"pid\": \"00001\",\n" +
                "\t\"uid\": 16,\n" +
                "\t\"devName\": \"iot-0001\",\n" +
                "\t\"body\": {\n" +
                "\t\t\"projectId\": \"990909090\",\n" +
                "\t\t\"productId\": \"00001\",\n" +
                "\t\t\"deviceName\": \"iot-0001\",\n" +
                "\t\t\"messageType\": \"lifeCycle\",\n" +
                "\t\t\"data\": {\n" +
                "\t\t\t\"status\": \"online\",\n" +
                "\t\t\t\"time\": 1524448722123\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 50000; i++) {
            Future<RecordMetadata> result = producer.send(new ProducerRecord<String, byte[]>("rule-datapush",Integer.toString(i) , data.getBytes()));
        }
        System.out.println(System.currentTimeMillis());
        System.out.println("end: " + (System.currentTimeMillis() - start));
        TimeUnit.SECONDS.sleep(10);
        producer.close();
    }
}
