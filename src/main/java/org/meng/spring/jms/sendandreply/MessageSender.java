package org.meng.spring.jms.sendandreply;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("send2RecvQueue")
	private Destination send2RecvQueue;
	
	public void sendMessage(final String message){
		jmsTemplate.send(send2RecvQueue,  new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				System.out.println("send message: "+message);
				return session.createTextMessage(message);
			}
		});
	}
	
}
