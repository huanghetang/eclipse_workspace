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
     * �ϴ��ļ�
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
       
        
        HashMap<String, String> paramMap = new HashMap<String,String>();
        //�ϴ�ͼƬ
        String savePath = UploadFileUtils.uploadImage(req, paramMap, null);
        System.out.println(savePath);
        String username = paramMap.get("username");
        System.out.println(username);
    }
    
    
    /*  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
      //��ȡͼƬ�ļ��о���·��
        System.out.println("�ϴ���ʼ...");
        String conTextrealPath = req.getServletContext().getRealPath("images");
        RequestContext requestContext = new ServletRequestContext(req);
        //�ж��Ƿ���multipart/form-data��ʽ����������
        if(FileUpload.isMultipartContent(requestContext)){
            *//**
             * ��������Ϣʵ���е�ÿһ����Ŀ��װ�ɵ�����DiskFileItem (FileItem�ӿڵ�ʵ��) ���������
             *�� org.apache.commons.fileupload.FileItemFactory �ӿڵ�Ĭ��ʵ��
             *org.apache.commons.fileupload.disk.DiskFileItemFactory ����ɡ����ϴ����ļ���Ŀ�Ƚ�Сʱ��
             *ֱ�ӱ������ڴ��У��ٶȱȽϿ죩���Ƚϴ�ʱ������ʱ�ļ�����ʽ�������ڴ�����ʱ�ļ��У���Ȼ�ٶ���Щ�������ڴ���Դ�����޵ģ���
             *//*
            DiskFileItemFactory factory = new DiskFileItemFactory();
//            factory.setRepository(new File("D:/Upload/test/"));
            //���ñ����ļ�·��
            factory.setRepository(new File(conTextrealPath+"/"));
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
                    System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   " + new String
                            (fileItem.getString().getBytes("iso8859-1"), "utf-8"));
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
                        String newFileName = IDUtils.genImageName()+"."+extension;
                        File newFile = new File(conTextrealPath+"/"+newFileName);// "D:/Upload/test/" 
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
        }
    }*/

}