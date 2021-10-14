package com.fanhq.example.pay;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;

/**
 * @author: fanhaiqiu
 * @date: 2021/9/9
 */
public class WeChatPay {
    public static void main(String[] args) throws Exception{
//        //...
//        String apiV3Key = "";
//        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
//                new WechatPay2Credentials("merchantId", new PrivateKeySigner("merchantSerialNumber", "merchantPrivateKey")),
//                apiV3Key.getBytes("utf-8"));
//        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
//                .withMerchant("merchantId", "merchantSerialNumber", "merchantPrivateKey")
//                .withWechatpay(new WechatPay2Validator(verifier));
//// ... 接下来，你仍然可以通过builder设置各种参数，来配置你的HttpClient
//
//// 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签
//        HttpClient httpClient = builder.build();
//
//        URIBuilder uriBuilder = new URIBuilder("https://api.mch.weixin.qq.com/v3/certificates");
//        HttpGet httpGet = new HttpGet(uriBuilder.build());
//        httpGet.addHeader("Accept", "application/json");
//
//        HttpResponse response = httpClient.execute(httpGet);
//
//        String bodyAsString = EntityUtils.toString(response.getEntity());
//        System.out.println(bodyAsString);
    }
}
