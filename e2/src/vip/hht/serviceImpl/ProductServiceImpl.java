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
		  //��ѯ
		  Cart cart = cdao.findCartById(pid,cid);
		  //�޼�¼ʱ����
		  if(cart==null){
			cdao.saveCart(pid,cid,buynum); 
			return;
		  }
		  //�м�¼ʱ����
		  int  xx = Integer.parseInt(buynum)+cart.getBuynum();
		  cdao.incrCart(pid,cid,xx);
	}
	@Override
	public PageBean findProductPageList(int pageNum1, int size1) {
		//����startIndex
		int startIndex = size1*(pageNum1-1);
		//��ѯ������
		int total = pdao.findTotalCount();
		//��ѯ��ǰҳ���ݼ���
		List list = pdao.finPageList(startIndex,size1);
		//����pageBean����
		PageBean pageBean = new PageBean();
		//��ֵ������
		pageBean.setTotal(total);
		pageBean.setData(list);
		
		int end = total%size1==0 ?(total/size1):(total/size1+1);
		pageBean.setEnd(end);
		pageBean.setPageNum(pageNum1);
		pageBean.setSize(size1);
		return pageBean;
	}
	
	
	//���ص�ַ
	private HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
	//Զ�̵�ַ
//	private HttpSolrServer solrServer = new HttpSolrServer("http://...:8080/solr");
	public PageBean findKeyWords(int pageNum1, int size1,String queryString,String catalog_name,String price,String sort) throws SolrServerException{
		//�½�һ����ѯ����
		SolrQuery solrQuery = new SolrQuery();
		//���ùؼ���
		if(queryString !=null && !queryString.equals("")){
			solrQuery.setQuery(queryString);
			//����Ĭ��������
			solrQuery.set("df", "product_keywords");
		}else{
			solrQuery.set("q", "*:*");
		}
		//��Ʒ����
		if(catalog_name!=null && !catalog_name.equals("")){
			solrQuery.addFilterQuery("product_catalog_name:"+catalog_name);
		}
	
		//��������
		if(price!=null && !"".equals(price.trim())){
			String[] split = price.split("-");
			solrQuery.addFilterQuery("product_price:["+split[0]+" TO "+split[1]+"]");
		}
		//�����Ʒ���¼ܹ�������(pflag = 1 Ϊ�¼�,0Ϊ����)
		solrQuery.addFilterQuery("product_pflag:0");
		
		//�۸�����
		if(sort!=null && sort.equals("1")){
			solrQuery.setSort("shop_price", ORDER.desc);
		}else{
			solrQuery.setSort("shop_price", ORDER.asc);
		}
		//��ҳ,��ʼ,������
		//����startIndex
		int startIndex = size1*(pageNum1-1);
		solrQuery.setStart(startIndex);
		solrQuery.setRows(size1);
		//������ʾ����
		solrQuery.setFields("id","product_name","shop_price","market_price","product_picture","product_catalog_name","product_description","product_pflag");
		
		//������ʾ����
		solrQuery.setHighlight(true);
		//ָ��������ʾ����
		solrQuery.addHighlightField("product_name");
		solrQuery.addHighlightField("product_catalog_name");
		solrQuery.addHighlightField("product_description");
		//���ø���ǰ��ǩ
		solrQuery.setHighlightSimplePre("<font color='red'>");
		//s���ø������ǩ
		solrQuery.setHighlightSimplePost("</font>");
		//ִ�в�ѯ
		QueryResponse queryResponse = solrServer.query(solrQuery);
		
		//��ȡ���������,ͨ��id��ȡ�ĵ�,ͨ���ĵ��е�������ȡ���������ĸ�����ʾ����,��ǰֻ��һ��Ԫ��
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		//��ý����
		SolrDocumentList docs = queryResponse.getResults();
		//��ѯ������
		int total = (int)docs.getNumFound();
		System.out.println("����ѯ��"+total+"������");
		
		List<Product> productModelList = new ArrayList<>();
		//��������
		for (SolrDocument solrDocument : docs) {
			Product productModel = new Product();
			String id = (String)solrDocument.get("id");
			productModel.setPid((String)solrDocument.get("id"));
			Map<String, List<String>> map = highlighting.get(id);
			List<String> list = map.get("product_name");
			List<String> list2 = map.get("product_catalog_name");
			List<String> list3 = map.get("product_description");
			//���������ʾ������ֶ�
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
		

		//����pageBean����
		PageBean pageBean = new PageBean();
		//��ֵ������
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
			System.out.println(findKeyWords(1, 10, "����",null,null,""));
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}