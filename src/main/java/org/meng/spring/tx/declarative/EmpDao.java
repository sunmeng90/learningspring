package org.meng.spring.tx.declarative;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class EmpDao {

	@Autowired
	@Qualifier("namedJdbcTemplate")
	NamedParameterJdbcTemplate namedJdbcTemplate;


	public Emp getEmp(int empNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("empNo", empNo);
		return (Emp) namedJdbcTemplate.queryForObject("SELECT * FROM learningspring.emp WHERE emp_no=:empNo", params,
				(rs, rowNum) -> {
					Emp emp = new Emp();
					emp.setEmpNo(rs.getInt("emp_no"));
					emp.setFirstName(rs.getString("first_name"));
					emp.setLastName(rs.getString("last_name"));
					return emp;
				});
	}


	public Emp getEmp(String firstName, String lastName) {
		throw new UnsupportedOperationException();
	}


	public int createEmp(Emp emp) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("emp_no", emp.getEmpNo());
		paramMap.put("first_name", emp.getFirstName());
		paramMap.put("last_name", emp.getLastName());
		paramMap.put("birthday", emp.getBirthday());
		paramMap.put("gender", emp.getGender());
		return namedJdbcTemplate.update(
				"insert into learningspring.emp (emp_no, first_name, last_name, birth_date, gender) values (:emp_no, :first_name, :last_name, :birthday, :gender)",
				paramMap);
	}


	public void updateEmp(Emp emp) {
		throw new UnsupportedOperationException();
	}

}
