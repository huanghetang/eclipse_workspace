package vip.hht.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import flexjson.JSONSerializer;
import vip.hht.beans.PCA;
import vip.hht.util.DBUtils;

public class AjaxServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from province_city_district where pid=?";
		try {
			List<PCA> list = queryRunner.query(sql,new BeanListHandler<PCA>(PCA.class), pid);
			 JSONSerializer jsonSerializer = new JSONSerializer();
			 String serialize = jsonSerializer.serialize(list);
			 System.out.println(serialize);
			 response.setContentType("text/html;charset=utf-8");
			 response.getWriter().write(serialize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
