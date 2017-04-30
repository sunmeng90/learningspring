package org.meng.spring.tx.declarative.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by meng_ on 4/18/2017.
 * Finance department that help employees on salary issue, e.g. register debit account for new employee, pay salary
 */
@Service("financeService")
public class FinanceService {
	
	@Autowired
	private FinanceDao financeDao;
	
	public int createBankAccountForEmp(Account account){
		return financeDao.insertBankAccountForEmp(account);
    }
}
