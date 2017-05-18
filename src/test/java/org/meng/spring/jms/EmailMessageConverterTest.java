package org.meng.spring.jms;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meng.spring.jms.messageconverter.EmailMessage;
import org.meng.spring.jms.messageconverter.EmailMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jms/applicationContext-jms-messageconverter.xml")
public class EmailMessageConverterTest {

    @Autowired
    EmailMessageProducer emailMessageProducer;
    @Autowired
    Destination emailQueue;

    @Test
    public void testSend() {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setFrom("meng@126.com");
        emailMessage.setTo("meng@test.com");
        emailMessage.setSubject("JMS message converter example");
        emailMessage.setContent("this is a test message");
        emailMessageProducer.sendMessage(emailQueue, emailMessage);

    }

}

