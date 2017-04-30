package org.meng.spring.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jdbc/jdbc-basic.xml")
public class EmpDaoTest {
	@Autowired
	private EmpDao empDao;

	@Test
	public void testGetEmpByID() {
		Emp emp = empDao.getEmpById(10002);
		assertNotNull(emp);
		assertEquals(10002, emp.getEmpNo());
	}
	@Test
	public void testGetEmpByNameWithNamedParams() {
		Emp emp = empDao.getEmpByNameWithNamedParams("Parto", "Bamford");
		assertNotNull(emp);
		assertEquals(10003, emp.getEmpNo());
	}
	@Test
	public void testGetEmpByNameWithNamedParamsMap() {
		Emp emp = empDao.getEmpByNameWithNamedParamsMap("Parto", "Bamford");
		assertNotNull(emp);
		assertEquals(10003, emp.getEmpNo());
	}

	@Test
	public void testCreateEmpBackupTable(){
		empDao.createBakupTable();
	}

	@Test
	public void testGetEmpOldThanByProc(){
		empDao.getEmpOldThanByProc(20);
		//assertTrue();
	}

	@Test
	public void testGetPagedEmp(){
		Map<String, Object> result = empDao.getPagedEmp(10, 10);
		assertNotNull(result);
		assertTrue(result.size()>0);
		assertTrue("total emp size should be large than 0", (Integer)result.get("totalCount") > 0);
		assertTrue("total emp pages should be large than 0", (Integer)result.get("totalPage") > 0);
		assertNotNull(result.get("empList"));
		assertTrue(((List)result.get("empList")).size()==10);

	}
}