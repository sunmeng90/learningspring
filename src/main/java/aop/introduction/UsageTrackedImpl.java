package aop.introduction;

public class UsageTrackedImpl implements UsageTracked {

	@Override
	public void incrementUseCount() {
		System.out.println("Usage count increase by 1");
	}

}
