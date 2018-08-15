package vip.hht.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import vip.hht.Tools.ImplFactory;
import vip.hht.beans.Cart;
import vip.hht.beans.Category;
import vip.hht.beans.PageBean;
import vip.hht.beans.Product;
import vip.hht.dao.CartDao;
import vip.hht.dao.CategoryDao;
import vip.hht.dao.ProductDao;
import vip.hht.service.ProductService;

public class ProductServiceImpl implements ProductService{
	private  ProductDao pdao =ImplFactory.getImpl(ProductDao.class);
	@Override
	public List findProductList() {
		List list = pdao.findProductList();
		return list;
	}
	@Override
	public Product findProductById(String pid) {
		Product product = pdao.findProductById(pid);
		String cid = product.getCid();
		CategoryDao cdao = ImplFactory.getImpl(CategoryDao.class);
		Category category=cdao.findCategoryById(cid);
		product.setCategory(category);
		return product;
	}
	@Override
	public void addCart(String pid, String cid, String buynum) {
		  CartDao cdao =ImplFactory.getImpl(CartDao.class);
		  //查询
		  Cart cart = cdao.findCartById(pid,cid);
		  //无记录时插入
		  if(cart==null){
			cdao.saveCart(pid,cid,buynum); 
			return;
		  }
		  //有记录时增加
		  int  xx = Integer.parseInt(buynum)+cart.getBuynum();
		  cdao.incrCart(pid,cid,xx);
	}
	@Override
	public PageBean findProductPageList(int pageNum1, int size1) {
		//计算startIndex
		int startIndex = size1*(pageNum1-1);
		//查询总条数
		int total = pdao.findTotalCount();
		//查询当前页数据集合
		List list = pdao.finPageList(startIndex,size1);
		//创建pageBean对象
		PageBean pageBean = new PageBean();
		//赋值并返回
		pageBean.setTotal(total);
		pageBean.setData(list);
		
		int end = total%size1==0 ?(total/size1):(total/size1+1);
		pageBean.setEnd(end);
		pageBean.setPageNum(pageNum1);
		pageBean.setSize(size1);
		return pageBean;
	}
	
	
	//本地地址
	private HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
	//远程地址
//	private HttpSolrServer solrServer = new HttpSolrServer("http://...:8080/solr");
	public PageBean findKeyWords(int pageNum1, int size1,String queryString,String catalog_name,String price,String sort) throws SolrServerException{
		//新建一个查询对象
		SolrQuery solrQuery = new SolrQuery();
		//设置关键词
		if(queryString !=null && !queryString.equals("")){
			solrQuery.setQuery(queryString);
			//设置默认搜索域
			solrQuery.set("df", "product_keywords");
		}else{
			solrQuery.set("q", "*:*");
		}
		//商品类型
		if(catalog_name!=null && !catalog_name.equals("")){
			solrQuery.addFilterQuery("product_catalog_name:"+catalog_name);
		}
	
		//过滤条件
		if(price!=null && !"".equals(price.trim())){
			String[] split = price.split("-");
			solrQuery.addFilterQuery("product_price:["+split[0]+" TO "+split[1]+"]");
		}
		//添加商品不下架过滤条件(pflag = 1 为下架,0为正常)
		solrQuery.addFilterQuery("product_pflag:0");
		
		//价格排序
		if(sort!=null && sort.equals("1")){
			solrQuery.setSort("shop_price", ORDER.desc);
		}else{
			solrQuery.setSort("shop_price", ORDER.asc);
		}
		//分页,开始,多少行
		//计算startIndex
		int startIndex = size1*(pageNum1-1);
		solrQuery.setStart(startIndex);
		solrQuery.setRows(size1);
		//设置显示的域
		solrQuery.setFields("id","product_name","shop_price","market_price","product_picture","product_catalog_name","product_description","product_pflag");
		
		//高亮显示开关
		solrQuery.setHighlight(true);
		//指定高亮显示的域
		solrQuery.addHighlightField("product_name");
		solrQuery.addHighlightField("product_catalog_name");
		solrQuery.addHighlightField("product_description");
		//设置高亮前标签
		solrQuery.setHighlightSimplePre("<font color='red'>");
		//s设置高亮后标签
		solrQuery.setHighlightSimplePost("</font>");
		//执行查询
		QueryResponse queryResponse = solrServer.query(solrQuery);
		
		//获取高亮结果集,通过id获取文档,通过文档中的域名获取多个结果集的高亮显示集合,当前只有一个元素
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		//获得结果集
		SolrDocumentList docs = queryResponse.getResults();
		//查询总条数
		int total = (int)docs.getNumFound();
		System.out.println("共查询到"+total+"条数据");
		
		List<Product> productModelList = new ArrayList<>();
		//处理结果集
		for (SolrDocument solrDocument : docs) {
			Product productModel = new Product();
			String id = (String)solrDocument.get("id");
			productModel.setPid((String)solrDocument.get("id"));
			Map<String, List<String>> map = highlighting.get(id);
			List<String> list = map.get("product_name");
			List<String> list2 = map.get("product_catalog_name");
			List<String> list3 = map.get("product_description");
			//如果高亮显示有这个字段
			if(list!=null && list.size()>0){
				productModel.setPname(list.get(0));
			}else{
				productModel.setPname((String) solrDocument.get("product_name"));;
			}
			if(list2!=null && list2.size()>0){
				productModel.setCname(list2.get(0));
			}else{
				productModel.setCname((String) solrDocument.get("product_catalog_name"));;
			}
			if(list3!=null && list3.size()>0){
				productModel.setPdesc(list3.get(0));
			}else{
				productModel.setPdesc((String) solrDocument.get("product_description"));;
			}
			productModel.setPimage((String) solrDocument.get("product_picture"));
			productModel.setShop_price(Double.valueOf(solrDocument.get("shop_price").toString()));
			productModel.setMarket_price(Double.valueOf(solrDocument.get("market_price").toString()));
			productModel.setPflag(Integer.valueOf(solrDocument.get("product_pflag").toString()));
/*			productModel.setCname(solrDocument.get("product_catalog_name").toString());
			productModel.setPdesc(solrDocument.get("product_description").toString());*/
			productModelList.add(productModel);
		}
		

		//创建pageBean对象
		PageBean pageBean = new PageBean();
		//赋值并返回
		pageBean.setTotal(total);
		pageBean.setData(productModelList);
		
		int end = total%size1==0 ?(total/size1):(total/size1+1);
		pageBean.setEnd(end);
		pageBean.setPageNum(pageNum1);
		pageBean.setSize(size1);
		return pageBean;
		
	}

	@Test
	public void test1(){
		try {
			System.out.println(findKeyWords(1, 10, "数码",null,null,""));
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}