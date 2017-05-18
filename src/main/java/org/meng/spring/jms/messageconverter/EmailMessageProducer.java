package org.meng.spring.jms.messageconverter;

import java.io.Serializable;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class EmailMessageProducer {
	@Autowired
	private JmsTemplate jmsTemplate;
	public void sendMessage(Destination destination, final Serializable message){
		//no need to wrap message to ObjectMessage
		jmsTemplate.convertAndSend(destination, message);
	}

}
