package com.fanhq.example.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateAclsResult;
import org.apache.kafka.common.acl.AccessControlEntry;
import org.apache.kafka.common.acl.AclBinding;
import org.apache.kafka.common.acl.AclOperation;
import org.apache.kafka.common.acl.AclPermissionType;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.resource.PatternType;
import org.apache.kafka.common.resource.ResourcePattern;
import org.apache.kafka.common.resource.ResourceType;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author: fanhaiqiu
 * @date: 2021/6/17
 */
public class CustomAdmin {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "172.19.3.194:9093,172.19.3.195:9093,172.19.3.196:9093");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"admin\" password=\"iot@10086\";");
        props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-512");
        props.put("request.timeout.ms", 600000);
        AdminClient adminClient = AdminClient.create(props);
        //创建topic
//        NewTopic topic = new NewTopic("test001", 3, (short) 1);
//        CreateTopicsResult createTopicsResult = adminClient.createTopics(Arrays.asList(topic));
//        createTopicsResult.all().get();
//        System.out.println(createTopicsResult.all().isDone());
        //删除topic
//        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList("test001"));
//        System.out.println(deleteTopicsResult);
        //查询
//        ListConsumerGroupOffsetsResult result = adminClient.listConsumerGroupOffsets("test");
//        System.out.println(result.partitionsToOffsetAndMetadata().get());
        //授权
        ResourcePattern resourcePattern = new ResourcePattern(ResourceType.GROUP, "test001", PatternType.LITERAL);
        AccessControlEntry accessControlEntry = new AccessControlEntry("User:fanhaiqiu", "*", AclOperation.READ, AclPermissionType.ALLOW);
        AclBinding aclBinding = new AclBinding(resourcePattern, accessControlEntry);
        CreateAclsResult createAclsResult = adminClient.createAcls(Arrays.asList(aclBinding));
        createAclsResult.all().get();
        System.out.println(createAclsResult.all().isDone());

        //删除权限
//        ResourcePatternFilter resourcePatternFilter = new ResourcePatternFilter(ResourceType.GROUP, "test001", PatternType.LITERAL);
//        AccessControlEntryFilter accessControlEntryFilter = new AccessControlEntryFilter("User:fanhaiqiu", "*", AclOperation.READ, AclPermissionType.ALLOW);
//        AclBindingFilter aclBinding = new AclBindingFilter(resourcePatternFilter, accessControlEntryFilter);
//        DeleteAclsResult deleteAclsResult = adminClient.deleteAcls(Arrays.asList(aclBinding));
//        deleteAclsResult.all().get();
//        System.out.println(deleteAclsResult.all().isDone());

        //新增用户
//        ScramCredentialInfo credentialInfo = new ScramCredentialInfo(ScramMechanism.SCRAM_SHA_512, 4096);
//        UserScramCredentialUpsertion userScramCredentialUpsertion = new UserScramCredentialUpsertion("fanhaiqiu", credentialInfo, "iot@10086");
//        AlterUserScramCredentialsResult alterUserScramCredentialsResult = adminClient.alterUserScramCredentials(Arrays.asList(userScramCredentialUpsertion));
//        alterUserScramCredentialsResult.all().get();
//        System.out.println(alterUserScramCredentialsResult.all().isDone());

        //删除用户
//        UserScramCredentialDeletion userScramCredentialDeletion = new UserScramCredentialDeletion("test_user", ScramMechanism.SCRAM_SHA_512);
//        AlterUserScramCredentialsResult alterUserScramCredentialsResult = adminClient.alterUserScramCredentials(Arrays.asList(userScramCredentialDeletion));
//        alterUserScramCredentialsResult.all().get();
//        System.out.println(alterUserScramCredentialsResult.all().isDone());

        //用户配额
//        Map<String, String> entries = new HashMap<>();
//        entries.put("user", "fanhaiqiu");
//        ClientQuotaEntity clientQuotaEntity = new ClientQuotaEntity(entries);
//        //生产配额
//        ClientQuotaAlteration.Op op1 = new ClientQuotaAlteration.Op("producer_byte_rate", 1024.00);
//        //消费配额
//        ClientQuotaAlteration.Op op2 = new ClientQuotaAlteration.Op("consumer_byte_rate", 1024.00);
//        ClientQuotaAlteration  clientQuotaAlteration = new ClientQuotaAlteration(clientQuotaEntity, Arrays.asList(op1, op2));
//        AlterClientQuotasResult alterClientQuotasResult = adminClient.alterClientQuotas(Arrays.asList(clientQuotaAlteration));
//        alterClientQuotasResult.all().get();
//        System.out.println(alterClientQuotasResult.all().isDone());

//        ClientQuotaFilterComponent component = ClientQuotaFilterComponent.ofEntity("user", "fanhaiqiu");
//        ClientQuotaFilter filter = ClientQuotaFilter.contains(Arrays.asList(component));
//        DescribeClientQuotasResult describeClientQuotasResult = adminClient.describeClientQuotas(filter);
//        Map<ClientQuotaEntity, Map<String, Double>> clientQuotaEntityMapMap = describeClientQuotasResult.entities().get();
//        clientQuotaEntityMapMap.forEach((k, v) -> {
//            System.out.println(k + ":" + v);
//        });

        //配置管理
//        Map<ConfigResource, Collection<AlterConfigOp>> configs = new HashMap<>();
//        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, "test001");
//        ConfigEntry configEntry = new ConfigEntry("delete.retention.ms", "86400000");
//        AlterConfigOp alterConfigOp = new AlterConfigOp(configEntry, AlterConfigOp.OpType.SET);
//        configs.put(configResource, Arrays.asList(alterConfigOp));
//        AlterConfigsResult alterConfigsResult = adminClient.incrementalAlterConfigs(configs);
//        alterConfigsResult.all().get();
//        System.out.println(alterConfigsResult.all().isDone());

        //配置查询
//        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, "test001");
//        DescribeConfigsResult describeConfigsResult = adminClient.describeConfigs(Arrays.asList(configResource));
//        Map<ConfigResource, Config> configResourceConfigMap = describeConfigsResult.all().get();
//        configResourceConfigMap.forEach((k, v) -> {
//            System.out.println(k.toString() + ":" + v.toString());
//        });

    }
}
