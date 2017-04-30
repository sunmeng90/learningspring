package org.meng.spring.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class EmpDao {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public Emp getEmpById(long empNo) {
		return jdbcTemplate.query("select * from emp where emp_no = " + empNo, new ResultSetExtractor<Emp>() {
			@Override
			public Emp extractData(ResultSet rs) throws SQLException {
				rs.next();
				Emp emp = new Emp();
				emp.setEmpNo(rs.getLong("emp_no"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				return emp;
			}
		});
	}

	public Emp getEmpByIdWithRowMapper(long empNo) {
		return jdbcTemplate.queryForObject("select * from emp where emp_no = " + empNo, new EmpRowMapper());
	}

	public Emp getEmpByNameWithNamedParams(String firstName, String lastName) {
		Emp emp = new Emp();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		return namedJdbcTemplate.queryForObject(
				"select * from emp where first_name = :firstName and last_name = :lastName",
				new BeanPropertySqlParameterSource(emp), new EmpRowMapper());
	}

	public void updateEmpName(Emp emp) {
		jdbcTemplate.update("update emp set first_name = ?, last_name = ? where emp_no = ?", emp.getFirstName(),
				emp.getLastName(), emp.getEmpNo());
	}

	public void createBakupTable(){
		jdbcTemplate.execute("create table emp_bakup like emp");
	}

	public List<Emp> getEmpOldThanByProc(int age){
		final String procedureCall = "{call getEmpByAgeOldThan(?)}";
		List params = new ArrayList<>();
		params.add(new SqlParameter("age", Types.INTEGER));
		params.add(new SqlReturnResultSet("empList", new EmpRowMapper()));
		Map<String, Object> result = this.jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement callableStatement = con.prepareCall(procedureCall);
				callableStatement.setInt("age", age);
				return callableStatement;
			}
		}, params);
		return (List<Emp>)result.get("empList");
	}
	//http://blog.csdn.net/hu_shengyang/article/details/8086861
	public Map<String, Object> getPagedEmp(final int pageIdx, final int pageSize){
		List<SqlParameter> params = new ArrayList<>();
		params.add(new SqlParameter("startIdx", Types.INTEGER));
		params.add(new SqlParameter("pageSize", Types.INTEGER));
		params.add(new SqlOutParameter("totalCount", Types.INTEGER));
		params.add(new SqlOutParameter("totalPage", Types.INTEGER));
		params.add(new SqlReturnResultSet("empList", new EmpRowMapper()));
		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement call =  con.prepareCall("{call getPagedEmp(?,?,?,?)}");
				call.setInt("startIdx", pageIdx);
				call.setInt("pageSize", pageSize);
				call.registerOutParameter("totalCount",Types.INTEGER);
				call.registerOutParameter("totalPage", Types.INTEGER);
				return call;
			}
		}, params);

		return resultMap;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return namedJdbcTemplate;
	}

	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}


	private static class EmpRowMapper implements RowMapper<Emp> {

		@Override
		public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
			Emp emp = new Emp();
			emp.setEmpNo(rs.getLong("emp_no"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			return emp;
		}

	}

	public Emp getEmpByNameWithNamedParamsMap(String firstName, String lastName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("firstName", firstName);
		params.put("lastName", lastName);
		return namedJdbcTemplate.queryForObject(
				"select * from emp where first_name = :firstName and last_name = :lastName",
				params, new EmpRowMapper());
	}

}
