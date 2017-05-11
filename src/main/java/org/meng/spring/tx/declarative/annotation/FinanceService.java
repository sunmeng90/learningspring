package org.meng.spring.tx.declarative.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by meng_ on 4/18/2017. Finance department that help employees on
 * salary issue, e.g. register debit account for new employee, pay salary
 */
@Service("financeService")
public class FinanceService {

	@Autowired
	private FinanceDao financeDao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public int createBankAccountForEmp(Account account) {
		return financeDao.insertBankAccountForEmp(account);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW)
	public int createBankAccountForEmpInNewTx(Account account) {
		return financeDao.insertBankAccountForEmp(account);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.NESTED)
	public int createBankAccountForEmpInNestedTx(Account account) {
		return financeDao.insertBankAccountForEmp(account);
	}
}
