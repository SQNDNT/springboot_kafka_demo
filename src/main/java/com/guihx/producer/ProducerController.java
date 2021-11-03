package com.guihx.producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProducerController {

    private final static String TOPIC_NAME = "test";

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/sendMsg")
    public String sendMsg() throws Exception {
        SendResult<String, String> sendResult = kafkaTemplate.send(TOPIC_NAME, "key", "this is msg").get();
        RecordMetadata metadata = sendResult.getRecordMetadata();
        String result = "发送消息结果: topic-"+ metadata.topic()+" | Partition-"+metadata.partition()+" | offset-"+ metadata.offset();
        System.out.println(result);
        return result;
    }
}
