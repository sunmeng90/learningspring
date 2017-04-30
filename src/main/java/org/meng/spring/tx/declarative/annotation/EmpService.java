package org.meng.spring.tx.declarative.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("empService")
public class EmpService{
	@Autowired
	EmpDao empDao;
	@Autowired
	FinanceService financeService;

	public Emp getEmp(int empNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public Emp getEmp(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED)
	public void createEmp(Emp emp, Account account) {
		empDao.createEmp(emp);
		financeService.createBankAccountForEmp(account);
	}

	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED)
	public void createEmpWithAccountInNewTx(Emp emp, Account account) {
		empDao.createEmp(emp);
		try{
			financeService.createBankAccountForEmpInNewTx(account);
		}catch(DataAccessException e){
			System.out.println("Inner transaction throw exception");
			e.printStackTrace();
		}
	}


	public void updateEmp(Emp emp) {
		// TODO Auto-generated method stub

	}

}
