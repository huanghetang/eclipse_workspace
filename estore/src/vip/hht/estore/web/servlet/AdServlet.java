package vip.hht.estore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import vip.hht.estore.beans.Ad;
import vip.hht.estore.beans.Bigad;
import vip.hht.estore.service.AdService;
import vip.hht.estore.web.serviceImpl.AdServiceImpl;
import vip.hht.utils.UploadFileUtils;

public class AdServlet extends BaseServlet {
	private AdService service = new AdServiceImpl();
	/**
	 * 查询所有的广告列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void adList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String pageStr = request.getParameter("page");//当前页
		String rowsStr = request.getParameter("rows");//每页显示数
		int page = pageStr==null?1:Integer.parseInt(pageStr);//默认第一页
		int rows = rowsStr==null?10:Integer.parseInt(rowsStr);//默认每页显示10条
		Map map = service.findAdByPage(page,rows);
		String jsonString = JSON.toJSONString(map);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString) ;
	}
/*	public void addAd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 	request.setCharacterEncoding("utf-8");
			String name = request.getParameter("adname");
	        String price = request.getParameter("adprice");
	        double money = Double.parseDouble(price);
	        String content = request.getParameter("adcontent");
	        //封装对象
	        Ad ad = new Ad();
	        ad.setAd_title(name);
	        ad.setAd_money(money);
	        ad.setAd_content(content);
	        service.addAd(ad);
	        response.sendRedirect(request.getContextPath()+"/adlist.html");
	}*/
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String cid = request.getParameter("ad_id");
		String[] splits = cid.split(",");
		for (String split : splits) {
			int ad_id = Integer.parseInt(split);
			service.delete(ad_id);
		}
		response.getWriter().write("1");
	}
	
	/**
	 * 添加功能 上传图片
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addAd(HttpServletRequest request, HttpServletResponse response) throws Exception{
	 	request.setCharacterEncoding("utf-8");
	 	//表单参数结果集
        HashMap<String, String> paramMap = new HashMap<String,String>();
        //上传图片
        String filePath = UploadFileUtils.uploadFile(request, paramMap);
        //获取表单参数
		String name = paramMap.get("adname");
        String price = paramMap.get("adprice");
        double money = Double.parseDouble(price);
        String content = paramMap.get("adcontent");
        
        //封装对象
        Ad ad = new Ad();
        ad.setAd_title(name);
        ad.setAd_money(money);
        ad.setAd_content(content);
        //图片路径
        ad.setAd_image(filePath);
        //保存
        service.addAd(ad);
        //重定向
        response.sendRedirect(request.getContextPath()+"/adlist.html");
	}
	
	/**
	 * 查找图片列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void  imgList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String pageStr = request.getParameter("page");//当前页
		String rowsStr = request.getParameter("rows");//每页显示数
		int page = pageStr==null?1:Integer.parseInt(pageStr);//默认第一页
		int rows = rowsStr==null?10:Integer.parseInt(rowsStr);//默认每页显示10条
		Map map = service.findImgByPage(page,rows);
		
		String jsonString = JSON.toJSONString(map);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString) ;
	}
	
	/**
	 * 添加轮播图片
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addImg(HttpServletRequest request, HttpServletResponse response) throws Exception{
	 	request.setCharacterEncoding("utf-8");
	 	//表单参数结果集
        HashMap<String, String> paramMap = new HashMap<String,String>();
        //上传图片
        String filePath = UploadFileUtils.uploadFile(request, paramMap);
        //获取表单参数
		String img_title = paramMap.get("img_title");
     
        
        //封装对象
		Bigad bigad = new Bigad();
        
     
		bigad.setTitle(img_title);
		bigad.setImage(filePath);
      
        //保存
        service.addImg(bigad);
        //重定向
        response.sendRedirect(request.getContextPath()+"/image.html");
	}
	
	/**
	 * 删除轮播图
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delImg(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String cid = request.getParameter("id");
		String[] splits = cid.split(",");
		for (String split : splits) {
			int img_id = Integer.parseInt(split);
			service.deleteImg(img_id);
		}
		response.getWriter().write("1");
	}
}