package com.fanhq.example.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.SaslConfigs;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author: fanhaiqiu
 * @date: 2021/6/17
 */
public class CustomAdmin {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "172.19.3.194:9092,172.19.3.195:9092,172.19.3.196:9092");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"admin\" password=\"iot@10086\";");
        props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-512");
        props.put("request.timeout.ms", 600000);
        AdminClient adminClient = AdminClient.create(props);
        //创建topic
//        NewTopic topic = new NewTopic("test001", 3, (short) 1);
//        CreateTopicsResult createTopicsResult = adminClient.createTopics(Arrays.asList(topic));
//        System.out.println(createTopicsResult.all().isDone());
        //删除topic
//        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList("test001"));
//        System.out.println(deleteTopicsResult);
        //查询
//        ListConsumerGroupOffsetsResult result = adminClient.listConsumerGroupOffsets("test");
//        System.out.println(result.partitionsToOffsetAndMetadata().get());
        //授权
//        ResourcePattern resourcePattern = new ResourcePattern(ResourceType.GROUP, "test001", PatternType.LITERAL);
//        AccessControlEntry accessControlEntry = new AccessControlEntry("User:fanhaiqiu", "*", AclOperation.READ, AclPermissionType.ALLOW);
//        AclBinding aclBinding = new AclBinding(resourcePattern, accessControlEntry);
//        CreateAclsResult createAclsResult = adminClient.createAcls(Arrays.asList(aclBinding));
//        createAclsResult.all().get();
//        System.out.println(createAclsResult.all().isDone());

        //删除权限
//        ResourcePatternFilter resourcePatternFilter = new ResourcePatternFilter(ResourceType.GROUP, "test001", PatternType.LITERAL);
//        AccessControlEntryFilter accessControlEntryFilter = new AccessControlEntryFilter("User:fanhaiqiu", "*", AclOperation.READ, AclPermissionType.ALLOW);
//        AclBindingFilter aclBinding = new AclBindingFilter(resourcePatternFilter, accessControlEntryFilter);
//        DeleteAclsResult deleteAclsResult = adminClient.deleteAcls(Arrays.asList(aclBinding));
//        deleteAclsResult.all().get();
//        System.out.println(deleteAclsResult.all().isDone());

        //新增用户
//        ScramCredentialInfo credentialInfo = new ScramCredentialInfo(ScramMechanism.SCRAM_SHA_512, 4096);
//        UserScramCredentialUpsertion userScramCredentialUpsertion = new UserScramCredentialUpsertion("test_user", credentialInfo, "iot@10086");
//        AlterUserScramCredentialsResult alterUserScramCredentialsResult = adminClient.alterUserScramCredentials(Arrays.asList(userScramCredentialUpsertion));
//        alterUserScramCredentialsResult.all().get();
//        System.out.println(alterUserScramCredentialsResult.all().isDone());

        UserScramCredentialDeletion userScramCredentialDeletion = new UserScramCredentialDeletion("test_user", ScramMechanism.SCRAM_SHA_512);
        AlterUserScramCredentialsResult alterUserScramCredentialsResult = adminClient.alterUserScramCredentials(Arrays.asList(userScramCredentialDeletion));
        alterUserScramCredentialsResult.all().get();
        System.out.println(alterUserScramCredentialsResult.all().isDone());
    }
}
