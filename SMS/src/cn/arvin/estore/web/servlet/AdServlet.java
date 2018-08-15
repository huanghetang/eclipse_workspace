package cn.arvin.estore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.arvin.estore.domain.Ad;
import cn.arvin.estore.domain.Bigad;
import cn.arvin.estore.service.AdService;
import cn.arvin.estore.service.impl.AdServiceImpl;
import cn.arvin.estore.utils.UploadFileUtils;



public class AdServlet extends BaseServlet {
	private AdService service = new AdServiceImpl();
	/**
	 * ��ѯ���еĹ���б�
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void adList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String pageStr = request.getParameter("page");//��ǰҳ
		String rowsStr = request.getParameter("rows");//ÿҳ��ʾ��
		int page = pageStr==null?1:Integer.parseInt(pageStr);//Ĭ�ϵ�һҳ
		int rows = rowsStr==null?10:Integer.parseInt(rowsStr);//Ĭ��ÿҳ��ʾ10��
		Map map = service.findAdByPage(page,rows);
		String jsonString = JSON.toJSONString(map);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString) ;
	}

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
	 * ��ӹ��� �ϴ�ͼƬ
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addAd(HttpServletRequest request, HttpServletResponse response) throws Exception{
	 	request.setCharacterEncoding("utf-8");
	 	//�����������
        HashMap<String, String> paramMap = new HashMap<String,String>();
        //�ϴ�ͼƬ
        String filePath = UploadFileUtils.uploadFile(request, paramMap);
        //��ȡ������
		String name = paramMap.get("adname");
        String price = paramMap.get("adprice");
        double money = Double.parseDouble(price);
        String content = paramMap.get("adcontent");
        
        //��װ����
        Ad ad = new Ad();
        ad.setAd_title(name);
        ad.setAd_money(money);
        ad.setAd_content(content);
        //ͼƬ·��
        ad.setAd_image(filePath);
        //����
        service.addAd(ad);
        //�ض���
        response.sendRedirect(request.getContextPath()+"/adlist.html");
	}
	
	/**
	 * ����ͼƬ�б�
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void  imgList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String pageStr = request.getParameter("page");//��ǰҳ
		String rowsStr = request.getParameter("rows");//ÿҳ��ʾ��
		int page = pageStr==null?1:Integer.parseInt(pageStr);//Ĭ�ϵ�һҳ
		int rows = rowsStr==null?10:Integer.parseInt(rowsStr);//Ĭ��ÿҳ��ʾ10��
		Map map = service.findImgByPage(page,rows);
		
		String jsonString = JSON.toJSONString(map);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString) ;
	}
	
	/**
	 * ����ֲ�ͼƬ
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addImg(HttpServletRequest request, HttpServletResponse response) throws Exception{
	 	request.setCharacterEncoding("utf-8");
	 	//�����������
        HashMap<String, String> paramMap = new HashMap<String,String>();
        //�ϴ�ͼƬ
        String filePath = UploadFileUtils.uploadFile(request, paramMap);
        //��ȡ������
		String img_title = paramMap.get("img_title");
     
        
        //��װ����
		Bigad bigad = new Bigad();
        
     
		bigad.setTitle(img_title);
		bigad.setImage(filePath);
      
        //����
        service.addImg(bigad);
        //�ض���
        response.sendRedirect(request.getContextPath()+"/image.html");
	}
	
	/**
	 * ɾ���ֲ�ͼ
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