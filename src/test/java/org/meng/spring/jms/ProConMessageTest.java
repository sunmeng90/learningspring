package org.meng.spring.jms;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meng.spring.jms.basic.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jms/applicationContext-jms-basic.xml")
public class ProConMessageTest {

	@Autowired
	MessageProducer messageProducer;

	@Autowired
	@Qualifier("queueDestination")
	Destination destination;

	@Test
	public void testSend() {
		for (int i = 0; i < 1000; i++) {
			messageProducer.sendMessage(destination, "Hello Meng");
		}
	}

}
