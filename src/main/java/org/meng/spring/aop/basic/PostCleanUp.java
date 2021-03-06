package org.meng.spring.aop.basic;

import org.aspectj.lang.JoinPoint;

public class PostCleanUp {
	public void cleanUp(JoinPoint call){
		System.out.println("Post clean up after method invocation to " + call.getSignature().getName());
	}
}
