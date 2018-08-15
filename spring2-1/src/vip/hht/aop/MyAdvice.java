package vip.hht.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * 定义一个通知(给被代理对象加强的代码)
 * @author zhoumo
 *
 */
@Component("myAdvice")
public class MyAdvice {
	
	/**
	 * 在调用目标方法之前调用
	 */
	public void before(){
		System.out.println("调用目标方法之前调用的方法");
	}
	/**
	 * 在调用目标方法之后调用
	 */
	public void afterReturning(){
		System.out.println("afterReturning--调用目标方法之后调用的方法(异常后不会执行)");
	}
	
	/**
	 * 环绕方法
	 * @throws Throwable 
	 */
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("环绕方法--在调用目标方法之前调用的代码");
		Object obj = pjp.proceed();
		System.out.println("环绕方法--在调用目标方法之后调用的代码");
		return obj;
	}
	/**
	 * 在目标方法出现异常之后调用的方法
	 */
	public void afterException(){
		System.out.println("异常了!!--- 在目标方法出现异常之后调用的方法");
	}
	/**
	 * 在调用目标方法之后调用 不论会不会异常
	 */
	public void after(){
		System.out.println(" 在调用目标方法之后调用  异常也会调用!!");
	}

}
