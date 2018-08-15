<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品详情</title>
<%@include file="inc/common_head.jsp"%>
<script type="text/javascript" src="themes/ecmoban_jumei/js/action.js"></script>
<script type="text/javascript" src="themes/ecmoban_jumei/js/mzp-packed-me.js"></script>
<script type="text/javascript" src="themes/ecmoban_jumei/js/good_detail.js"></script>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>

<script>
	//购买数量校验
	function checkNum(value){
		var ll = parseInt(value);
		if(ll!=value){//含有字母,小数
			$("#buynum").val(1);
			return;
		}
		if(value<=0){
			$("#buynum").val(1);
		}
		if(value>50){
			$("#buynum").val(50);
		}
		/* $("#jifen").text($("#buynum").val()); */
	}
</script>

<script type="text/javascript">
	function addCart(){
		//同步请求
		location.href="ProductController?method=addCart&pid=${product.pid}&buynum="+$("#buynum").val();
	}






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
			商品详情
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
			<div class="picture" id="imglist">
				<a href="images/201410/goods_img/139_P_1413929169416.jpg" rel="zoom1" 
					rev="images/201410/goods_img/139_P_1413929169416.jpg">
					<img src="images/201410/thumb_img/139_thumb_P_1413929169547.jpg" class="onbg" />
				</a>
				<a href="images/201410/goods_img/139_P_1413929154557.jpg"
					rel="zoom1" rev="images/201410/goods_img/139_P_1413929154557.jpg">
					<img src="images/201410/thumb_img/139_thumb_P_1413929154414.jpg" class="autobg" />
				</a>
				<a href="images/201410/goods_img/139_P_1413929169239.jpg"
					rel="zoom1" rev="images/201410/goods_img/139_P_1413929169239.jpg">
					<img src="images/201410/thumb_img/139_thumb_P_1413929169012.jpg"
					class="autobg" />
				</a>
				<a href="images/201411/goods_img/139_P_1416438939021.jpg"
					rel="zoom1" rev="images/201411/goods_img/139_P_1416438939021.jpg">
					<img src="images/201411/thumb_img/139_thumb_P_1416438939077.jpg"
					class="autobg" />
				</a>
				<a href="images/201501/goods_img/139_P_1420324949546.jpg"
					rel="zoom1" rev="images/201501/goods_img/139_P_1420324949546.jpg">
					<img src="images/201501/thumb_img/139_thumb_P_1420324949384.jpg"
					class="autobg" />
				</a>
			</div>
			<script type="text/javascript">mypicBg();</script>
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
						<strong>本店售价：</strong>
						<font class="shop">${product.shop_price}元</font>
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
			</ul><br/>
			<ul class="bnt_ul">
				<li class="clearfix">
					<dd>
						<strong>购买此商品可使用：</strong><font class="f4" id="jifen">${product.shop_price*10} 积分</font>
					</dd>
				</li>
				<li class="clearfix">
					<dd>
						<strong>购买数量：</strong>
						<input name="buynum" id="buynum" size="4" value="1" onchange="checkNum(this.value)"/>
					</dd>
				</li>
				<li class="padd">
					<a href="javascript:;" onclick="addCart()">
						<img src="themes/ecmoban_jumei/images/goumai2.gif"  />
					</a>
				</li>
			</ul>
		</div>
	</div>
	<!-- 商品信息结束 -->
	
	<div class="blank"></div>
	<div class="box">
		<div style="padding:0 0px;">
			<div id="com_b" class="history clearfix">
				<h2>商品描述</h2>
				<h2 class="h2bg">商品属性</h2>
				<h2 class="h2bg">商品标签</h2>
				<h2 class="h2bg">相关商品</h2>
			</div>
		</div>
		<div class="box_1">
			<div id="com_v" class="  " style="padding:6px;"></div>
			<div id="com_h">
				<blockquote>
					<p>&nbsp;</p>
					<div class="more_pic"
						style="margin: 0px 20px; padding: 0px; overflow: hidden; text-align: center; color: rgb(102, 102, 102); font-family: Arial;">
						<img src="images/a_top.jpg" alt="秋冬黑色天鹅毛衣七分袖休闲套头毛衣蕾丝网纱半身裙蓬蓬裙套装"
							class="load_img"
							style="border: 0px; display: block; margin-left: auto; margin-right: auto; background-image: url(images/loading.gif); height: 710px; width: 640px; background-position: 50% 50%; background-repeat: no-repeat no-repeat;" />
					</div>
					<div class="more_pic"
						style="margin: 0px 20px; padding: 0px; overflow: hidden; text-align: center; color: rgb(102, 102, 102); font-family: Arial;">
						<img src="images/a_bottom.jpg"
							alt="秋冬黑色天鹅毛衣七分袖休闲套头毛衣蕾丝网纱半身裙蓬蓬裙套装" class="load_img"
							style="border: 0px; display: block; margin-left: auto; margin-right: auto; background-image: url(images/loading.gif); height: 190px; width: 640px; background-position: 50% 50%; background-repeat: no-repeat no-repeat;" />
					</div>
					<p class="pro_txt"
						style="margin: 0px 20px; padding: 0px 0px 10px; overflow: hidden; line-height: 24px; color: rgb(102, 102, 102); font-family: Arial;">&nbsp;</p>
					<div class="more_pic"
						style="margin: 0px 20px; padding: 0px; overflow: hidden; text-align: center; color: rgb(102, 102, 102); font-family: Arial;">
						<img src="images/b_top.jpg" alt="秋冬黑色天鹅毛衣七分袖休闲套头毛衣蕾丝网纱半身裙蓬蓬裙套装"
							class="load_img"
							style="border: 0px; display: block; margin-left: auto; margin-right: auto; background-image: url(images/loading.gif); height: 710px; width: 710px; background-position: 50% 50%; background-repeat: no-repeat no-repeat;" />
					</div>
					<div class="more_pic"
						style="margin: 0px 20px; padding: 0px; overflow: hidden; text-align: center; color: rgb(102, 102, 102); font-family: Arial;">
						<img src="images/b_bottom.jpg"
							alt="秋冬黑色天鹅毛衣七分袖休闲套头毛衣蕾丝网纱半身裙蓬蓬裙套装" class="load_img"
							style="border: 0px; display: block; margin-left: auto; margin-right: auto; background-image: url(images/loading.gif); height: 151px; width: 710px; background-position: 50% 50%; background-repeat: no-repeat no-repeat;" />
					</div>
					<p class="pro_txt"
						style="margin: 0px 20px; padding: 0px 0px 10px; overflow: hidden; line-height: 24px; color: rgb(102, 102, 102); font-family: Arial;">&nbsp;</p>
					<div class="more_pic"
						style="margin: 0px 20px; padding: 0px; overflow: hidden; text-align: center; color: rgb(102, 102, 102); font-family: Arial;">
						<img src="images/c_top.jpg" alt="秋冬黑色天鹅毛衣七分袖休闲套头毛衣蕾丝网纱半身裙蓬蓬裙套装"
							class="load_img"
							style="border: 0px; display: block; margin-left: auto; margin-right: auto; background-image: url(images/loading.gif); height: 710px; width: 710px; background-position: 50% 50%; background-repeat: no-repeat no-repeat;" />
					</div>
					<div class="more_pic"
						style="margin: 0px 20px; padding: 0px; overflow: hidden; text-align: center; color: rgb(102, 102, 102); font-family: Arial;">
						<img src="images/c_bottom.jpg"
							alt="秋冬黑色天鹅毛衣七分袖休闲套头毛衣蕾丝网纱半身裙蓬蓬裙套装" class="load_img"
							style="border: 0px; display: block; margin-left: auto; margin-right: auto; background-image: url(images/loading.gif); height: 167px; width: 710px; background-position: 50% 50%; background-repeat: no-repeat no-repeat;" />
					</div>
				</blockquote>
				<blockquote>
					<table class="table" width="100%" border="0" cellpadding="3"
						cellspacing="1" bgcolor="#dddddd">
						<tr>
							<th colspan="2" bgcolor="#FFFFFF">商品属性</th>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF" align="left" width="30%" class="f1">[花型]</td>
							<td bgcolor="#FFFFFF" align="left" width="70%">印花/绣花</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF" align="left" width="30%" class="f1">[袖型]</td>
							<td bgcolor="#FFFFFF" align="left" width="70%">七分袖</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF" align="left" width="30%" class="f1">[风格]</td>
							<td bgcolor="#FFFFFF" align="left" width="70%">简约</td>
						</tr>
					</table>
				</blockquote>
				<blockquote>
					<div class="box">
						<div class="box_1">
							<h3>
								<span class="text">商品标签</span>
							</h3>
							<div class="boxCenterList clearfix ie6">
								<form name="tagForm" action="javascript:;" id="tagForm">
									<p id="ECS_TAGS" style="margin-bottom:5px;"></p>
									<p>
										<input type="text" name="tag" id="tag" class="inputBg"
											size="35" /> <input type="submit" value="添 加"
											class="bnt_blue" style="border:none;" /> <input
											type="hidden" name="goods_id" value="139" />
									</p>
								</form>
							</div>
						</div>
					</div>
					<div class="blank5"></div>
				</blockquote>
				<blockquote></blockquote>
			</div>
		</div>
	</div>
	<script type="text/javascript">reg("com");</script>
	<div class="blank"></div>
		<div class="box_1">
				<h3>
					<span class="text">用户评论</span><!-- (共<font class="f1">0</font>条评论) -->
				</h3>
				<div class="boxCenterList clearfix" style="height:1%;">
				<c:if test="${not empty pageBean.data}" >
						<c:forEach items="${pageBean.data}" var="com">
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
							</span>  
							<c:if test="${not empty com.createTime}">
								<span style="float: right;">${fn:substring(com.createTime, 0, 10)}</span>
							</c:if>
							 <br/>
