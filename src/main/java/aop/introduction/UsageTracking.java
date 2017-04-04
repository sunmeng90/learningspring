package aop.introduction;

public class UsageTracking {
	public void recordUsage(UsageTracked usageTracked) {
		usageTracked.incrementUseCount();
	}
}
