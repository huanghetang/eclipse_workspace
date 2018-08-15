<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>我的足迹</title>
<%@include file="inc/common_head.jsp"%>
</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block clearfix">
		<div class="AreaR">
			<div class="block box">
				<div class="blank"></div>
				<div id="ur_here">
					当前位置: <a href="index.jsp">首页</a><code>&gt;</code>我的足迹
				</div>
			</div>
			<div class="blank"></div>
			<div class="box">
				<div class="box_1">
					<div class="userCenterBox boxCenterList clearfix" style="_height:1%;">
						<h5>
						<span>我的足迹</span>
						<c:if test="${not empty orderList}">
							<form action="OrderController?method=queryOrderByTime" method="post">
								<input type="text" id="test1" class="data" name="startTime" value="${startTime}">
								<input type="text" id="test2" class="data" name="endTime" value="${endTime}">
								<input type="submit" value="查询">
							</form>
						</c:if>
						</h5>
						<c:if test="${empty orderList}">
								<center><span>没有订单</span></center>
						</c:if>
						<c:if test="${not empty orderList}">
							<table width="100%" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#dddddd">
							<tr align="center">
								<td bgcolor="#ffffff">订单号</td>
								<td bgcolor="#ffffff" width="200px">下单时间</td>
								<td bgcolor="#ffffff">订单总金额</td>
								<td bgcolor="#ffffff">订单状态</td>
								<td bgcolor="#ffffff" width="200px">操作</td>
							</tr>
							<c:forEach items="${orderList}" var="o">
							<tr>
								<td align="center" bgcolor="#ffffff">
									<a href="javascript:;" class="f6">${o.id}</a>
								</td>
								<td align="center" bgcolor="#ffffff">${o.createtime}</td>
								<td align="right" bgcolor="#ffffff" style="text-align: center;">${o.totalprice}元</td>
								<c:if test="${o.status==0 }">
									<td align="center" bgcolor="#ffffff">
										<a href="OrderController?method=orderDetail&oid=${o.id}"><font color="gray">未支付</font></a>
									</td>
								</c:if>
								<c:if test="${o.status==1}">
									<td align="center" bgcolor="#ffffff">
										<a href="OrderController?method=orderDetail&oid=${o.id}"><font color="green">已支付</font></a>
									</td>
								</c:if>
								<td align="center" bgcolor="#ffffff">
									<a href="#">在线支付</a>&nbsp;
									<a href="javascript:;">取消订单</a>
								</td>
							</tr>
							</c:forEach>
						</table>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="inc/footer.jsp"%>
	
	
	
<script src="laydate/laydate.js"></script> <!-- 改成你的路径 -->
 <script>
//执行一个laydate实例
  laydate.render({
  elem: '#test1' //指定元素
});
laydate.render({
	elem: '#test2' //指定元素
}); 
/*  laydate.render({
elem: '.data' //指定元素
});  */
</script>
</body>
</html>