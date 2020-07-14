package com.mq.adapterpoc.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.JmsException;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Slf4j
public class MessageProcessor {
//
//    public void processMessage(Message msg){
//        String txtMsg = null;
//        String txIdr = null;
//        try {
//            log.info(">>processMessage started>>");
//            if (msg instanceof BytesMessage)
//                txtMsg = getByteMessage(msg);
//            else
//                txtMsg = ((TextMessage) msg).getText();
//            txIdr = MessageUtil.getSwiftMessageId(txtMsg);
//            log.info("Message ID in LISTENER::" + txIdr);
//            swiftProcess.processMessages(txtMsg, txIdr);
//        } catch (JmsException | JMSException e) {
//            emailProcessor.sendEmail("Failed to process "+txtMsg.toString(), e.getMessage());
//            log.error("Got JMSException error in gsl3plsr-adapter-flow" + e.getMessage());
//            auditBuilder.auditFailed(txtMsg, e.getMessage(), "GSL3PLSwiftReceive");
//
//        }
//
//
//
//    }
}
