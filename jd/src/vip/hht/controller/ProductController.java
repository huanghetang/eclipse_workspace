package vip.hht.controller;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vip.hht.pojo.ProductModel;
import vip.hht.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;
	
	@RequestMapping("/list.action")
	public String list(){
		return "product_list";
	}
	
	
	@RequestMapping("/filterQuery.action")
	public String filterQuery(String queryString,String catalog_name,
			String price,String sort,Model model){
		List<ProductModel> productList;
		try {
			productList = service.findAll(queryString, catalog_name, price, sort);
			model.addAttribute("productList", productList);
			model.addAttribute("queryString", queryString);
			model.addAttribute("catalog_name", catalog_name);
			model.addAttribute("price", price);
			model.addAttribute("sort", sort);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
		return "product_list";
		
	}

}
