package org.meng.spring.tx.declarative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("empService")
public class EmpServiceImpl implements EmpService {
	@Autowired
	EmpDao empDao;
	@Autowired
	FinanceService financeService;

	@Override
	public Emp getEmp(int empNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Emp getEmp(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createEmp(Emp emp, Account account) {
		empDao.createEmp(emp);
		financeService.createBankAccountForEmp(account);
	}

	@Override
	public void updateEmp(Emp emp) {
		// TODO Auto-generated method stub

	}

}
