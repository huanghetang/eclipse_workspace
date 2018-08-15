package vip.hht.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import vip.hht.dao.UserDao;
//事务注解,可以加在类和方法上,加在类上表示该类的所有业务方法都会使用该事务属性,如果类上和方法上同时存在,则方法上的属性会覆盖类上的事务属性
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
public class UserServiceImpl implements UserService{
	private UserDao dao;
	/*	private TransactionTemplate tt;//spring使用transactionTemplate事物模板操作事物(编码模式)
*/	
	

/*	public TransactionTemplate getTt() {
		return tt;
	}



	public void setTt(TransactionTemplate tt) {
		this.tt = tt;
	}*/



	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	
	
	public UserDao getDao() {
		return dao;
	}



	/**
	 * 转账
	 */
	//事务注解,可以加在类和方法上,加在类上表示该类的所有业务方法都会使用该事务属性,如果类上和方法上同时存在,则方法上的属性会覆盖类上的事务属性
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void transfer(final Integer from ,final Integer to, final double money){
		dao.decreaseMoney(from, to, money);
		int i =1/0;
		dao.increaseMoney(from, to, money);
	}
	
	
	/**
	 * 转账  通过编码的方式完成spring事务的管理
	 */
/*	public void transfer(final Integer from ,final Integer to, final double money){
		System.out.println(tt);
		tt.execute(new TransactionCallback<Object>(){//包装了transacionManager事物核心类的代码 实际上1,执行写入的代码,2异常包裹,3提交出异常回滚
			@Override
			public Object doInTransaction(TransactionStatus arg0) {
				dao.decreaseMoney(from, to, money);
				int i =1/0;
				dao.increaseMoney(from, to, money);
				return null;
			}
			
			
		});

	}*/
	
	

}
