package com.mq.adapterpoc.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TradeEventConsumer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JmsTemplate jmsTemplate;


    @KafkaListener(topics={"${kafka.consumer.topic}"})
    public void onTestTopicMessage(ConsumerRecord<Integer, String> record) throws Exception {
        log.info("Consumed record from test-topic : {}", record);
        jmsTemplate.convertAndSend("DEV.QUEUE.1", record.value());
    }


    @KafkaListener(topics={"dlq"})
    public void onDlqMessage(ConsumerRecord<Integer, String> record) throws Exception {
        log.info("Consumed record from dlq : {}", record);
    }
}



