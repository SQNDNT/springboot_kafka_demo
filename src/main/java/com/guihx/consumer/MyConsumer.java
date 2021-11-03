package com.guihx.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {

    private final static String TOPIC_NAME = "test";
    private final static String GROUP_NAME = "testGroup";

    @KafkaListener(topics = TOPIC_NAME,groupId = GROUP_NAME)
    public void getMsg(ConsumerRecord<String,String> consumerRecord, Acknowledgment acknowledgment){
        System.out.println("consumerRecord = "+consumerRecord);
        System.out.println("value = "+consumerRecord.value());
        //手动提交偏移量(生效的前提是yml中配置了 enable-auto-commit: false )
        acknowledgment.acknowledge();
    }

}
