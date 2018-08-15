package vip.hht.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vip.hht.pojo.Items;
import vip.hht.pojo.QueryVo;
import vip.hht.service.UserServiceImpl;

@Controller
public class ItemController {
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping("/itemList.action")
	public ModelAndView queryItemList(){
		
		ModelAndView mav = new ModelAndView();
		
		List<Items> list = userService.selectItems();

		mav.setViewName("itemList");
		mav.addObject("itemList", list);
		return mav;
		
	}
	
	//基本类型,pojo类型,包装类型
	@RequestMapping("/itemEdit.action")
//	public ModelAndView toEdit(@RequestParam(value="id1",required=false,defaultValue="1") Integer id,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		public ModelAndView toEdit(Integer id,HttpServletRequest request,HttpServletResponse response,HttpSession session){
//		String id = request.getParameter("id"); 
		Items items = userService.selectItems(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editItem");
		mav.addObject("item", items);
		return mav;
	}

	//修改
	@RequestMapping(value="/update.action")
	public ModelAndView updateitem(QueryVo vo){
//		String id = request.getParameter("id");
		System.out.println("===============================");
		System.out.println(vo);
		Items items = vo.getItems();
//		items.setCreatetime(new Date());
		System.out.println(items);
		userService.updateItems(items);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("success");
		return mav;
	}
	
}
