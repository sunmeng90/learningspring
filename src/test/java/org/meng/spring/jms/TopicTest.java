package org.meng.spring.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meng.spring.jms.topic.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jms/applicationContext-jms-topic.xml")
public class TopicTest {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    @Qualifier("topicDestination")
    private Destination destination;

    @Test
    public void testSend() {
        messageProducer.sendMessage(destination, "Hello Meng");
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
