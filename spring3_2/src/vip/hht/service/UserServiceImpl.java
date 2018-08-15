package vip.hht.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import vip.hht.dao.UserDao;
//����ע��,���Լ�����ͷ�����,�������ϱ�ʾ���������ҵ�񷽷�����ʹ�ø���������,������Ϻͷ�����ͬʱ����,�򷽷��ϵ����ԻḲ�����ϵ���������
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
public class UserServiceImpl implements UserService{
	private UserDao dao;
	/*	private TransactionTemplate tt;//springʹ��transactionTemplate����ģ���������(����ģʽ)
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
	 * ת��
	 */
	//����ע��,���Լ�����ͷ�����,�������ϱ�ʾ���������ҵ�񷽷�����ʹ�ø���������,������Ϻͷ�����ͬʱ����,�򷽷��ϵ����ԻḲ�����ϵ���������
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void transfer(final Integer from ,final Integer to, final double money){
		dao.decreaseMoney(from, to, money);
		int i =1/0;
		dao.increaseMoney(from, to, money);
	}
	
	
	/**
	 * ת��  ͨ������ķ�ʽ���spring����Ĺ���
	 */
/*	public void transfer(final Integer from ,final Integer to, final double money){
		System.out.println(tt);
		tt.execute(new TransactionCallback<Object>(){//��װ��transacionManager���������Ĵ��� ʵ����1,ִ��д��Ĵ���,2�쳣����,3�ύ���쳣�ع�
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
