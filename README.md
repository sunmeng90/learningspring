<center> ***Learning Spring*** </center>
#overview
The Spring Framework is a lightweight solution and a potential one-stop-shop for building your enterprise-ready applications. Spring enables you to build applications from "plain old Java objects" (POJOs) and to apply enterprise services non-invasively to POJOs. The two basic core feature that Spring provides are Inversion of Control and AOP

##IoC
bean declaration 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="student" class="bean.Student" scope="prototype">
		<property name="id" value="1" />
		<property name="firstName" value="Meng" />
		<property name="lastName" value="Sun" />
	</bean>

</beans>
```

get bean 
```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean/applicationContext-beans.xml");
        Student stu = (Student) applicationContext.getBean("student");
        System.out.println(stu.getFirstName() + " " + stu.getLastName());
    }

}
```

##AOP
###Overview
AOP aims to get rid of code tangling and scattering.

###Terms in AoP:
* JoinPoint: A joinpoint is a point in the execution of the **application** where an aspect can be plugged in. This point could be a method being called, an exception being thrown, or even a field being modified.  
* PointCut:  A pointcut definition matches one or more joinpoints at which advice should be woven.
* Advice: the functionality we want to apply
* Aspect: combines pointcut and advice
* Weaving: The process of appling an aspect to our system. linking aspects with other application types or objects to create an advised object. This can
be done at compile time (using the AspectJ compiler, for example), load time, or at runtime. Spring AOP, like other pure Java AOP frameworks, performs weaving at runtime.

###Advice Types:  

* Before
* After
* AfterReturning
* Around

###Best Practices  
Define pointcut which matches all the method executions in AccountService. The execution expression are in format: execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern)
throws-pattern?)
```xml
<aop:pointcut id="accountExecution" expression="execution(* aop.AccountService.*(..))" />
```
Define an advice bean that profiles the method execution
```java
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class SimpleProfiler {
    public Object profile(ProceedingJoinPoint call) throws Throwable {
        StopWatch clock = new StopWatch("Profiling for " + call.getSignature().getName());
        try{
            clock.start(call.toShortString());
            return call.proceed();
        }finally{
            clock.stop();
            System.out.println(clock.prettyPrint());
        }
        
    }
}

```

```xml
<bean id="simpleProfiler" class="aop.SimpleProfiler" />
```

Declare the (advice) bean as an around advice
```xml
<aop:around pointcut-ref="accountExecution" method="profile" />
```
Aspect configration:
```xml
<aop:config>
        <aop:aspect ref="simpleProfiler">
            <aop:pointcut id="accountExecution" expression="execution(* aop.AccountService.*(..))" />
            <aop:around pointcut-ref="accountExecution" method="profile" />
        </aop:aspect>
</aop:config>
```

###Appling advice: 
Runtimie Weaving

###Advice ordering:  
When multiple advice needs to execute at the same join point (executing method) the ordering rules are:
The highest precedence advice runs first "on the way in" (so given two pieces of before advice, the one with highest precedence runs first). "On the way out" from a join point, the highest precedence advice runs last (so given two pieces of after advice, the one with the highest precedence will run second).

###Introduction:
Introductions (known as inter-type declarations in AspectJ) enable an aspect to declare that advised objects implement a given interface, and to provide an implementation of that interface on behalf of those objects. In other words, An introduction allows you to add/declare new methods or attributes to the existing classes.
e.g. There is a requirement to expose services invocation statistics, we can do it in this way:

* Add an interface UsageTracked(the implementation of that interface DefaultUsageTracked) to service under aop.introduction.AccountService, the interface should have method ``` usageTracked.incrementUseCount(); ```
* Declare advice bean to intercept all the method execution of AccountService which implements UsageTracked. The advice bean should have a method: ``` public void recordUsage(UsageTracked usageTracked) {        usageTracked.incrementUseCount();} ```

* During runtime, everytime methods in AccountServcie is invoked, it will be intercepted by advice usageTracking which will invoke ``` usageTracked.incrementUseCount(); ``` that added to AccountService

 
configuration:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">


    <bean id="accountService" class="aop.introduction.AccountService" />
    <bean id="usageTracking" class="aop.introduction.UsageTracking" />
    <aop:aspectj-autoproxy />
    <aop:config>
        <aop:aspect id="usageTrackerAspect" ref="usageTracking">
            <aop:declare-parents types-matching="aop.introduction.AccountService" implement-interface="aop.introduction.UsageTracked" default-impl="aop.introduction.UsageTrackedImpl" />
            <aop:pointcut expression="execution(* aop.introduction.AccountService.*(..)) and this(usageTracked)" id="accountExecution" />
            <aop:before method="recordUsage" pointcut-ref="accountExecution" />
        </aop:aspect>
    </aop:config>
</beans>
```

```java
public interface UsageTracked {
    public void incrementUseCount();
}
```

```java
public class UsageTrackedImpl implements UsageTracked {

    @Override
    public void incrementUseCount() {
        System.out.println("Usage count increase by 1");
    }

}

```

```java
public class UsageTracking {
    public void recordUsage(UsageTracked usageTracked) {
        usageTracked.incrementUseCount();
    }
}

```


```java
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop/aop-introduction.xml");
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        System.out.println(accountService.getBalance("622021234567"));
        accountService.transfer("1234", "abcd", 11);

    }

}
```

###schema-based AOP
https://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html
