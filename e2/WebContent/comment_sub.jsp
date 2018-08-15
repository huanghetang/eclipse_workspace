<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">



<head>
    <%@include file="inc/common_head.jsp"%>
    <script>
	function listCart(){
		location.href ="${path}/ProductController?method=solrList";
	}

</script>
</head>
<body>
<%@include file="inc/header.jsp" %>
<div style="width:350px;margin:0 aotu;text-align:right;">
	<div class="result-tip">
		<i class="icon-success"></i> 评论成功
	</div>
	
	<div>
		<input onclick="listCart()"
		type="button" value="继续购物" class="btn2" />&nbsp;&nbsp;&nbsp;
		
		
	</div>
</div>
<%@include file="inc/footer.jsp" %>
</body>
</html>