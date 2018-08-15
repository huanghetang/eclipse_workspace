package vip.hht.filter;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;

public class MyServletContextListener implements ServletContextListener{
	static int count;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized-------------");
		
		//tomcat����ʱ��������ʼ��
		ServletContext servletContext = sce.getServletContext();
		//
		servletContext.setAttribute("onlineNum", 0);
		
		//��һ����ʱ��,����ը��������2��ըһ��,Ȼ��3��ըһ��,Ȼ��2��ըһ��,Ȼ��3��ըһ��...
		class MyTimeTask extends TimerTask{
			@Override
			public void run() {
				count = (count+1)%2;
				System.out.println("bombing.....");
				new Timer().schedule(new MyTimeTask(),count*1000+2000);
			}
			
		}
		Timer timer = new Timer();
		MyTimeTask myTimeTask = new MyTimeTask();
		timer.schedule(myTimeTask,2000);
		
		while(true){
			System.out.println(Calendar.getInstance().get(Calendar.SECOND));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed-------------------");
		
	}
	

	public static void main(String[] args) {
		Map hashMap = new HashMap();
		hashMap.put("name", "����");
		test(hashMap);
		
	}
	
	
	public static  void test(Map map){
		System.out.println("Map");
	}
	
	public static void test(HashMap map){
		System.out.println("HashMap");
	}


}
