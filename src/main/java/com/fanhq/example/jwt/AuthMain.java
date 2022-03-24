package com.fanhq.example.jwt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: fanhaiqiu
 * @date: 2022/3/24
 */
public class AuthMain {

    public static void main(String[] args) throws Exception{
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://172.19.3.196:8087/admin/v2/namespaces/tenant2/ns2/permissions/test-user");
        httpPost.setHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.6vfN42m7xh0xlFZGKREn4keyXVzrw_iWP1sylo-RyXE");
        List<String> data = new ArrayList<>();
        data.add("produce");
        data.add("consume");
        StringEntity stringEntity = new StringEntity(JSONArray.toJSONString(data));
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        HttpResponse response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode());
    }
}

