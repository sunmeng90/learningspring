package aop;

import org.aspectj.lang.JoinPoint;

public class SecurityCheck {
	public void doAuth(JoinPoint call) {
		System.out.println("Authentication before method invocation to " + call.getSignature().getName());
	}
}
