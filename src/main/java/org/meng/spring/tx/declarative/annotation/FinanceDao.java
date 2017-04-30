package org.meng.spring.tx.declarative.annotation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FinanceDao {

    @Autowired
    @Qualifier("namedJdbcTemplate")
    NamedParameterJdbcTemplate namedJdbcTemplate;

    public int insertBankAccountForEmp(Account account){
    	final Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("account_no", account.getAccountNo());
    	paramMap.put("emp_no", account.getEmpNo());
    	return namedJdbcTemplate.update("insert into learningspring.account (account_no, emp_no ) values (:account_no, :emp_no)",  paramMap);
    }
}
