<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告栏</title>
<%@include file="inc/common_head.jsp"%>
<link href="data/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
	
		<center><span style="font-size: 20px">${adInfo.ad_title }</span>
		<p>${adInfo.ad_content}</p>
		<c:if test="${not empty adInfo.ad_image}">
			<%-- <c:if test="${fn:contains(adInfo.ad_image, 'mp4')}">
				<embed src="${adInfo.ad_image}" width="600px" height="400px" autostart="true" loop="false"/>
			</c:if> --%>
			<!-- 显示多媒体 -->
			<embed src="${adInfo.ad_image}" width="100%" height="600px" autostart="true" loop="false"/>
		</c:if>	
		<%-- <img  src="${adInfo.ad_image}" onerror="this.style.display='none'" > --%>
				
				
		</center>

</body>
</html>





