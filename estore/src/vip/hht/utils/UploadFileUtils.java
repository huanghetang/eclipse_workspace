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
 * servelt上传文件
 * @author zhoumo
 * @createtime 2018/06/07
 *
 */
public class UploadFileUtils {
	//配置图片保存路径
	private static String basePath = "D:/images/"; 

	/**
	 * 保存文件到本地磁盘上
	 * @param req 请求
	 * @param paramMap 需要一个存放表单数据的map
	 * @return 上传文件后需要保存到数据库中的路径
	 * @throws UnsupportedEncodingException
	 */
	public static String uploadFile(HttpServletRequest req,Map<String,String> paramMap) throws UnsupportedEncodingException{
		 //获取图片文件夹绝对路径
        System.out.println("上传开始...");
        String conTextrealPath = req.getServletContext().getRealPath("images");
        RequestContext requestContext = new ServletRequestContext(req);
        //判断是否是multipart/form-data格式的数据类型
        //上传文件后保存到数据库中的路径
        String newFileName = null;
        if(FileUpload.isMultipartContent(requestContext)){
            /**
             * 将请求消息实体中的每一个项目封装成单独的DiskFileItem (FileItem接口的实现) 对象的任务
             *由 org.apache.commons.fileupload.FileItemFactory 接口的默认实现
             *org.apache.commons.fileupload.disk.DiskFileItemFactory 来完成。当上传的文件项目比较小时，
             *直接保存在内存中（速度比较快），比较大时，以临时文件的形式，保存在磁盘临时文件夹（虽然速度慢些，但是内存资源是有限的）。
             */
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置保存文件路径
            factory.setRepository(new File(basePath));
//          factory.setRepository(new File(conTextrealPath+"/"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(2000000);
            List items = new ArrayList();
            try {
                items = upload.parseRequest(req);
            } catch (FileUploadException e1) {
                System.out.println("文件上传发生错误" + e1.getMessage());
            }
            Iterator it = items.iterator();
            while(it.hasNext()){
                FileItem fileItem = (FileItem) it.next();
                //普通表单类型
                if(fileItem.isFormField()){
                    String key = fileItem.getFieldName();
                    String value = new String (fileItem.getString().getBytes("iso8859-1"), "utf-8");
/*                    System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   " +
                    				new String (fileItem.getString().getBytes("iso8859-1"), "utf-8"));*/
                    System.out.println(key+","+value);
                    paramMap.put(key,value);
                }else{
                	//文件类型(type="file")
                    System.out.println(fileItem.getFieldName() + "   " +
                            fileItem.getName() + "   " + fileItem.isInMemory() + "    " +
                            fileItem.getContentType() + "   " + fileItem.getSize());
                    //安全检查
                    if(fileItem.getName()!=null && fileItem.getSize()!=0){
                        File fullFile = new File(fileItem.getName());
                        //获取原始文件名
                        String fileName = fullFile.getName();
                        //获取原始文件扩展名
                        String extension = FilenameUtils.getExtension(fileName);
                        //重新换个文件名称(避免重复)
                        newFileName = IDUtils.genImageName()+"."+extension;
//                        File newFile = new File(conTextrealPath+"/"+newFileName);// "D:/Upload/test/" 
                        File newFile = new File(basePath+newFileName);
                        //将文件路径保存到数据库
                        System.out.println(newFileName);
                        try {
                            fileItem.write(newFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("文件没有选择 或 文件内容为空");
                    }
                }
            }
            //设置保存到数据库的路径
            return "/images/"+newFileName;
        }
        return null;
       
	}
	
	
	
	/**
	 * 保存文件到tomcat发布项目根路径下
	 * @param req
	 * @param paramMap  需要一个存放表单数据的map
	 * @param imagePath 传null
	 * @return 返回需要保存到数据库的路径
	 * @throws UnsupportedEncodingException
	 */
	public static String uploadImage(HttpServletRequest req,Map<String,String> paramMap,String imagePath) throws UnsupportedEncodingException{
		 //获取图片文件夹绝对路径
       System.out.println("上传开始...");
       imagePath = req.getServletContext().getRealPath("products");
       RequestContext requestContext = new ServletRequestContext(req);
       //判断是否是multipart/form-data格式的数据类型
       //上传文件后保存到数据库中的路径
       String newFileName = null;
       if(FileUpload.isMultipartContent(requestContext)){
           /**
            * 将请求消息实体中的每一个项目封装成单独的DiskFileItem (FileItem接口的实现) 对象的任务
            *由 org.apache.commons.fileupload.FileItemFactory 接口的默认实现
            *org.apache.commons.fileupload.disk.DiskFileItemFactory 来完成。当上传的文件项目比较小时，
            *直接保存在内存中（速度比较快），比较大时，以临时文件的形式，保存在磁盘临时文件夹（虽然速度慢些，但是内存资源是有限的）。
            */
           DiskFileItemFactory factory = new DiskFileItemFactory();
           //设置保存文件路径
           factory.setRepository(new File(imagePath+"/"));
           ServletFileUpload upload = new ServletFileUpload(factory);
           upload.setSizeMax(2000000);
           List items = new ArrayList();
           try {
               items = upload.parseRequest(req);
           } catch (FileUploadException e1) {
               System.out.println("文件上传发生错误" + e1.getMessage());
           }
           Iterator it = items.iterator();
           while(it.hasNext()){
               FileItem fileItem = (FileItem) it.next();
               //普通表单类型
               if(fileItem.isFormField()){
                   String key = fileItem.getFieldName();
                   String value = new String (fileItem.getString().getBytes("iso8859-1"), "utf-8");
/*                    System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   " +
                   				new String (fileItem.getString().getBytes("iso8859-1"), "utf-8"));*/
                   System.out.println(key+","+value);
                   paramMap.put(key,value);
               }else{
               	//文件类型(type="file")
                   System.out.println(fileItem.getFieldName() + "   " +
                           fileItem.getName() + "   " + fileItem.isInMemory() + "    " +
                           fileItem.getContentType() + "   " + fileItem.getSize());
                   //安全检查
                   if(fileItem.getName()!=null && fileItem.getSize()!=0){
                       File fullFile = new File(fileItem.getName());
                       //获取原始文件名
                       String fileName = fullFile.getName();
                       //获取原始文件扩展名
                       String extension = FilenameUtils.getExtension(fileName);
                       //重新换个文件名称(避免重复)
                       newFileName = IDUtils.genImageName()+"."+extension;
                       //保存到tomcat项目路径下
                       File newFile = new File(imagePath+"/"+newFileName);
                       //将文件路径保存到数据库
                       System.out.println(newFileName);
                       try {
                           fileItem.write(newFile);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }else{
                       System.out.println("文件没有选择 或 文件内容为空");
                   }
               }
           }
           //设置保存到数据库的路径
           return "products/"+newFileName;
       }
       return null;
      
	}

}
