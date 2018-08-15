package vip.hht.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import vip.hht.beans.PageBean;
import vip.hht.beans.Product;
import vip.hht.beans.User;
import vip.hht.service.AdminProductServcie;

/**
 * 后台管理的商品
 * @author zhoumo
 *
 */
@Controller
public class AdminProductController {

	@Autowired
	private AdminProductServcie adminProductServcieImpl;
	
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/product/list")
	public String toProductList(String pageNum,Model model,HttpSession session){
		//判断超时
		User user = (User)session.getAttribute("user");
		if(user ==null){//超时
			//重定向到管理员登陆
			return "redirect:/admin/login";
		}
		//重复的代码每次展示商品菜单都需要
		PageBean pageBean = adminProductServcieImpl.findPageProduct(Integer.parseInt(pageNum));
		model.addAttribute("pageBean", pageBean);
		return "admin/product/list";
	}
	
	
	/**
	 * 跳转到修改
	 */
	@RequestMapping("/admin/product/toEdit")
	public String toEdit(Integer pageNum,String pid,Model model){
		//查询该条数据
		Product product = adminProductServcieImpl.findProductById(pid);
		System.out.println(product.getPimage());
		////记录当前页
		product.setPageNum(pageNum);
		//填充数据
		model.addAttribute("product", product);
		//给页面传一个字符串时间
		Date pdate = product.getPdate();
		if(pdate!=null){
			String strTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pdate);
			model.addAttribute("strTime", strTime);
		}
		//返回视图
		return "admin/product/edit";
	}
	
	/**
	 * 修改商品信息
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/admin/product/edit")
	public String edit(Product product,Model model,MultipartFile uploadImg,HttpSession session) throws Exception{
		String filename = UUID.randomUUID().toString().replaceAll("-", "");
		if(!uploadImg.isEmpty()){//用户上传图片了
			//获取图片后缀名.jsp
			String suffix = FilenameUtils.getExtension(uploadImg.getOriginalFilename());
			
			//获取上下文
			ServletContext servletContext = session.getServletContext();
			//获取绝对路劲
			String absolute = servletContext.getRealPath("/products");
			//保存到本地
			uploadImg.transferTo(new File(absolute,filename+"."+suffix));
			//路径保存到数据库
			product.setPimage("products/"+filename+"."+suffix);
		}else{//没有上传文件
			product.setPimage(product.getPimage());
		}
		//修改
		adminProductServcieImpl.editProduct(product);
		int pageNum = product.getPageNum();
		//重定向到分页查询
		return "redirect:/admin/product/list?pageNum="+pageNum;		
	}
	
	
	/**
	 * 跳转到添加商品页面
	 */
	@RequestMapping("/admin/product/toAdd")
	public String toAdd(String pageNum,Model model){
		//把当前页保存到打开的页面
		model.addAttribute("pageNum", pageNum);
		return "admin/product/add";
	}
	
	
	
	/**
	 * 添加商品
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/admin/product/add")
	public String add(Product product,MultipartFile uploadImg,HttpSession session) throws IllegalStateException, IOException{
		if(!uploadImg.isEmpty()){
			//获取上下文对象
			ServletContext servletContext = session.getServletContext();
			String absolutePath = servletContext.getRealPath("/products");
			//获取文件后缀名
			String suffix = FilenameUtils.getExtension(uploadImg.getOriginalFilename());
			//创建一个文件名
			String filename = UUID.randomUUID().toString().replaceAll("-", "");
			//保存到本地
			uploadImg.transferTo(new File(absolutePath,filename+"."+suffix));
			//放进product
			product.setPimage("products/"+filename+"."+suffix);
		}
		//创建一个主键
		String pid = UUID.randomUUID().toString().replaceAll("-", "");
		//设置主键
		product.setPid(pid);
		adminProductServcieImpl.addProduct(product);
		//获取当前页参数
		int pageNum = product.getPageNum();
		//重定向到分页查询
		return "redirect:/admin/product/list?pageNum="+pageNum;
		
	}
	
	/**
	 * 删除单个商品
	 */
	@RequestMapping("/admin/product/deleteById")
	public String deleteById(String pid,String pageNum){
		adminProductServcieImpl.deleteById(pid);
		return "redirect:/admin/product/list?pageNum="+pageNum;
	}
	
	/**
	 * 删除多个商品 ,逻辑删除
	 */
	@RequestMapping("/admin/product/banchDel")
	@ResponseBody
	public PageBean banchDel(Integer[] pids,String pageNum){
		System.out.println(pageNum);
		
		adminProductServcieImpl.banchDel(pids);
		
		PageBean pageBean = adminProductServcieImpl.findPageProduct(Integer.parseInt(pageNum));
		return pageBean;
	}
	
	
}



