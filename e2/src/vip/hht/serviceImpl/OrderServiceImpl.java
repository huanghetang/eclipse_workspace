package vip.hht.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import vip.hht.Tools.ImplFactory;
import vip.hht.Tools.JDBCUtils;
import vip.hht.beans.CartModel;
import vip.hht.beans.MyComment;
import vip.hht.beans.Order;
import vip.hht.beans.OrderItem;
import vip.hht.beans.OrderItemModel;
import vip.hht.beans.PageBean;
import vip.hht.dao.CartDao;
import vip.hht.dao.CommentDao;
import vip.hht.dao.OrderDao;
import vip.hht.dao.OrderItemsDao;
import vip.hht.dao.ProductDao;
import vip.hht.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao odao = ImplFactory.getImpl(OrderDao.class);
	private CartDao cdao = ImplFactory.getImpl(CartDao.class);
	private ProductDao pdao = ImplFactory.getImpl(ProductDao.class);
	private OrderItemsDao oidao = ImplFactory.getImpl(OrderItemsDao.class);
	private CommentDao comdao = ImplFactory.getImpl(CommentDao.class);
	@Override
	public List findListById(String id) {
		List list = odao.findListById(id);
		
		return list;
	}
	@Override
	public List findOrderItemList(String[] pids) {
		List list = cdao.findOrderItemList(pids);
		return list;
	}
	/**CartModel
	 * 	//���ﳵ��Ϣ
	private int uid;
	private String pid;
	private int buynum;
	//��Ʒ��Ϣ
	private String pname;
	private Double market_price;
	private int pnum;
	private Double shop_price;
	private String pimage;
	private Timestamp pdate;
	private Integer is_hot; 
	private String pdesc;
	private Integer pflag = 0;
	private String cid;
	private Double countPrice;
	
	 */
	@Override
	public void saveOrderItems(Order order) {
		//�鿴���ݿ���Ʒ����
		String[] pids = order.getPids();
		//�鿴��ǰ�ύ�Ķ���,���ﳵ+��Ʒ������
		List<CartModel> cartModelList = cdao.findCartModelListById(order.getUid(), pids);
		//����-��Ʒ ��ϵ����Ҫ���������
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		Connection con = JDBCUtils.getConnection();
		try {
			//��������
			con.setAutoCommit(false);
			//����������ÿһ�����ﳵ+��Ʒ������
			for (CartModel cartModel : cartModelList) {
				int buy = cartModel.getBuynum();
				//���
				int repository = cartModel.getPnum();
				if(buy-repository>0){
					throw new RuntimeException("����,��ǰ��Ʒ:"+cartModel.getPname()+"���"+repository+",������"+buy);
				}
				//��װһ�¹�ϵ������Ҫ��������ݼ�
				OrderItem item = new OrderItem();
				item.setOid(order.getId());
				item.setPid(cartModel.getPid());
				item.setBuynum(buy);
				orderItemList.add(item);
				//������Ʒ��Ŀ��
				pdao.updatePnumByPid(con,cartModel.getPid(),repository-buy);
				//ɾ�����ﳵcart�����������ύ����
				cdao.deleteCartByIds(con,order.getUid(), cartModel.getPid());
				
			}
			//order������������
			odao.addOrder(con,order);
			//�����ϵ��,��Ʒ+���� orderitems
			for(OrderItem item :orderItemList){
				oidao.add(con,item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Order> findOrderList(int uid) {
		List<Order> list = odao.findOrderListByUid(uid);
		return list;
	}
	@Override
	public Order findOrderById(String oid) {
		return odao.findOrderById(oid);
	}
	@Override
	public List<OrderItemModel> findOrderDetail(String oid) {
		return odao.findOrderItems(oid);
	}
	
	
	@Override
	public List<Order> findOrderListByTime(String startTime, String endTime,int uid) {
		List<Order> list = odao.findOrderListByTime(startTime,endTime,uid);
		return list;
	}
	
	//������֧������
	@Override
	public void updateOrderBuId(String oid) {
		// TODO Auto-generated method stub
		odao.updateOrderBuId(oid);
	}
	
	@Override
	public void addComment(String oid,int uid, String pid, String level, String comment) {
		comdao.addComment(oid,uid,pid,level,comment);
		oidao.setIsComment(oid,pid);
		
	}
	
	/**
	 * ��ҳ��ѯ����
	 */
	public PageBean findCommentByPid(String pid,int pageNum,int size){
		int startIndex = size*(pageNum-1);
		//������
		int total = comdao.findTotalCounts(pid);
		//����
		List<MyComment> list =  comdao.findCommentByPid(pid,startIndex,size);
		
		PageBean pageBean = new PageBean();
		
		pageBean.setData(list);
		//ÿҳ��ʾ��
		pageBean.setTotal(total);
		pageBean.setPageNum(pageNum);
		
		//��ҳ��
		int end = total%size==0?(total/size):(total/size+1);
		pageBean.setEnd(end);
		pageBean.setSize(size);
		return pageBean;
	}
	
	
	@Override
	public List<Order> findOrderListByUid(int uid) {
		List<Order> list = odao.findOrderListByUid(uid);
		return list;
	}
	
	
	@Override
	public void updateOrderStatus(String id) {
		 QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		 String sql = "update orders set status = 4 where id = ?";
		 try {
			runner.update(sql,id);
		} catch (Exception e) {
			e.printStackTrace();
			 throw new RuntimeException("���Ķ���״̬ʧ��");
		}
	}
	

}
