package com.fanhq.example;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.MessageToken;

import java.io.UnsupportedEncodingException;

/**
 * Hello world!
 */
public class Application {


    public static void main(String[] args) throws UnsupportedEncodingException {
        Profile profile = Profile.getAppKeyProfile("要连接的<END_POINT>", "您的<AppKey>", "您的<AppSecret>");
        MessageCallback messageCallback = new MessageCallback() {
            public Action consume(MessageToken messageToken) {
                System.out.println("receive : " + new String(messageToken.getMessage().getPayload()));
                return Action.CommitSuccess;
            }
        };

        MessageClient messageClient = MessageClientFactory.messageClient(profile);
        messageClient.setMessageListener(messageCallback);
        messageClient.connect(messageCallback);

        try
        {
            System.in.read();
        } catch (Exception e) {}
    }

}


