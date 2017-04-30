package org.meng.spring.tx.declarative.annotation;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meng.spring.tx.declarative.annotation.Account;
import org.meng.spring.tx.declarative.annotation.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tx/tx-declarative-annotation.xml")
public class EmpServiceTest {
	@Autowired
	EmpService empService;

	@Test
	public void testCreateEmp() {

		Emp emp = new Emp();
		emp.setEmpNo(10000025);
		emp.setFirstName("t_first");
		emp.setLastName("t_last");
		emp.setBirthday(LocalDate.now());
		emp.setGender("F");

		Account account = new Account();
		account.setAccountNo("123456");
		account.setEmpNo(1000003);

		empService.createEmp(emp, account);

	}


	@Test
	public void testCreateEmpWithTxNew() {

		Emp emp = new Emp();
		emp.setEmpNo(10000026);
		emp.setFirstName("t_first");
		emp.setLastName("t_last");
		emp.setBirthday(LocalDate.now());
		emp.setGender("F");

		Account account = new Account();
		account.setAccountNo("123456123456123456123456123456123456");
		account.setEmpNo(1000003);

		empService.createEmpWithAccountInNewTx(emp, account);

	}

}
