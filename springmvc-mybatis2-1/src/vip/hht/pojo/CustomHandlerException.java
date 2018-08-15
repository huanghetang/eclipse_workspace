package vip.hht.pojo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * springmvc的异常处理器是HandlerExceptionResolver
 * dao,service,controller出现异常向外抛出,最后由前端控制器交由HandlerExceptionResolver全局异常
 * 处理器(接口)来处理,自己实现接口写实现方法
 * 自定义异常处理类
 * @author zhoumo
 *
 */
public class CustomHandlerException implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {
		// TODO Auto-generated method stub
		ModelAndView mav = new ModelAndView("error");
		String message ="您走错地方了";
		try {
			if(e instanceof MyException){
				message = e.getMessage();
			}
			mav.addObject("message", message);
			System.out.println(e.getClass().getName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return mav;
	}

}
