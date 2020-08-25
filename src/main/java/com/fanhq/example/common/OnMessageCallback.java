package com.fanhq.example.common;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fanhaiqiu
 * @date 2020/7/1
 */
public class OnMessageCallback implements MqttCallback {

    private static final AtomicInteger count = new AtomicInteger(0);
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    private static DateTimeFormatter formatter =  DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息时间:" + formatter.format(LocalDateTime.now()));
        System.out.println("接收消息主题:" + topic);
        System.out.println("接收消息Qos:" + message.getQos());
        System.out.println("接收消息内容:" + new String(message.getPayload()));
        System.out.println("接收消息数量:" + count.incrementAndGet());
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}
