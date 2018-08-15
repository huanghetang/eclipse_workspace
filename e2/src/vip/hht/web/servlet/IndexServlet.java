package vip.hht.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import vip.hht.Tools.ImplFactory;
import vip.hht.beans.Ad;
import vip.hht.beans.Bigad;
import vip.hht.service.IndexService;

public class IndexServlet extends BaseServlet {
	/**
	 * ��ѯ���ƥ��·��/indexʱ��ѯ
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void  adList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		IndexService service = ImplFactory.getImpl(IndexService.class);
		List<Ad> adList= service.adList();
		String ads = JSON.toJSONString(adList);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(ads);
		
	} 
	
	/**
	 * �鿴�������
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void adInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String ad_id = request.getParameter("id");
		if(ad_id!=null){
			IndexService service = ImplFactory.getImpl(IndexService.class);
			Ad adInfo=service.findAdInfoById(ad_id);
			//��ȡͼƬ
			System.out.println(adInfo.getAd_image());
			request.setAttribute("adInfo", adInfo);
			//����ͼƬ
			
			request.getRequestDispatcher("adInfo.jsp").forward(request, response);
		}
	}
	
	/**
	 * �鿴�ֲ�ͼƬ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void imgList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		IndexService service = ImplFactory.getImpl(IndexService.class);
		List<Bigad> imgList= service.imgList();
		String imgs = JSON.toJSONString(imgList);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(imgs);
	}
}