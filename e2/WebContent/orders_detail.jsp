<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详情</title>
<%@include file="inc/common_head.jsp"%>
<script type="text/javascript">
	/* $(function(){
		var status =${order.status};
		$("#table1 tr")
	}); */
</script>
</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block clearfix">
		<div class="AreaR">
			<div class="block box">
				<div class="blank"></div>
				<div id="ur_here">
					当前位置: <a href="index.jsp">首页</a>
					<code>&gt;</code>
					用户中心
				</div>
			</div>
			<div class="blank"></div>
			<div class="box">
				<div class="box_1">
					<div class="userCenterBox boxCenterList clearfix"
						style="_height:1%;">
						<h5>
							<span>订单状态</span>
						</h5>
						<table id="table1" width="100%" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#dddddd">
							<tr>
								<td width="15%" align="right">订单编号：</td>
								<td align="left">${order.id}</td>
							</tr>
							<tr>
								<td width="15%" align="right">订单状态：</td>
								<td align="left">
									<c:if test="${order.status==0}">
										<font color="gray">未支付</font>
									</c:if>	
									<c:if test="${order.status==1}">
										<font color="green">已支付</font>
									</c:if>	
									<c:if test="${order.status==2}">
										<font color="red">待审核</font>
									</c:if>	
									<c:if test="${order.status==3}">
										<font color="blue">已审核</font>
									</c:if>	
									<!-- <font color="gray">已过期</font>？ -->
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">下单时间：</td>
								<td align="left">${order.createtime}</td>
							</tr>
							<tr>
								<td align="right">收货人信息：</td>
								<td align="left">${order.address}</td>
							</tr>
						</table>
						<div class="blank"></div>
						<h5><span>商品列表</span></h5>
						<c:if test="${empty itemModelList}">
						<span>没有订单商品明细</span>
						</c:if>
						<c:if test="${not empty itemModelList}">
						<table id="table2" width="100%" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#dddddd">
							<tr>
								<th width="20%" align="center">商品名称</th>
								<th width="20%" align="center">市场价格</th>
								<th width="20%" align="center">商品价格</th>
								<th width="10%" align="center">购买数量</th>
								<th width="20%" align="center">小计</th>
								<th width="20%" align="center"></th>
							</tr>
							<c:forEach items="${itemModelList}" var="im">
							<tr>
								<td style="text-align: center;">
									<a href="${pageContext.request.contextPath}/ProductController?method=productDetail&pid=${im.pid}" class="f6">${im.pname}</a>
								</td>
								<td style="text-align: center;">${im.market_price }元</td>
								<td style="text-align: center;">${im.shop_price}元</td>
								<td align="center">${im.buynum}</td>
								<td style="text-align: center;">${im.buynum*im.shop_price}元</td>
									<c:if test="${order.status ==1}">
										<td style="text-align: center;">
										<c:if test="${im.isComment==0}">
											<a href=${pageContext.request.contextPath}/ProductController?method=productDetail&pid=${im.pid}&oid=${order.id}>立即评价</a>
										</c:if>
										<c:if test="${im.isComment==1}">
											<font>已评价</font>
										</c:if>
										</td>
									</c:if>
									<c:if test="${order.status != 1 }">
										<td></td>
									</c:if>
								
							</tr>
							</c:forEach>
							<tr>
								<td colspan="6" style="text-align:right;padding-right:10px;font-size:25px;">
									商品总价&nbsp;<font color="red">&yen;${order.totalprice}</font>元
									<c:if test="${order.status ==0 }">
										<a href="pay.jsp?oid=${order.id}&totalPrice=0.01"><input value="确认支付" type="button" class="btn" /></a>
										<%-- <a href="pay.jsp?oid=${order.id}"><input value="取消订单" type="button" class="btn" /></a> --%>
									</c:if>
									<c:if test="${order.status ==1 }">
										<a href="OrderController?method=goodsReturn&oid=${order.id }"><input value="申请退货" type="button" class="btn" /></a>
									</c:if>
								</td>
							</tr>
						</table>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>