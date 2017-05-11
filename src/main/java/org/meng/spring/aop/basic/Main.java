package org.meng.spring.aop.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop/aop-basic.xml");
		AccountService accountService = (AccountService) applicationContext.getBean("accountService");
		// System.out.println(accountService.getBalance("622021234567"));
		accountService.getBalance("622021234567");
		accountService.transfer("1234", "abcd", 12345678910.22);
		// accountService.accountThrowing();

	}

}
