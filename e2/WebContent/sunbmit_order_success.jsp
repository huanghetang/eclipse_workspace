<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">

<script>
	function listCart(){
		location.href ="${pageContext.request.contextPath }/OrderController?method=listOrder";
	}

</script>

<head>
    <%@include file="inc/common_head.jsp"%>
</head>
<body>
<%@include file="inc/header.jsp" %>
<div style="width:350px;margin:0 aotu;text-align:right;">
	<div class="result-tip">
		<i class="icon-success"></i> 恭喜,下单成功...
	</div>
	
	<div>
		<%-- <input onclick="location.href='OrderController?method=orderDetail&oid=${id}'" type="button" value="去结算" class="btn2" />&nbsp;&nbsp;&nbsp;
		 --%>
		<input onclick="listCart()" type="button" value="查询订单" class="btn" />
	</div>
</div>
<%@include file="inc/footer.jsp" %>
</body>
</html>