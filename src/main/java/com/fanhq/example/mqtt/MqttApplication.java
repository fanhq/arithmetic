package com.fanhq.example.mqtt;

import com.fanhq.example.common.OnMessageCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.TimeUnit;

/**
 * @author fanhaiqiu
 * @date 2020/8/25
 */
public class MqttApplication {

    public static void main(String[] args) throws Exception {
        String subTopic1 = "$sys/10086/device003/thing/property";
        String subTopic2 = "$sys/10086/device003/thing/event";
        String subTopic3 = "$sys/10086/device003/thing/lifecycle";
        //String pubTopic3 = "testtopic/1";
        String content = "Hello World";
        int qos = 2;
        String broker = "tcp://127.0.0.1:27002";
        String clientId = "emqx_test1";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);

            // MQTT 连接选项
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("admin");
            connOpts.setPassword("0jJWY2UDNzITM".toCharArray());
            // 保留会话
            connOpts.setCleanSession(false);

            // 设置回调
            client.setCallback(new OnMessageCallback());

            // 建立连接
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);

            System.out.println("Connected");
            //System.out.println("Publishing message: " + content);

            // 订阅
            client.subscribe(new String[]{subTopic1, subTopic2, subTopic3});

            // 消息发布所需参数
//            MqttMessage message = new MqttMessage(content.getBytes());
//            message.setQos(qos);
//            client.publish(pubTopic, message);
            //System.out.println("Message published");

            TimeUnit.SECONDS.sleep(6000);
            client.disconnect();
            System.out.println("Disconnected");
            client.close();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
