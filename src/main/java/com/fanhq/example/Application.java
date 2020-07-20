package com.fanhq.example;

import com.fanhq.example.common.OnMessageCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class Application {


    public static void main(String[] args) throws Exception {
//        Profile profile = Profile.getAppKeyProfile("要连接的<END_POINT>", "您的<AppKey>", "您的<AppSecret>");
//        MessageCallback messageCallback = new MessageCallback() {
//            public Action consume(MessageToken messageToken) {
//                System.out.println("receive : " + new String(messageToken.getMessage().getPayload()));
//                return Action.CommitSuccess;
//            }
//        };
//
//        MessageClient messageClient = MessageClientFactory.messageClient(profile);
//        messageClient.setMessageListener(messageCallback);
//        messageClient.connect(messageCallback);
//
//        try
//        {
//            System.in.read();
//        } catch (Exception e) {}
        String subTopic = "testtopic/1";
        //String pubTopic = "testtopic/1";
        String content = "Hello World";
        int qos = 2;
        String broker = "tcp://127.0.0.1:1883";
        String clientId = "emqx_test1";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);

            // MQTT 连接选项
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("emqx_test1");
            connOpts.setPassword("emqx_test_password1".toCharArray());
            // 保留会话
            connOpts.setCleanSession(true);

            // 设置回调
            client.setCallback(new OnMessageCallback());

            // 建立连接
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);

            System.out.println("Connected");
            System.out.println("Publishing message: " + content);

            // 订阅
            client.subscribe(subTopic);

            // 消息发布所需参数
//            MqttMessage message = new MqttMessage(content.getBytes());
//            message.setQos(qos);
//            client.publish(pubTopic, message);
            System.out.println("Message published");

            TimeUnit.SECONDS.sleep(60);
            client.disconnect();
            System.out.println("Disconnected");
            client.close();
            System.exit(0);
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


