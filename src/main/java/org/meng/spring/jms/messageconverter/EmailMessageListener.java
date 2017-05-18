package org.meng.spring.jms.messageconverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class EmailMessageListener implements MessageListener {

	@Autowired
	private MessageConverter emailMessageConverter;

	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objMessage = (ObjectMessage) message;
			try {
				EmailMessage emailMessage = (EmailMessage) emailMessageConverter.fromMessage(objMessage);
				System.out.println("Receive Email Message: " + emailMessage);
			} catch (MessageConversionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
