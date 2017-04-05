package org.meng.spring.tx.declarative;

public interface EmpService {

	Emp getEmp(int empNo);

	Emp getEmp(String firstName, String lastName);

	void insertEmp(Emp Eemp);

	void updateEmp(Emp emp);

}