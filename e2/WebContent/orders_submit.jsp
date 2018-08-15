<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="Generator" content="ECSHOP v2.7.3" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<title>提交订单</title>
<%@include file="inc/common_head.jsp"%>


<script>
/* 	$(function() {
		//获取省市县数据并追加到select下
		function get(ele, value) {
			$.get("${path}/OrderController?method=provinceBrother", "pid=" + value, function(data) {
				$(data).each(
						function() {
							var opt = '	<option value="'+this.id+'">'
									+ this.name + '</option>';
							ele.append(opt);
						});
			}, "json");
		}
		//获取jquery对象
		var pro = $("#province");
		//初始化省
		get(pro, 0);
		//给市和县绑定change事件
		$("#province,#city").change(function() {
			//当前下拉选改变时,清空后面所有同级别的select标签内容,保留一个初值
			$(this).nextAll().prop("length", 1);
			//给下一级select标签赋值
			get($(this).next(), this.value);
		});
	}); */
</script>





</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block clearfix"><div class="AreaR">
	<div class="block box"><div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="index.jsp">首页</a><code>&gt;</code>购物流程
		</div>
	</div><div class="blank"></div><div class="box"><div class="box_1">
	<div class="userCenterBox boxCenterList clearfix" style="_height:1%;">
	<form action="OrderController?method=saveOrder" method="post">
		<!---------收货人信息开始---------->
		<h5><span>收货人信息</span></h5>
		<table width="100%" align="center" border="0" cellpadding="5"
			cellspacing="1" bgcolor="#dddddd">
			<tr>
				<td bgcolor="#ffffff" align="right" width="120px">区域信息：</td>
				<td bgcolor="#ffffff">
<!-- 					省
					<select id="province">
						<option value="">-- 请选择省 --</option>
					</select>&nbsp;&nbsp;&nbsp;
					市
					<select id="city">
						<option value="">-- 请选择市 --</option>
					</select>&nbsp;&nbsp;&nbsp;
					县(区)
					<select id="district">
						<option value="">-- 请选择县(区) --</option>
					</select> -->
					<div style="position: relative;" ><!-- container -->
 						<input name="pcd" type="text" data-toggle="city-picker" size="70">
					</div>
				</td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" align="right">详细地址：</td>
				<td bgcolor="#ffffff">
					<input style="width:347px;" name="detailAddress"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" align="right">邮政编码：</td>
				<td bgcolor="#ffffff"><input  name="mailCode"/></td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" align="right">收货人姓名：</td>
				<td bgcolor="#ffffff"><input name="reviceNname" name=/></td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" align="right">联系电话：</td>
				<td bgcolor="#ffffff"><input name="telephone"/></td>
			</tr>
		</table>
		<!---------收货人信息结束---------->
		
		<!----------商品列表开始----------->
		<div class="blank"></div>
		<h5><span>商品列表</span></h5>
		<table width="100%" border="0" cellpadding="5" cellspacing="1"
			bgcolor="#dddddd">
			<tr>
				<th width="30%" align="center">商品名称</th>
				<th width="22%" align="center">市场价格</th>
				<th width="22%" align="center">商品价格</th>
				<th width="15%" align="center">购买数量</th>
				<th align="center">小计</th>
			</tr>
			
			<c:if test="${not empty cartList}">
			<c:set var="total" value="0"></c:set>
			<c:forEach items="${cartList }" var="cm">
			<c:set var="total" value="${total+cm.shop_price*cm.buynum }"></c:set>
			<tr>
				<td>
					<a href="javascript:;" class="f6">${cm.pname}</a>
				</td>
				<td>${cm.market_price }元</td>
				<td>${cm.shop_price }元</td>
				<td align="center">${cm.buynum }</td>
				<td>${cm.countPrice}元</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="5" style="text-align:right;padding-right:10px;font-size:25px;">
					商品总价&nbsp;<font color="red">&yen;${total}</font>元
					<input type="submit" value="提交订单" class="btn" />
				</td>
			</tr>
			<input type="hidden" name="pid" value="${pid}">
			<input type="hidden" name="totalPrice" value="${total}">
			</c:if>
		</table>
		<!----------商品列表结束----------->
	</form>
	</div></div></div></div></div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>