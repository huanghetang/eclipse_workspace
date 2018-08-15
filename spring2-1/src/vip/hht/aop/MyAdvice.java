package vip.hht.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * ����һ��֪ͨ(������������ǿ�Ĵ���)
 * @author zhoumo
 *
 */
@Component("myAdvice")
public class MyAdvice {
	
	/**
	 * �ڵ���Ŀ�귽��֮ǰ����
	 */
	public void before(){
		System.out.println("����Ŀ�귽��֮ǰ���õķ���");
	}
	/**
	 * �ڵ���Ŀ�귽��֮�����
	 */
	public void afterReturning(){
		System.out.println("afterReturning--����Ŀ�귽��֮����õķ���(�쳣�󲻻�ִ��)");
	}
	
	/**
	 * ���Ʒ���
	 * @throws Throwable 
	 */
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("���Ʒ���--�ڵ���Ŀ�귽��֮ǰ���õĴ���");
		Object obj = pjp.proceed();
		System.out.println("���Ʒ���--�ڵ���Ŀ�귽��֮����õĴ���");
		return obj;
	}
	/**
	 * ��Ŀ�귽�������쳣֮����õķ���
	 */
	public void afterException(){
		System.out.println("�쳣��!!--- ��Ŀ�귽�������쳣֮����õķ���");
	}
	/**
	 * �ڵ���Ŀ�귽��֮����� ���ۻ᲻���쳣
	 */
	public void after(){
		System.out.println(" �ڵ���Ŀ�귽��֮�����  �쳣Ҳ�����!!");
	}

}
