package org.meng.spring.jms.listeneradapter;

public class MessageConsumerListener {
	public String handleMessage(String message){
		System.out.println(this.getClass().getName()+" receive message "+ message);
		return "This is a response message from " + this.getClass().getName();
	}
}
