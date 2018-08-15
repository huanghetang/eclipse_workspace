package vip.hht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vip.hht.beans.PageBean;
import vip.hht.beans.Product;
import vip.hht.beans.ProductVo;
import vip.hht.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productServiceImpl;
	
	
	/**
	 * 查询所有商品
	 */
	@RequestMapping("/product/product_list")
	public String findAll(Model model){
		List<ProductVo> productList = productServiceImpl.findAll();
		model.addAttribute("productList", productList);
		return "product_list";
	}
	
	
	/**
	 * 查询商品并分页显示
	 */
	@RequestMapping("/product/page")
	public String findPageList(@RequestParam long pageNum,Model model){
		PageBean pageBean = productServiceImpl.findPageList(pageNum);
		model.addAttribute("pageBean", pageBean);
		return "product_list";
	}
}
