										***learningspring***
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

AOP aims to get rid of code tangling and scattering.

Terms in AoP:
JoinPoint: public methods in spring-managed beans
PointCut: the actual joinpoints that we have declared
Advice: the functionality we want to apply
Aspect: combines pointcut and advice
Weaving: The process of appling an aspect to our system

Advice Types:
Before
After
AfterReturning



Best Practices

Define pointcut
Define advice
Appling advice


###schema-based AOP
https://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html
