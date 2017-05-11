package org.meng.spring.jms.listeneradapter;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

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
	@Qualifier("queue")
	private Destination queue;

	@Autowired
	@Qualifier("responseQueue")
	private Destination responseQueue;

	public void sendMessage(final String message) {
		jmsTemplate.send(queue, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(message);
				textMessage.setJMSReplyTo(responseQueue);
				System.out.println("send message: " + message);
				return textMessage;
			}
		});
	}

}