<!-- 								<div class="commentsList">
									<table width="710" border="0" cellspacing="5" cellpadding="0">
										<tr>
											<td width="20%"></td>
											<td></td>
											<td width="20%"><font></font></td>
										</tr>
									</table>
								</div> -->
						</c:forEach>	
				</c:if>
				<c:if test="${empty pageBean.data}">
					<ul class="comments">
						<li>暂时还没有任何用户评论</li>
					</ul>
				</c:if>
					<div id="pagebar" class="f_r">
						<form name="selectPageForm" action="javascript:;" method="get">
							<div id="pager">
								总计 ${pageBean.total}个记录，共 ${pageBean.end} 页,当前第${pageBean.pageNum}页 <span> 
								
								<a href="ProductController?method=productDetail&pid=${product.pid}&pageNum=1">第一页</a>
								<c:if test="${pageBean.pageNum >2}">
									<a href="ProductController?method=productDetail&pid=${product.pid}&pageNum=${pageBean.pageNum-1}">上一页</a> 
								</c:if>
								
								<c:if test="${pageBean.pageNum < (pageBean.end-1)}">
									<a href="ProductController?method=productDetail&pid=${product.pid}&pageNum=${pageBean.pageNum+1}">下一页</a>
								</c:if>
									<a href="ProductController?method=productDetail&pid=${product.pid}&pageNum=${pageBean.end}">最末页</a>
								</span>
							</div>
						</form>
					</div>
					<div class="blank5"></div>
				</div>
			</div>
	<div class="box">
		<div class="box_1">
			<h3>
				<span class="text">购买过此商品的人还购买过</span>
			</h3>
			<div class="boxCenterList clearfix ie6" style="padding:2px;">
				<div class="goodsItem" style="padding: 10px 2px 15px 2px;">
					<a href="javascript:;"><img
						src="images/201501/thumb_img/137_thumb_G_1420325495120.jpg"
						alt="海绵宝宝多元海藻呵护洗沐500ml" class="goodsimg" /></a><br />
					<p>
						<a href="javascript:;" title="海绵宝宝多元海藻呵护洗沐500ml">海绵宝宝多元海藻呵护洗沐500ml</a>
					</p>
					<font class="shop_s">87元</font>
				</div>
				<div class="goodsItem" style="padding: 10px 2px 15px 2px;">
					<a href="javascript:;"><img
						src="images/201501/thumb_img/134_thumb_G_1421183937155.jpg"
						alt="飘柔（Rejoice）倍瑞丝护理专研顺滑优惠装（洗发露450ML+护发素230ML）"
						class="goodsimg" /></a><br />
					<p>
						<a href="javascript:;"
							title="飘柔（Rejoice）倍瑞丝护理专研顺滑优惠装（洗发露450ML+护发素230ML）">飘柔（Rejoice）倍瑞丝护理专研顺滑优惠装（洗发露450...</a>
					</p>
					<font class="shop_s">110元</font>
				</div>
				<div class="goodsItem" style="padding: 10px 2px 15px 2px;">
					<a href="javascript:;"><img
						src="images/201501/thumb_img/140_thumb_G_1420324897667.jpg"
						alt="完美芦荟胶一对 40g*2" class="goodsimg" /></a><br />
					<p>
						<a href="javascript:;" title="完美芦荟胶一对 40g*2">完美芦荟胶一对
							40g*2</a>
					</p>
					<font class="shop_s">110元</font>
				</div>
				<div class="goodsItem" style="padding: 10px 2px 15px 2px;">
					<a href="javascript:;"><img
						src="images/201501/thumb_img/141_thumb_G_1420324798329.jpg"
						alt="兰蔻清滢柔肤水400ml" class="goodsimg" /></a><br />
					<p>
						<a href="javascript:;" title="兰蔻清滢柔肤水400ml">兰蔻清滢柔肤水400ml</a>
					</p>
					<font class="shop_s">110元</font>
				</div>
			</div>
		</div>
	</div>
	<div class="blank5"></div>
	
</div>
</div>
<%@include file="inc/footer.jsp"%>
</body>
</html>