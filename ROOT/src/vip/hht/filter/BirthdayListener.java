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
 * ���û����յ�����û���������ף��
 * @author zhoumo
 *
 */
public class BirthdayListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized------------");
		//���յ��췢���ʼ�ף��
		Timer timer = new Timer();
		final QueryRunner queryRunner = new QueryRunner(new ComboPooledDataSource());
		timer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				//��ѯ����ʱ�����email�˺�
				String sql="select * from custom where birthday like ?";
				DateFormat format = new SimpleDateFormat("MM-dd");
				//��ȡ��ǰ�º���
				String md = format.format(new Date());
				try {
					List<Custom> list = queryRunner.query(sql, new BeanListHandler<Custom>(Custom.class), "%"+md);
					if(list!=null && list.size()>0){
						for(Custom cus:list){
							//�����ʼ�
							MailUtils.sendMail(cus.getEmail(), "ף�����տ���", "�װ���,ף�����տ���");
							System.out.println("�Ѿ���"+cus.getName()+"�����������ʼ�");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}, new Date(), 5*1000);//new Date��һ��ִ�е�ʱ��,����ִ�е�ʱ����
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed------------");
		
	}

}
