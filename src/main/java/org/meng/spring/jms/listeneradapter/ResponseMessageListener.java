package org.meng.spring.jms.listeneradapter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ResponseMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println(this.getClass().getName()+" receive response message: "+ ((TextMessage)message).getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
