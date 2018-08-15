package vip.hht.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FilenameUtils;

/**
 * servelt�ϴ��ļ�
 * @author zhoumo
 * @createtime 2018/06/07
 *
 */
public class UploadFileUtils {
	//����ͼƬ����·��
	private static String basePath = "D:/images/"; 

	/**
	 * �����ļ������ش�����
	 * @param req ����
	 * @param paramMap ��Ҫһ����ű����ݵ�map
	 * @return �ϴ��ļ�����Ҫ���浽���ݿ��е�·��
	 * @throws UnsupportedEncodingException
	 */
	public static String uploadFile(HttpServletRequest req,Map<String,String> paramMap) throws UnsupportedEncodingException{
		 //��ȡͼƬ�ļ��о���·��
        System.out.println("�ϴ���ʼ...");
        String conTextrealPath = req.getServletContext().getRealPath("images");
        RequestContext requestContext = new ServletRequestContext(req);
        //�ж��Ƿ���multipart/form-data��ʽ����������
        //�ϴ��ļ��󱣴浽���ݿ��е�·��
        String newFileName = null;
        if(FileUpload.isMultipartContent(requestContext)){
            /**
             * ��������Ϣʵ���е�ÿһ����Ŀ��װ�ɵ�����DiskFileItem (FileItem�ӿڵ�ʵ��) ���������
             *�� org.apache.commons.fileupload.FileItemFactory �ӿڵ�Ĭ��ʵ��
             *org.apache.commons.fileupload.disk.DiskFileItemFactory ����ɡ����ϴ����ļ���Ŀ�Ƚ�Сʱ��
             *ֱ�ӱ������ڴ��У��ٶȱȽϿ죩���Ƚϴ�ʱ������ʱ�ļ�����ʽ�������ڴ�����ʱ�ļ��У���Ȼ�ٶ���Щ�������ڴ���Դ�����޵ģ���
             */
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //���ñ����ļ�·��
            factory.setRepository(new File(basePath));
//          factory.setRepository(new File(conTextrealPath+"/"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(2000000);
            List items = new ArrayList();
            try {
                items = upload.parseRequest(req);
            } catch (FileUploadException e1) {
                System.out.println("�ļ��ϴ���������" + e1.getMessage());
            }
            Iterator it = items.iterator();
            while(it.hasNext()){
                FileItem fileItem = (FileItem) it.next();
                //��ͨ������
                if(fileItem.isFormField()){
                    String key = fileItem.getFieldName();
                    String value = new String (fileItem.getString().getBytes("iso8859-1"), "utf-8");
/*                    System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   " +
                    				new String (fileItem.getString().getBytes("iso8859-1"), "utf-8"));*/
                    System.out.println(key+","+value);
                    paramMap.put(key,value);
                }else{
                	//�ļ�����(type="file")
                    System.out.println(fileItem.getFieldName() + "   " +
                            fileItem.getName() + "   " + fileItem.isInMemory() + "    " +
                            fileItem.getContentType() + "   " + fileItem.getSize());
                    //��ȫ���
                    if(fileItem.getName()!=null && fileItem.getSize()!=0){
                        File fullFile = new File(fileItem.getName());
                        //��ȡԭʼ�ļ���
                        String fileName = fullFile.getName();
                        //��ȡԭʼ�ļ���չ��
                        String extension = FilenameUtils.getExtension(fileName);
                        //���»����ļ�����(�����ظ�)
                        newFileName = IDUtils.genImageName()+"."+extension;
//                        File newFile = new File(conTextrealPath+"/"+newFileName);// "D:/Upload/test/" 
                        File newFile = new File(basePath+newFileName);
                        //���ļ�·�����浽���ݿ�
                        System.out.println(newFileName);
                        try {
                            fileItem.write(newFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("�ļ�û��ѡ�� �� �ļ�����Ϊ��");
                    }
                }
            }
            //���ñ��浽���ݿ��·��
            return "/images/"+newFileName;
        }
        return null;
       
	}
	
	
	
	/**
	 * �����ļ���tomcat������Ŀ��·����
	 * @param req
	 * @param paramMap  ��Ҫһ����ű����ݵ�map
	 * @param imagePath ��null
	 * @return ������Ҫ���浽���ݿ��·��
	 * @throws UnsupportedEncodingException
	 */
	public static String uploadImage(HttpServletRequest req,Map<String,String> paramMap,String imagePath) throws UnsupportedEncodingException{
		 //��ȡͼƬ�ļ��о���·��
       System.out.println("�ϴ���ʼ...");
       imagePath = req.getServletContext().getRealPath("products");
       RequestContext requestContext = new ServletRequestContext(req);
       //�ж��Ƿ���multipart/form-data��ʽ����������
       //�ϴ��ļ��󱣴浽���ݿ��е�·��
       String newFileName = null;
       if(FileUpload.isMultipartContent(requestContext)){
           /**
            * ��������Ϣʵ���е�ÿһ����Ŀ��װ�ɵ�����DiskFileItem (FileItem�ӿڵ�ʵ��) ���������
            *�� org.apache.commons.fileupload.FileItemFactory �ӿڵ�Ĭ��ʵ��
            *org.apache.commons.fileupload.disk.DiskFileItemFactory ����ɡ����ϴ����ļ���Ŀ�Ƚ�Сʱ��
            *ֱ�ӱ������ڴ��У��ٶȱȽϿ죩���Ƚϴ�ʱ������ʱ�ļ�����ʽ�������ڴ�����ʱ�ļ��У���Ȼ�ٶ���Щ�������ڴ���Դ�����޵ģ���
            */
           DiskFileItemFactory factory = new DiskFileItemFactory();
           //���ñ����ļ�·��
           factory.setRepository(new File(imagePath+"/"));
           ServletFileUpload upload = new ServletFileUpload(factory);
           upload.setSizeMax(2000000);
           List items = new ArrayList();
           try {
               items = upload.parseRequest(req);
           } catch (FileUploadException e1) {
               System.out.println("�ļ��ϴ���������" + e1.getMessage());
           }
           Iterator it = items.iterator();
           while(it.hasNext()){
               FileItem fileItem = (FileItem) it.next();
               //��ͨ������
               if(fileItem.isFormField()){
                   String key = fileItem.getFieldName();
                   String value = new String (fileItem.getString().getBytes("iso8859-1"), "utf-8");
/*                    System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   " +
                   				new String (fileItem.getString().getBytes("iso8859-1"), "utf-8"));*/
                   System.out.println(key+","+value);
                   paramMap.put(key,value);
               }else{
               	//�ļ�����(type="file")
                   System.out.println(fileItem.getFieldName() + "   " +
                           fileItem.getName() + "   " + fileItem.isInMemory() + "    " +
                           fileItem.getContentType() + "   " + fileItem.getSize());
                   //��ȫ���
                   if(fileItem.getName()!=null && fileItem.getSize()!=0){
                       File fullFile = new File(fileItem.getName());
                       //��ȡԭʼ�ļ���
                       String fileName = fullFile.getName();
                       //��ȡԭʼ�ļ���չ��
                       String extension = FilenameUtils.getExtension(fileName);
                       //���»����ļ�����(�����ظ�)
                       newFileName = IDUtils.genImageName()+"."+extension;
                       //���浽tomcat��Ŀ·����
                       File newFile = new File(imagePath+"/"+newFileName);
                       //���ļ�·�����浽���ݿ�
                       System.out.println(newFileName);
                       try {
                           fileItem.write(newFile);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }else{
                       System.out.println("�ļ�û��ѡ�� �� �ļ�����Ϊ��");
                   }
               }
           }
           //���ñ��浽���ݿ��·��
           return "products/"+newFileName;
       }
       return null;
      
	}

}
