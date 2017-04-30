package org.meng.spring.tx.declarative;

public class EmpServiceImplMock implements EmpService {
	/* (non-Javadoc)
	 * @see org.meng.spring.tx.declarative.EmpService#getEmp(int)
	 */
	@Override
	public Emp getEmp(int empNo) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see org.meng.spring.tx.declarative.EmpService#getEmp(java.lang.String, java.lang.String)
	 */
	@Override
	public Emp getEmp(String firstName, String lastName) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see org.meng.spring.tx.declarative.EmpService#createEmp(org.meng.spring.tx.declarative.Emp)
	 */
	@Override
	public void createEmp(Emp emp, Account account) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see org.meng.spring.tx.declarative.EmpService#updateEmp(org.meng.spring.tx.declarative.Emp)
	 */
	@Override
	public void updateEmp(Emp emp) {
		throw new UnsupportedOperationException();
	}

}
