<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">

<head>
    <%@include file="inc/common_head.jsp"%>
</head>

<body>
<%@include file="inc/header.jsp" %>
<h1 style="padding-top:20px;text-align:center;">
<div><font color="green">支付成功！</div>
<div>
<input onclick="location.href='OrderController?method=listOrder'" type="button" value="查看订单信息" class="btn" />
</div>

</h1>
<%@include file="inc/footer.jsp" %>
</body>
</html>