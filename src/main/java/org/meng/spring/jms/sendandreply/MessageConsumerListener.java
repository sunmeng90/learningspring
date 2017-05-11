package org.meng.spring.jms.sendandreply;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MessageConsumerListener implements MessageListener {
	public void onMessage(Message message) {
		try {
			TextMessage text = (TextMessage) message;
			System.out.println("Receive Message: " + text.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}