package org.meng.spring.jms.sendandreply;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.SessionAwareMessageListener;

public class SessionAwareMessageConsumerListener implements SessionAwareMessageListener<TextMessage> {

	@Autowired
	@Qualifier("recv2SendQueue")
	Destination recv2SendQueue;
	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
		System.out.println("Receive: "+message.getText());
		MessageProducer messageProducer = session.createProducer(recv2SendQueue);
		Message ackMsg = session.createTextMessage("========================Ack========================");
		messageProducer.send(ackMsg);
	}

}
