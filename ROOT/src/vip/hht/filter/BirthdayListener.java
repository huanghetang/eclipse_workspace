package vip.hht.filter;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import vip.hht.beans.Custom;
import vip.hht.util.MailUtils;


/**
 * 在用户生日当天给用户发送生日祝福
 * @author zhoumo
 *
 */
public class BirthdayListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized------------");
		//生日当天发送邮件祝福
		Timer timer = new Timer();
		final QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());
		timer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				//查询生日时当天的email账号
				String sql="select * from custom where birthday like ?";
				DateFormat format = new SimpleDateFormat("MM-dd");
				//获取当前月和日
				String md = format.format(new Date());
				try {
					List<Custom> list = queryRunner.query(sql, new BeanListHandler<Custom>(Custom.class), "%"+md);
					if(list!=null && list.size()>0){
						for(Custom cus:list){
							//发送邮件
							MailUtils.sendMail(cus.getEmail(), "祝你生日快乐", "亲爱的,祝你生日快乐");
							System.out.println("已经给"+cus.getName()+"发送了生日邮件");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}, new Date(), 5*1000);//new Date第一次执行的时间,后续执行的时间间隔
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed------------");
		
	}

}
