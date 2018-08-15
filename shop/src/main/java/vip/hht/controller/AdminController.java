package vip.hht.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vip.hht.beans.Category;
import vip.hht.beans.PageBean;
import vip.hht.beans.QueryVo;
import vip.hht.beans.User;
import vip.hht.service.AdminService;

/**
 * 管理员登陆和商品类别
 * @author zhoumo
 *
 */
@Controller
public class AdminController {

	@Autowired
	private AdminService adminServiceImpl;
	
	
	/**
	 *跳转主页
	 * @return
	 */
	@RequestMapping("/admin")
	public String toLogin(){
		return "admin/index";
	}
	
	/**
	 * 管理员登陆
	 * @param queryVo
	 * @param session
	 * @return
	 */
	@RequestMapping("/admin/login")
	public String login(QueryVo queryVo,HttpSession session){
		User user = adminServiceImpl.login(queryVo);
		session.setAttribute("user", user);
		return "admin/home";
	}
	
	/**
	 * 跳转页面
	 * @param path
	 * @return
	 */
	@RequestMapping("/admin/{path}")
	public String redirectPath(@PathVariable String path){
		return "admin/"+path;
	}
	
	/**
	 * 点击左边dtree才进入展示页面,加分页
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/admin/category/list")//拦截的路径
	
	public String FindAllCategory(Model model,HttpSession session,int pageNum){
	User user = (User)session.getAttribute("user");
	if(user!=null){//超时或未登录
		PageBean pageBean =  adminServiceImpl.findAllCategory(pageNum);
		model.addAttribute("pageBean",pageBean);
		//返回的视图
		return "admin/welcome";
	}
	return "admin/index";
	
}

	
/*	public String FindAllCategory(Model model,HttpSession session){
		User user = (User)session.getAttribute("user");
		if(user!=null){//超时或未登录
			List<Category> categoryList =  adminServiceImpl.findAllCategory();
			System.out.println(categoryList);
			model.addAttribute("categoryList",categoryList);
			//返回的视图
			return "admin/welcome";
		}
		return "admin/index";
		
	}*/
	
	/**
	 * 跳转到修改页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/toModCategory")
	public String toModCategory(@RequestParam String id,Model model){
		Category category = adminServiceImpl.finCategoryById(id);
		model.addAttribute("category", category);
		return "admin/category/edit";
	}
	
	/**
	 * 修改category
	 */
	@RequestMapping("/admin/modCategory")
	public String modCategory(Category category,Model model){
		adminServiceImpl.editCategoryById(category);
		//重定向
		return "redirect:/admin/category/list?pageNum=1";

	}
	
	/**
	 * 根据cid删除category
	 */
	@RequestMapping("/admin/delCategory")
	public String delCategory(String id,Model model){
		adminServiceImpl.delCategoryById(id);
		//重定向
		return "redirect:/admin/category/list?pageNum=1";
		
	}
	
	/**
	 * 跳转添加页面
	 * @return
	 */
	@RequestMapping("/admin/toAddCategory")
	public String toAddGategory(){
		return "admin/category/add";
	}
	
	/**
	 * 添加商品类别
	 * @param category
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/addCategory")
	public String addGategory(Category category,Model model){
		//获取一个cid
		String cid = UUID.randomUUID().toString().replaceAll("-", "");
		//设置主键
		category.setCid(cid);
		adminServiceImpl.addCategory(category);
		//TODO
		return "redirect:/admin/category/list?pageNum=1";
	}
	
	public static void main(String[] args) {
		String str = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(str);
	}
}
