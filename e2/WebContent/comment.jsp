<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品评价</title>
<%@include file="inc/common_head.jsp"%>
<script type="text/javascript" src="themes/ecmoban_jumei/js/action.js"></script>
<script type="text/javascript" src="themes/ecmoban_jumei/js/mzp-packed-me.js"></script>
<script type="text/javascript" src="themes/ecmoban_jumei/js/good_detail.js"></script>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>



<script type="text/javascript">
 	$(function(){
 		/*var picturecode_flag = false;
		
		$("#captcha").change(function(){
			
			if(this.value==""||this.value.length!=4){
				$("#check_code_notice").html('<font color=red>图片验证码有误</font>');
				picturecode_flag = false;
				return;
			}
			
			$.get(
				"RegisterServlet?method=checkyzm",		
				"yzm="+this.value,
				function(data){
					if(data==1){
						$("#check_code_notice").html("<font style='color:green'>√</font>");
						picturecode_flag = true;
					}else{
						$("#check_code_notice").html("验证码错误");
						picturecode_flag = false;
					}
				},
				"text"
			);
			
		}); */
	
		
		$("#comm_sub").click(function(){
			if($("textarea").val()==""){
				alert("评论不能为空");
				return;
			}
			
			var picturecode_flag = true;
			if(picturecode_flag){
				
				$("#commentForm").submit();
			}else{
				alert("请将信息填写完全")
			}
		});
	});
</script>

</head>
<body>
<%@ include file="inc/header.jsp"%>
<div class="block clearfix">
<div class="AreaR" style="width:950px;">
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href=".">首页</a>
			<code>&gt;</code>
			商品评价
		</div>
	</div>
	<div class="blank"></div>
	<!-- 商品信息开始 -->
	<div id="goodsInfo" class="clearfix">
		<!-- 商品图片信息开始 -->
		<div class="imgInfo">
			<!-- 当前显示的大图 -->
			<a href="${product.pimage}"
				id="zoom1" class="MagicZoom MagicThumb">
				<img src="${product.pimage}"
				width="360px;" height="360px" />
			</a>
			<!-- 下方的图片列表 -->
			
			<!-- <script type="text/javascript">mypicBg();</script> -->
		</div>
		<!-- 商品文字信息 -->
		<div class="textInfo">
			<h1 class="clearfix">${product.pname }</h1>
			<ul class="ul2 clearfix">
				<li class="clearfix">
					<dd>
						<strong>市场售价：</strong>
						<font class="market">${product.market_price}元</font>
					</dd>
				</li>
				
				<li class="clearfix">
					<dd>
						<strong>商品库存：</strong> ${product.pnum}件
					</dd>
				</li>
				<li class="clearfix">
					<dd>
						<strong>商品分类：</strong>  ${product.category.cname}
					</dd>
				</li>
				<li class="clearfix" style="width:100%;">
					<dd>
						<strong>商品描述：</strong>
						<div style="text-indent: 2em;">
						${product.pdesc}
						</div>
					</dd>
				</li>
				
				<li class="clearfix"  style="width:100%;"> 
					<dd>
						<strong>本店售价：</strong>
						<font class="shop" style="font-size:40px;font-weight:1000;"><strong>${product.shop_price}</strong></font>元
					</dd>
				</li>
			</ul><br/>
		
		</div>
	</div>
	<!-- 商品信息结束 -->
	
	<div class="blank"></div>

	<!-- <script type="text/javascript">reg("com");</script> -->
	<div class="blank"></div>
	
	<div class="blank5"></div>
	<div id="ECS_COMMENT">
		<div class="box">
		
		<!-- 评论 -->
					<div class="commentsList">
						<form action="${pageContext.request.contextPath}/OrderController?method=subComment"
							method="post" name="commentForm" id="commentForm">
							<table width="710" border="0" cellspacing="5" cellpadding="0">
								<tr>
									<td align="right">评价等级：</td>
									<td>
										<label><input name="comment_rank" type="radio" value="1"
										id="comment_rank1" /> <img
										src="themes/ecmoban_jumei/images/stars1.gif" />
										</label>
										<label>
										 <input name="comment_rank" type="radio" value="2"id="comment_rank2" />
										  <img src="themes/ecmoban_jumei/images/stars2.gif" />
										</label>
										<label>
										 <input
										name="comment_rank" type="radio" value="3"
										id="comment_rank3" /> <img
										src="themes/ecmoban_jumei/images/stars3.gif" />
										</label>
										<label>
										 <input
										name="comment_rank" type="radio" value="4"
										id="comment_rank4" /> <img
										src="themes/ecmoban_jumei/images/stars4.gif" />
										</label>
										<label>
										 <input
										name="comment_rank" type="radio" value="5" checked="checked"
										id="comment_rank5" /> <img
										src="themes/ecmoban_jumei/images/stars5.gif" />
										</label>
										</td>
								</tr>
								<tr>
									<td align="right" valign="top">评论内容：</td>
									<td><textarea name="content" class="inputBorder"
											style="height:50px; width:620px;"></textarea> <input
										type="hidden" name="cmt_type" value="0" /> <input
										type="hidden" name="id" value="139" /></td>
								</tr>
								<tr>
									<td colspan="2">
