package com.fanhq.example.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionReplica;
import org.apache.kafka.common.acl.*;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.apache.kafka.common.quota.ClientQuotaEntity;
import org.apache.kafka.common.quota.ClientQuotaFilter;
import org.apache.kafka.common.quota.ClientQuotaFilterComponent;
import org.apache.kafka.common.resource.PatternType;
import org.apache.kafka.common.resource.ResourcePattern;
import org.apache.kafka.common.resource.ResourcePatternFilter;
import org.apache.kafka.common.resource.ResourceType;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
        //props.put("metric.reporters", "org.apache.kafka.common.metrics.JmxReporter");
        AdminClient adminClient = AdminClient.create(props);

//        while (true) {
//            Map<MetricName, ? extends Metric> metrics = adminClient.metrics();
//            metrics.forEach((k, v) -> {
//                System.out.println(k + ":" + v.metricValue());
//            });
//            TimeUnit.SECONDS.sleep(30);
//        }

        //分区大小，根据topic过滤，多个备份要先确定leader
//        DescribeLogDirsResult describeLogDirsResult = adminClient.describeLogDirs(Arrays.asList(0, 1, 2));
//        Map<Integer, Map<String, LogDirDescription>> integerMapMap = describeLogDirsResult.allDescriptions().get();
//        integerMapMap.forEach((k, v) -> {
//            System.out.println(k + ":" + v);
//        });
//        TopicPartitionReplica replica = new TopicPartitionReplica("test00", 2, 1);
//        DescribeReplicaLogDirsResult describeReplicaLogDirsResult = adminClient.describeReplicaLogDirs(Arrays.asList(replica));
//        Map<TopicPartitionReplica, DescribeReplicaLogDirsResult.ReplicaLogDirInfo> topicPartitionReplicaReplicaLogDirInfoMap = describeReplicaLogDirsResult.all().get();
//        topicPartitionReplicaReplicaLogDirInfoMap.forEach((k, v) -> {
//            System.out.println(k + ":" + v);
//        });

        //修改offset
        TopicPartition partition  = new TopicPartition("test002", 0);
        OffsetAndMetadata metadata = new OffsetAndMetadata(8295600L);
        Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
        offsets.put(partition, metadata);
        AlterConsumerGroupOffsetsResult alterOffsetsResult = adminClient.alterConsumerGroupOffsets("test002", offsets);
        alterOffsetsResult.all().get();
        System.out.println(alterOffsetsResult.all().isDone());

//        //创建topic
//        NewTopic topic = new NewTopic("test002", 3, (short) 2);
//        CreateTopicsResult createTopicsResult = adminClient.createTopics(Arrays.asList(topic));
//        createTopicsResult.all().get();
//        System.out.println(createTopicsResult.all().isDone());
        //删除topic
//        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList("test002"));
//        System.out.println(deleteTopicsResult.all().get());

//        ListTopicsResult listTopicsResult = adminClient.listTopics();
//        System.out.println(listTopicsResult.listings().get());

        //查询
//        DescribeConsumerGroupsResult describeConsumerGroupsResult = adminClient.describeConsumerGroups(Arrays.asList("test002"));
//        System.out.println(describeConsumerGroupsResult.all().get());
//        ListConsumerGroupOffsetsResult listConsumerGroupOffsetsResult = adminClient.listConsumerGroupOffsets("test002");
//        System.out.println(listConsumerGroupOffsetsResult.partitionsToOffsetAndMetadata().get());
//
//
//        DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Arrays.asList("test002"));
//        System.out.println(describeTopicsResult.all().get());

//        DeleteConsumerGroupsResult deleteConsumerGroupsResult = adminClient.deleteConsumerGroups(Arrays.asList("test002"));
//        System.out.println(deleteConsumerGroupsResult.all().get());

//        ListPartitionReassignmentsResult partitionReassignmentsResult = adminClient.listPartitionReassignments();
//        Map<TopicPartition, PartitionReassignment> topicPartitionPartitionReassignmentMap = partitionReassignmentsResult.reassignments().get();
//        topicPartitionPartitionReassignmentMap.forEach((k, v) -> {
//            System.out.println(k + ":" + v);
//        });


        //授权
//        ResourcePattern resourcePattern = new ResourcePattern(ResourceType.GROUP, "test002", PatternType.LITERAL);
//        AccessControlEntry accessControlEntry = new AccessControlEntry("User:fanhaiqiu", "192.168.200.48", AclOperation.READ, AclPermissionType.ALLOW);
//        AclBinding aclBinding = new AclBinding(resourcePattern, accessControlEntry);
//        CreateAclsResult createAclsResult = adminClient.createAcls(Arrays.asList(aclBinding));
//        createAclsResult.all().get();
//        System.out.println(createAclsResult.all().isDone());


        //删除权限
//        ResourcePatternFilter resourcePatternFilter = new ResourcePatternFilter(ResourceType.TOPIC, "test002", PatternType.LITERAL);
//        AccessControlEntryFilter accessControlEntryFilter = new AccessControlEntryFilter("User:fanhaiqiu", "192.168.200.48", AclOperation.READ, AclPermissionType.ALLOW);
//        AclBindingFilter aclBinding = new AclBindingFilter(resourcePatternFilter, accessControlEntryFilter);
//        DeleteAclsResult deleteAclsResult = adminClient.deleteAcls(Arrays.asList(aclBinding));
//        deleteAclsResult.all().get();
//        System.out.println(deleteAclsResult.all().isDone());

        //新增用户
//        ScramCredentialInfo credentialInfo = new ScramCredentialInfo(ScramMechanism.SCRAM_SHA_512, 4096);
//        UserScramCredentialUpsertion userScramCredentialUpsertion = new UserScramCredentialUpsertion("wangjianlin", credentialInfo, "iot@10086");
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
//        //ClientQuotaAlteration.Op op1 = new ClientQuotaAlteration.Op("producer_byte_rate", 1024.00);
//        //消费配额
//        ClientQuotaAlteration.Op op2 = new ClientQuotaAlteration.Op("consumer_byte_rate", 33554432.00);
//        ClientQuotaAlteration  clientQuotaAlteration = new ClientQuotaAlteration(clientQuotaEntity, Arrays.asList(op2));
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
//        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, "test002");
//        ConfigEntry configEntry = new ConfigEntry("retention.bytes", "268435456");
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
