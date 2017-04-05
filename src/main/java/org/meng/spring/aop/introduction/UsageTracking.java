package org.meng.spring.aop.introduction;

public class UsageTracking {
	public void recordUsage(UsageTracked usageTracked) {
		usageTracked.incrementUseCount();
	}
}
