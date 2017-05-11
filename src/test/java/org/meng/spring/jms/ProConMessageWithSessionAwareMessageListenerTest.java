package org.meng.spring.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meng.spring.jms.sendandreply.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jms/applicationContext-jms-sendandreply.xml")
public class ProConMessageWithSessionAwareMessageListenerTest {

	@Autowired
	MessageSender messageSender;
	
	@Test
	public void testSend() {
		for (int i = 0; i < 50; i++) {
			messageSender.sendMessage("Hello Meng");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
