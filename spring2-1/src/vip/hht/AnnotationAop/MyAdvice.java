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
 * ����һ��֪ͨ(������������ǿ�Ĵ���)
 * @author zhoumo
 *
 */
@Component("ma")
@Aspect//����ע��(�����+֪ͨ)a
public class MyAdvice {
	
	@Pointcut("execution(* vip.hht.aop.*Service.*(..))")
	public void pc(){}
	
	/**
	 * �ڵ���Ŀ�귽��֮ǰ����
	 */
	@Before("MyAdvice.pc()")
	public void before(){
		System.out.println("����Ŀ�귽��֮ǰ���õķ���");
	}
	/**
	 * �ڵ���Ŀ�귽��֮�����
	 */
	@AfterReturning("execution( * vip.hht.aop.*Service.*(..))")
	public void afterReturning(){
		System.out.println("afterReturning--����Ŀ�귽��֮����õķ���(�쳣�󲻻�ִ��)");
	}
	
	/**
	 * ���Ʒ���
	 * @throws Throwable 
	 */
	@Around("execution( * vip.hht.aop.*Service.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("���Ʒ���--�ڵ���Ŀ�귽��֮ǰ���õĴ���");
		Object obj = pjp.proceed();
		System.out.println("���Ʒ���--�ڵ���Ŀ�귽��֮����õĴ���");
		return obj;
	}
	/**
	 * ��Ŀ�귽�������쳣֮����õķ���
	 */
	@AfterThrowing("execution( * vip.hht.aop.*Service.*(..))")
	public void afterException(){
		System.out.println("�쳣��!!--- ��Ŀ�귽�������쳣֮����õķ���");
	}
	/**
	 * �ڵ���Ŀ�귽��֮����� ���ۻ᲻���쳣
	 */
	@After("execution( * vip.hht.aop.*Service.*(..))")
	public void after(){
		System.out.println(" �ڵ���Ŀ�귽��֮�����  �쳣Ҳ�����!!");
	}

}
