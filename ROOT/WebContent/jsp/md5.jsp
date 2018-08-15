<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta charset="utf-8"> -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">

		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>Bootstrap 101 Template</title>

		<!-- Bootstrap -->
		<link href="../css/bootstrap.min.css" rel="stylesheet">
		<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
		<script src="../js/jquery.min.js"></script>
		<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
		<script src="../js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<%-- <form action="/MD5" method="post">
	<span style="text-align: center;">请输入需要MD5加密的信息:<input type="text" name="mimi"><br/></span>
	<span style="text-align: center;">加密结果为:<input type="text" name="retVal" value="${retVal}"></span>
<input type="submit">
</form> --%>

<%= pageContext.getResponse() %><br>
<%= pageContext.getServletConfig() %><br>
<%= pageContext.getServletContext() %><br>
<%= pageContext.getSession()%><br>

<%-- ${pageScope} --%>
${requestScope.request}
${sessionScope.session}
<%-- ${applicationScope}
 --%>
 
 
 <%-- 演示pageContext获取其他8个内置对象 --%>



<%= pageContext.getRequest() %>
<%= pageContext.getResponse() %>
<%= pageContext.getSession() %>
<%= pageContext.getServletContext() %>
<%= pageContext.getServletConfig() %>
<%= pageContext.getOut() %>
<%= pageContext.getException() %>
<%= pageContext.getPage() %>
<hr>
<%-- 演示设置数据和取出数据 --%>
存数据
<% pageContext.setAttribute("hehe", "hehe1", PageContext.APPLICATION_SCOPE); %>
<%= pageContext.getAttribute("hehe", pageContext.APPLICATION_SCOPE) %>


<form action="/MD5" method="post">
		<div class="container-fluid ">
			<div class="row">
				<div class="col-md-6 text-center">
					请输入需要MD5加密的信息:
				</div>
				
				<div class="col-md-6">
				<input type="text" name="mimi">
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 text-center">
				<input type="submit">
				</div>
			</div>
			
				<div class="row">
				<div class="col-md-6 text-center">
					加密结果为
				</div>
				
				<div class="col-md-6">
				<input type="text" name="retVal" value="${retVal}">
				</div>
				</div>
			
		</div>
		
		</form>
</body>
</html>