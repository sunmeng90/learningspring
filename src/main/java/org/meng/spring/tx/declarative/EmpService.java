package org.meng.spring.tx.declarative;

public interface EmpService {

	Emp getEmp(int empNo);

	Emp getEmp(String firstName, String lastName);

	void createEmp(Emp Eemp, Account account);

	void updateEmp(Emp emp);

}