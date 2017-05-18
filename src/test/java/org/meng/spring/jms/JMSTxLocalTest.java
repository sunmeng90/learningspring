package org.meng.spring.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meng.spring.jms.messageconverter.EmailMessage;
import org.meng.spring.jms.messageconverter.EmailMessageProducer;
import org.meng.spring.jms.tx.local.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jms/applicationContext-jms-tx-local.xml")
public class JMSTxLocalTest {
    @Autowired
    MessageProducer messageProducer;
    @Autowired
    Destination queue;

    @Test
    public void testSend() throws InterruptedException {
        messageProducer.sendMessage(queue, "Hello, Meng");

    }

}

