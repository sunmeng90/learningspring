package org.meng.spring.tx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meng.spring.tx.declarative.Account;
import org.meng.spring.tx.declarative.Emp;
import org.meng.spring.tx.declarative.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tx/tx-declarative-2.xml")
@Transactional
public class EmpServiceTest {
	@Autowired
	private EmpService empService;

	@Test
	public void testGetEmp() {
		Emp emp = empService.getEmp(10002);
		assertNotNull(emp);
		assertEquals(10002, emp.getEmpNo());
	}

	@Test
	public void testCreateEmp(){
		Emp emp = new Emp();
		emp.setEmpNo(19999);
		emp.setFirstName("t_first");
		emp.setLastName("t_last");
		emp.setBirthday(LocalDate.now());
		emp.setGender("F");
        empService.getEmp(10002);
		empService.createEmp(emp, null);
		emp.setEmpNo(20000);
		empService.createEmp(emp, null);
	}

	@Test
	public void testCreateEmpWithTx(){
		Emp emp = new Emp();
		emp.setEmpNo(10000023);
		emp.setFirstName("t_first");
		emp.setLastName("t_last");
		emp.setBirthday(LocalDate.now());
		emp.setGender("F");
		
		Account account = new Account();
		account.setAccountNo("123456");
		account.setEmpNo(1000003);

		empService.createEmp(emp, account);
	}
}