<!-- 										<div style="padding-left:15px; text-align:left; float:left;">
											验证码：<input type="text" id="captcha" class="inputBorder"
												style="width:50px; margin-left:5px;" /> <img
												src="validatecode.jsp" alt="captcha"
												onClick="this.src='validatecode.jsp?'+Math.random()"
												class="captcha"> <span id="check_code_notice"
								style="color:#FF0000"> * </span>
										</div> -->
										<input name="pid" type="hidden" value="${product.pid}">
										<input name="oid" type="hidden" value="${oid}">
										<input id="comm_sub" type="button" value="立即评论"
										class="f_r bnt_blue_1" style=" margin-right:6px;">
									</td>
								</tr>
							</table>
						</form>
					</div>
			</div>
			
			
			<div class="f_r"> </div>
		
			<div class="box_1">
				<h3>
					<span class="text">用户评论</span>(共<font class="f1">0</font>条评论)
				</h3>
				<div class="boxCenterList clearfix" style="height:1%;">
				<c:if test="${not empty comlist}" >
					<c:forEach items="${comlist}" var="com">
								<span>${com.nickname}:${com.comment }&nbsp;&nbsp;
								<c:if test="${com.level == 1}">
									<img src="themes/ecmoban_jumei/images/stars1.gif" />
								</c:if>
								<c:if test="${com.level == 2}">
									 <img src="themes/ecmoban_jumei/images/stars2.gif" />
								</c:if>
								<c:if test="${com.level == 3}">
									 <img src="themes/ecmoban_jumei/images/stars3.gif" />
								</c:if>
								<c:if test="${com.level == 4}">
									 <img src="themes/ecmoban_jumei/images/stars4.gif" />
								</c:if>
								<c:if test="${com.level == 5}">
									 <img src="themes/ecmoban_jumei/images/stars5.gif" />
								</c:if>
								</span><br/>
						</c:forEach>
				
<%-- 				<div class="commentsList">
					<table width="710" border="0" cellspacing="5" cellpadding="0">
						<c:forEach items="${comlist}" var="com">
						<tr><td width="20%">用户名:</td><td width="20%"><font>${com.nickname}</font></td><td></td></tr>
						<tr><td>评论内容</td>
						<td>${com.comment }</td></tr>
						</c:forEach>	
					</table>
				</div> --%>
				</c:if>
				<c:if test="${empty comlist}">
					<ul class="comments">
						<li>暂时还没有任何用户评论</li>
					</ul>
				</c:if>
					<div id="pagebar" class="f_r">
						<form name="selectPageForm" action="javascript:;" method="get">
							<div id="pager">
								总计 0 个记录，共 1 页。 <span> <a
									href="javascript:gotoPage(1,139,0)">第一页</a> <a
									href="javascript:;">上一页</a> <a href="javascript:;">下一页</a> <a
									href="javascript:;">最末页</a>
								</span>
							</div>
						</form>
					</div>
					<div class="blank5"></div>
				</div>
			</div>
		
		<div class="blank5"></div>
	</div>
</div>
</div>
<%@include file="inc/footer.jsp"%>
</body>
</html>