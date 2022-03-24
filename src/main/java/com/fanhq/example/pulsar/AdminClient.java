package com.fanhq.example.pulsar;

import com.alibaba.fastjson.JSON;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.common.policies.data.AuthAction;

import java.util.Map;
import java.util.Set;

/**
 * @author: fanhaiqiu
 * @date: 2022/3/24
 */
public class AdminClient {

    public static void main(String[] args) throws Exception {
        String url = "http://172.19.3.196:8087,172.19.3.195:8087,172.19.3.194:8087";
        String authPluginClassName = "org.apache.pulsar.client.impl.auth.AuthenticationToken";
        String authParams = "token:eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiJ9.6vfN42m7xh0xlFZGKREn4keyXVzrw_iWP1sylo-RyXE";
        PulsarAdmin admin = PulsarAdmin.builder()
                .authentication(authPluginClassName,authParams)
                .serviceHttpUrl(url)
                .build();

        Map<String, Set<AuthAction>> permissions = admin.namespaces().getPermissions("tenant2/ns2");
        System.out.println(JSON.toJSONString(permissions));


        admin.close();

    }
}
