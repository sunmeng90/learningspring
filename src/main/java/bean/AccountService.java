package bean;

public class AccountService {
	public double getBalance(String accountNo){
		return Math.random() * 100000;
	}

	public boolean transfer(String fromAccount, String toAccount, double amount){
		System.out.printf("transferring $%f from %s to %s", amount, fromAccount, toAccount);
		return true;
	}
}
