<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet" />
<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
body,td,th {
	color: #000000;
}
-->
    </style>
<style>
BODY {SCROLLBAR-FACE-COLOR: #cccccc; SCROLLBAR-HIGHLIGHT-COLOR: #ffffFF; SCROLLBAR-SHADOW-COLOR: #ffffff; SCROLLBAR-3DLIGHT-COLOR: #cccccc; SCROLLBAR-ARROW-COLOR:  #ffffff; SCROLLBAR-TRACK-COLOR: #ffffFF; SCROLLBAR-DARKSHADOW-COLOR: #cccccc; }
</style>
</head>

<body>

<form name="Form1" method="post" action="name.aspx" id="Form1">

	<table width="100%" border="0" height="88" border="1"<%--  background="${pageContext.request.contextPath}/images/back1.JPG" --%>>
		<tr>
			<td colspan=3 class="ta_01" align="center" bgcolor="#afd1f3"><strong>系统首页</strong></td>
		</tr>
		<!--  -->
		<c:if test="${empty pageBean.data}">
		<tr>
			<td width="65%" height="84" align="center" valign="top" >
				<span class="Style1">无数据</span>
			</td>
		</tr>
		</c:if>
		<c:if test="${ not empty pageBean.data}">
			<tr>
			<!-- 	<td height=2>全选</td> -->
				<td height=2 class="ta_01">商品类别id</td>
				<td height=2 class="ta_01">商品类别名称</td>
				<td height=2 class="ta_01">修改</td>
				<td height=2 class="ta_01">删除</td>
			</tr>
		<c:forEach items="${pageBean.data}" var="category">
			<tr>
				<%-- <td><input type="checkbox" name="cid" value="${category.cid}"></td> --%>
				<td height=2>${category.cid}</td>
				<td height=2>${category.cname}</td>
				<td height=2>
					<a href="${pageContext.request.contextPath}/admin/toModCategory?id=${category.cid}">修改</a>
				</td>
				<td height=2>
					<a href="${pageContext.request.contextPath}/admin/delCategory?id=${category.cid}">删除</a>
				</td>
			</tr>
		</c:forEach>
		<!-- 显示 分页条-->
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
					<c:forEach items="${pageBean.bar}" var="currentPageNumber">
						<a href="${pageContext.request.contextPath }/admin/product/list?pageNum=${currentPageNumber}">
							<span <c:if test="${pageBean.pageNum == currentPageNumber }">style="color: red;"</c:if>><u><i>${currentPageNumber}&nbsp;&nbsp;</i></u></span>
							
						</a>
					</c:forEach>
					</td>
				</tr>
		
		
			<tr>
			<td colspan="4" align="left">
				<a href="${pageContext.request.contextPath}/admin/toAddCategory">添加分类</a>
			</td>
			</tr>
		</c:if>
		
	</table>

	</form>
	

</body>

</html>