package org.meng.spring.aop.basic;

import org.aspectj.lang.JoinPoint;

public class ExceptionHandler {

	public void handleException(JoinPoint call, Throwable error){
		System.out.println("Handle exception thrown by method invocation to " + call.getSignature().getName());
		System.out.println("Print exception details: ");
		error.printStackTrace();
	}
}