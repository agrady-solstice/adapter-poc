package com.mq.adapterpoc.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class JMSConsumer {

    @Autowired
    JmsTemplate jmsTemplate;

    @JmsListener(destination = "DEV.QUEUE.1", containerFactory = "jmsListenerContainerFactory")
    public void getMessge(Message message){

        System.out.println(("Message > " + message.toString()));
    }

}
