package vip.hht.AnnotationAop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 定义一个通知(给被代理对象加强的代码)
 * @author zhoumo
 *
 */
@Component("ma")
@Aspect//切面注解(切入点+通知)a
public class MyAdvice {
	
	@Pointcut("execution(* vip.hht.aop.*Service.*(..))")
	public void pc(){}
	
	/**
	 * 在调用目标方法之前调用
	 */
	@Before("MyAdvice.pc()")
	public void before(){
		System.out.println("调用目标方法之前调用的方法");
	}
	/**
	 * 在调用目标方法之后调用
	 */
	@AfterReturning("execution( * vip.hht.aop.*Service.*(..))")
	public void afterReturning(){
		System.out.println("afterReturning--调用目标方法之后调用的方法(异常后不会执行)");
	}
	
	/**
	 * 环绕方法
	 * @throws Throwable 
	 */
	@Around("execution( * vip.hht.aop.*Service.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("环绕方法--在调用目标方法之前调用的代码");
		Object obj = pjp.proceed();
		System.out.println("环绕方法--在调用目标方法之后调用的代码");
		return obj;
	}
	/**
	 * 在目标方法出现异常之后调用的方法
	 */
	@AfterThrowing("execution( * vip.hht.aop.*Service.*(..))")
	public void afterException(){
		System.out.println("异常了!!--- 在目标方法出现异常之后调用的方法");
	}
	/**
	 * 在调用目标方法之后调用 不论会不会异常
	 */
	@After("execution( * vip.hht.aop.*Service.*(..))")
	public void after(){
		System.out.println(" 在调用目标方法之后调用  异常也会调用!!");
	}

}
