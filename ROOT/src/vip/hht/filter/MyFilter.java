package vip.hht.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;

/**
 * 乱码过滤器解决post和get请求乱码问题
 * 
 * 已经在server.xml中配置了URIEncoding="utf-8",测试时需要去掉
 * @author zhoumo
 * 
 */
public class MyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("MyFilter init---");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		MyRequest myRequest = new MyRequest(req);
		res.setContentType("text/html;charset=utf-8");
		
/*		String username = myRequest.getParameter("username");
		System.out.println(username);
		
		String username2 = myRequest.getParameter("username");
		System.out.println(username2);*/

		
		chain.doFilter(myRequest, res);

	}

	@Override
	public void destroy() {
		System.out.println("MyFilter destroy---");
	}

}
