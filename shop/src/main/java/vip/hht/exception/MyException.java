package vip.hht.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义一个全局异常
 * dao-service-controller全部往外抛出异常时,交给DispatcherServlet处理
 * DispatcherServlet交给HandleExceptionResolver接口处理,所以需要自定义一个实现类
 * @author zhoumo
 *
 */
@Component
public class MyException implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//可以判断是不是我自定义的预期异常---
		
		//本次直接统一返回一个异常页面
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/index");
		mav.addObject("msg", "错了");
		ex.printStackTrace();
		return mav;
	}

}
