package vip.hht.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vip.hht.beans.QueryVo;
import vip.hht.beans.User;
import vip.hht.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userServiceImpl;
	/**
	 * 跳转登陆
	 * @return
	 */
/*	@RequestMapping("/login")
	public String toLogin(){
		return "login";
	}*/
	
	/**
	 * 更换验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/checkCode")
	public void checkCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//  创建画布
		int width = 120;
		int height = 40;
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//  获得画笔
		Graphics g = bufferedImage.getGraphics();
		//  填充背景颜色
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		//  绘制边框
		g.setColor(Color.red);
		g.drawRect(0, 0, width - 1, height - 1);
		//  生成随机字符
		//  准备数据
		String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		//  准备随机对象
		Random r = new Random();
		//  声明一个变量 保存验证码
		String code = "";
		//  书写4个随机字符
		for (int i = 0; i < 4; i++) {
			//  设置字体
			g.setFont(new Font("宋体", Font.BOLD, 28));
			//  设置随机颜色
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

			String str = data.charAt(r.nextInt(data.length())) + "";
			g.drawString(str, 10 + i * 28, 30);

			//  将新的字符 保存到验证码中
			code = code + str;
		}
		//  绘制干扰线
		for (int i = 0; i < 6; i++) {
			//  设置随机颜色
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		}

		//  将验证码 打印到控制台
		System.out.println(code);

		//  将验证码放到session中
		request.getSession().setAttribute("code_session", code);

		//  将画布显示在浏览器中
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		
	}
	
	
	/**
	 * 登陆
	 * @throws IOException 
	 */
	@RequestMapping("/doLogin")
	public String doLogin(QueryVo vo,HttpServletResponse response,HttpServletRequest request,Model model) throws IOException{
		//查看验证码对不对
		String yzm = (String)request.getSession().getAttribute("code_session");
		if(yzm!=null && yzm.equalsIgnoreCase(vo.getCheckCode())){//对
			
			User u = userServiceImpl.findUserWhenLogin(vo);
			if(u!=null){//登陆成功
				//记住用户名
				if("on".equals(vo.getRember())){
//					String encodeValue = URLEncoder.encode("我", "utf-8");
					Cookie cookie = new Cookie("username",u.getEmail());
					cookie.setMaxAge(60*60*24*7);
					response.addCookie(cookie);
					
				}else{//清除用客户端cookie用户名
					Cookie cookie = new Cookie("username","");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				//加入数据
//				model.addAttribute("user", u);
				request.getSession().setAttribute("user", u);
				
				//返回主页
				return "index";
			}
		}
		model.addAttribute("msg","验证码错误");
		//登陆失败,返回主页
		return "login";
	}
	
	/**
	 * 注销
	 */
	@RequestMapping("/loginout")
	public String loginout(HttpSession session){
		session.invalidate();
		//清空user
		return "login";
	}
	
	
	/**
	 * 页面跳转
	 */
	
	@RequestMapping("/{path}")
	public String login(@PathVariable String path,HttpSession session){
		//已登陆可以跳转
		User user = (User)session.getAttribute("user");
		if(user!=null){
			return path;
		}
		//没有登陆,跳转到登陆
		return "login";
	}

}
