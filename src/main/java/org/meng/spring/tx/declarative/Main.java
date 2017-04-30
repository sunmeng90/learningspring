package org.meng.spring.tx.declarative;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Main {

    public static void main(final String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("tx/tx-declarative.xml");
        EmpService empService = (EmpService) ctx.getBean("empServiceMock");
        //empService.createEmp (new Emp());
        empService.getEmp (1);
    }
}