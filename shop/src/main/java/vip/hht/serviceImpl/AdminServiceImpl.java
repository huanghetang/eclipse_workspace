package vip.hht.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vip.hht.beans.Category;
import vip.hht.beans.CategoryExample;
import vip.hht.beans.CategoryExample.Criteria;
import vip.hht.beans.PageBean;
import vip.hht.beans.PageParam;
import vip.hht.beans.Product;
import vip.hht.beans.QueryVo;
import vip.hht.beans.User;
import vip.hht.mapper.CategoryMapper;
import vip.hht.mapper.ProductMapper;
import vip.hht.mapper.UserMapper;
import vip.hht.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	

	
	@Override
	public User login(QueryVo queryVo) {
		User user = userMapper.findUserWhenLogin(queryVo);
		return user;
	}

	@Override
	public PageBean findAllCategory(int pageNum) {
		//计算分页起始索引
		int size = 3;//每页显示3条
		int startIndex = size*(pageNum-1);
		//查询
		PageParam pageParam = new PageParam();
		pageParam.setSize(size);
		pageParam.setStartIndex(startIndex);
		List<Category> categoryList = categoryMapper.selectCategory4Page(pageParam);
		//总条数
		CategoryExample example = new CategoryExample();
		int total = categoryMapper.countByExample(example);
		//创建分页对象
		PageBean pageBean = new PageBean();
		//初始化对象并返回
		pageBean.setTotal(total);
		pageBean.setSize(size);
		pageBean.setPageNum(pageNum);
		
		//总页数
		int end = total%size==0?(total/size):(total/size +1);
		pageBean.setEnd(end);
		pageBean.setData(categoryList);
		
		return pageBean;
	}

	@Override
	public Category finCategoryById(String id) {
		Category category = categoryMapper.selectByPrimaryKey(id);
		return category;
	}

	@Override
	public void editCategoryById(Category category) {
		int update = categoryMapper.updateByPrimaryKey(category);
		String b = update==1?"成功":"失败";
		System.out.println("修改"+b);
	}

	@Override
	public void delCategoryById(String cid) {
		//把依赖这个商品分类的所有商品cid清空
		//事务已经配置
		//清空和该条商品分类有关的所有cid
		int nc = categoryMapper.updateProductCidNull(cid);
		System.out.println("清空了商品中关联的cid共"+nc+"条");
//		int i=1/0;
		//删除商品管理
		int update = categoryMapper.deleteByPrimaryKey(cid);
		System.out.println("清空了商品分类中cid为"+cid+"共"+update+"条");
		
	}

	@Override
	public void addCategory(Category category) {
		int update = categoryMapper.insert(category);
		String b = update==1?"成功":"失败";
		System.out.println("添加"+b);
		
		
	}



}
