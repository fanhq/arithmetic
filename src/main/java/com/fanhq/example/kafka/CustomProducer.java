package com.fanhq.example.kafka;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.config.SaslConfigs;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author: fanhaiqiu
 * @date: 2021/6/16
 */
public class CustomProducer {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.19.3.194:9092,172.19.3.195:9092,172.19.3.196:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"fanhaiqiu\" password=\"iot@10086\";");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-512");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>("test001", Integer.toString(i), Integer.toString(i)), new Callback() {

                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    exception.printStackTrace();
                    System.out.println(metadata.toString());
                }
            });
        }
        TimeUnit.SECONDS.sleep(3);
        producer.close();
    }


}
