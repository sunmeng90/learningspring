package org.meng.spring.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Main {

    public static void main(final String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc/jdbc-basic.xml");
        EmpDao empDao = (EmpDao) ctx.getBean("empDao");
        System.out.println(empDao.getEmpById(10002));
        System.out.println(empDao.getEmpByIdWithRowMapper(10002));
        //Bezalel Simmel
        Emp emp = empDao.getEmpById(10002);
        System.out.println(emp);
        System.out.println("-----------------------");
        emp.setFirstName(emp.getFirstName()+"_upd");
        emp.setLastName(emp.getLastName()+"_upd");
        empDao.updateEmpName(emp);
    }
}