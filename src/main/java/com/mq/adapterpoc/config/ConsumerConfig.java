package com.mq.adapterpoc.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mq.adapterpoc.domain.TradeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DefaultAfterRollbackProcessor;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.util.backoff.FixedBackOff;

import javax.jms.ConnectionFactory;

@EnableKafka
@EnableJms
@Slf4j
public class ConsumerConfig {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory (
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory,
            KafkaTransactionManager<Object, Object> kafkaTransactionManager) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.getContainerProperties().setTransactionManager(kafkaTransactionManager);
        factory.setMissingTopicsFatal(false);
        DefaultAfterRollbackProcessor rollbackProcessor = new DefaultAfterRollbackProcessor<Object, Object>(
                (record, exception) -> {
                    log.info("Writing to DLQ: " + record);
                    try {
                        TradeEvent tradeEvent = objectMapper.readValue((String) record.value(), TradeEvent.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        log.error("Error writing record {} to DLQ:", record);
                    }
                },
                new FixedBackOff(100L, 1));
        // the below 2 lines are key to send the offsets after successfully writing to the DLQ
        rollbackProcessor.setCommitRecovered(true);
        rollbackProcessor.setKafkaTemplate(kafkaTemplate);
        factory.setAfterRollbackProcessor(rollbackProcessor);
        return factory;

    }
}


