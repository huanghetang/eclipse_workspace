package vip.hht.servlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FilenameUtils;

import vip.hht.util.IDUtils;
import vip.hht.util.UploadFileUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jerei on 2017/3/19.
 */
public class MultipartTestServlet extends HttpServlet {

    public MultipartTestServlet() {
        super();
    }

    /**
     * 上传文件
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
       
        
        HashMap<String, String> paramMap = new HashMap<String,String>();
        //上传图片
        String savePath = UploadFileUtils.uploadImage(req, paramMap, null);
        System.out.println(savePath);
        String username = paramMap.get("username");
        System.out.println(username);
    }
    
    
    /*  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
      //获取图片文件夹绝对路径
        System.out.println("上传开始...");
        String conTextrealPath = req.getServletContext().getRealPath("images");
        RequestContext requestContext = new ServletRequestContext(req);
        //判断是否是multipart/form-data格式的数据类型
        if(FileUpload.isMultipartContent(requestContext)){
            *//**
             * 将请求消息实体中的每一个项目封装成单独的DiskFileItem (FileItem接口的实现) 对象的任务
             *由 org.apache.commons.fileupload.FileItemFactory 接口的默认实现
             *org.apache.commons.fileupload.disk.DiskFileItemFactory 来完成。当上传的文件项目比较小时，
             *直接保存在内存中（速度比较快），比较大时，以临时文件的形式，保存在磁盘临时文件夹（虽然速度慢些，但是内存资源是有限的）。
             *//*
            DiskFileItemFactory factory = new DiskFileItemFactory();
//            factory.setRepository(new File("D:/Upload/test/"));
            //设置保存文件路径
            factory.setRepository(new File(conTextrealPath+"/"));
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
                    System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   " + new String
                            (fileItem.getString().getBytes("iso8859-1"), "utf-8"));
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
                        String newFileName = IDUtils.genImageName()+"."+extension;
                        File newFile = new File(conTextrealPath+"/"+newFileName);// "D:/Upload/test/" 
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
        }
    }*/

}