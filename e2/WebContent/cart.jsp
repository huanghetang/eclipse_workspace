<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的购物车</title>
<%@include file="inc/common_head.jsp"%>

<script type="text/javascript">
	//异步删除购物车商品
	function deleteCartById(uid,pid){
		console.log(uid);
		console.log(pid);
		//先删除商品,再局部刷新购物车
/* 		var flag = confirm("确认删除?");
		if(!flag){
			return;
		} */
		$.post(
			"${pageContext.request.contextPath }/CartController?method=deleteCartById",
			{"uid":uid,"pid":pid},
			function(data){
				if(data==1){//删除成功
					//重新请求服务端查询购物车,拿到数据后局部刷新页面
					reloadTable(pid);
				}else{
					alert("删除失败");
				}
			}
		);
	}
	
	function reloadTable(pid){
		//删除当前行
		$("#tr"+pid).remove();
	}
	
	
	
	//选中时给当前行的buynum加上   取消选中时,取消readonly属性
	function changeReadWrite(){
		$(":checkbox[id^='check']").each(function(i){
			var che = $(this).prop("checked");
			//alert(che);
			if(che){
				 $('#buynum'+this.value).prop("readonly",true);
				 //选中结算时不能删除
				 $('#delete'+this.value).removeAttr("onclick");
				$('#delete'+this.value).unbind();
			}else{
				 $('#buynum'+this.value).removeProp("readonly");
				 //不选中时添加点击删除事件
				 var uid =$("#uid"+this.value).val();
				 var pid = $(this).val();
				 $('#delete'+this.value).bind("click",function(){
					  deleteCartById(uid,pid);
				  });
			}
/* 			var buynum = $('#buynum'+this.value).prop("readonly");
			 $('#buynum'+this.value).prop("readonly",!buynum); */
		});
	}
	
	//计算总金额
	function sumPrice(){
		var sum = 0;
		//$(":checkbox:checked").each(function(i){
		$(":checkbox[id^='check']:checked").each(function(i){
 			//从0开始数,找到每行小记
 			//var thisprice = $(this).parent().parent().find('td').eq(6).text();
 			//var thisprice = $(this).parent().parent().find('#count'+this.value).text();
 			var thisprice = $('#count'+this.value).text();
			sum += parseInt(thisprice); 
		});
		//alert(sum);
		return sum;
	}
	
	//计算便宜的金额
	function reducedPrice(){
		var sum = 0;
		//$(":checkbox:checked").each(function(){
		$(":checkbox[id^='check']:checked").each(function(i){
			//找到每一行市场价
			var market = $(this).parent().parent().find('#market'+this.value).text();
			//找到每一行本店价格
			var shop = $(this).parent().parent().find('#shop'+this.value).text();//this.value是当前行id
			//找到每一行的购买数量
			var buynum = $('#buynum'+this.value).val();
			//找到每一个商品的差价
			var reduce1 = parseFloat(market) - parseFloat(shop);
			//找到每一行商品总量的差价
			var reduce = parseFloat(reduce1)*parseFloat(buynum);
			//对差价求和
			 sum += parseFloat(reduce);
		});
		//alert(sum);
		return sum;
		
		}

	
	//校验购买数量
	function checkNum(value,element){
		var ll = parseInt(value);
		if(ll!=value){//含有字母,小数
			$(element).val(1);
		}
		if(value<=0){
			$(element).val(1);
		}
		if(value>50){
			$(element).val(50);
		}
		return $(element).val();
		
	}
	
	
	//提交订单
	function submitPreOrder(){
		//找到所有选中的pid
		var pid="";
		$(":checkbox[id^='check']:checked").each(function(i){
				if(i==0){
					pid += this.value;					
				}else{
					pid +=","+this.value;
				}
		});
		//没有选中商品不能提交订单详情
		if(pid==""){
			return;
		}
		//提交请求
		location.href="${path}/OrderController?method=order2Submit&pid="+pid;
	}
	
	//批量删除
	function deleteCarts(){
		if(!confirm("确认删除?")){
			return;
		}
		//找到所有选中的pid
		var pid="";
		$(":checkbox[id^='check']:checked").each(function(i){
				if(i==0){
					pid += this.value;					
				}else{
					pid +=","+this.value;
				}
				//删除选中的行
				reloadTable(this.value);
		});
		//没有选中商品不能提交订单详情
		if(pid==""){
			return;
		}
		//删除
		$.post(
				"${pageContext.request.contextPath }/CartController?method=deleteCartByIds",
				{"pid":pid},
				function(data){
					if(data==1){//删除成功
						console.log("删除成功");
						 changeReadWrite();
							//计算所有行的结果,
							var sum = sumPrice();
							var reduced = reducedPrice();
							//赋值
							$("#sumPrice").html(sum);
							$("#reducedPrice").text(reduced);
					}else{
						alert("删除失败");
					}
				}
			);
	}
	
	
	function editPnum(checkedNum,pid){
		$.post(
			"CartController?method=editPnumByPid",
			{"pnum":checkedNum,"pid":pid},
			function(data){
				if(data==1){
					conlose.log("商品数量修改为"+checkedNum);
				}else{
					conlose.log("商品数量修改异常");
				}
			}
		);
	}
</script>


<script>
//页面加载时给checkbox绑定一个点击事件,遍历所有选中的checkbox,给下面的sumPrice和reducedPrice赋值
	$(function(){
		$(":checkbox[id^='check']").each(function(i){
			/* alert(i+this.value); */
			$(this).bind("click",function(){
				//选中时把购买数量readonly,取消时把readonly取消
				changeReadWrite();
				//点击取消时,取消father的选中
				var f = $(this).prop("checked");
				if(!f){
					$("#selectAll").prop("checked",false);
				}
				//计算所有行的结果,
				var sum = sumPrice();
				var reduced = reducedPrice();
				//赋值
				$("#sumPrice").html(sum);
				$("#reducedPrice").text(reduced);
			});
		});
		
		//页面加载后给购买数量加一个change事件当改变数量后重新计算每行小记
		$("input[id^='buynum']").each(function(i){
			$(this).bind("change",function(){
				//buynum获取值
				var num = this.value;
				//购买数量过滤
				var checkedNum = checkNum(num,this);
				//提交ajax请求,修改cart表中购买数量
				var pidStr = this.id;
				var pid = pidStr.substr(6);
				editPnum(checkedNum,pid);
				//alert(checkedNum);
				//获取单价
				var unitPrict =  $(this).parent().parent().find('td').eq(3).text();
				//计算总价
				var count = parseFloat(checkedNum)*parseFloat(unitPrict);
				//给小记赋值
				$(this).parent().parent().find('td').eq(5).text(count+".0元")
			});
		});
		
		//全选或全部取消
		$("#selectAll").bind("click",function(){
			//遍历checkbox,让所有checkbox的选中状态和它一样,
			var father = $("#selectAll").prop("checked");
			 $(":checkbox[id^='check']").each(function(i){
				 $(this).prop("checked",father);	
			 });
			 //若是选中就触发每个checkbox的点击事件
			 $(":checkbox[id^='check']:checked").each(function(i){
					 changeReadWrite();
					//计算所有行的结果,
					var sum = sumPrice();
					var reduced = reducedPrice();
					//赋值
					$("#sumPrice").html(sum);
					$("#reducedPrice").text(reduced);
			 });
			 //取消全选时清空金额
				if(!father){
					//赋值
					changeReadWrite()
					$("#sumPrice").html(0);
					$("#reducedPrice").text(0);
				}
		});
		
		
		
	});
	
	
</script>



</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block table">
		<div class="AreaR">
			<div class="block box">
				<div class="blank"></div>
				<div id="ur_here">
					当前位置: <a href="index.jsp">首页</a><code>&gt;</code>我的购物车
				</div>
			</div>
			<div class="blank"></div>
			<div class="box">
				<div class="box_1">
					<div class="userCenterBox boxCenterList clearfix"
						style="_height:1%;">
						<h5><span>我的购物车</span></h5>
						<table width="100%" align="center" border="0" cellpadding="5"
							cellspacing="1" bgcolor="#dddddd">
							<tr>
								<th bgcolor="#ffffff">
								<input type="checkbox" id="selectAll">
								</th>
								<th bgcolor="#ffffff">商品名称</th>
								<th bgcolor="#ffffff">市场价</th>
								<th bgcolor="#ffffff">本店价</th>
								<th bgcolor="#ffffff">购买数量</th>
								<th bgcolor="#ffffff">小计</th>
								<th bgcolor="#ffffff" width="160px">操作</th>
							</tr>
							<c:if test="${empty list}">
								<span>购物车是空的</span>
							</c:if>
							<c:if test="${not empty list }">
							<c:forEach items="${list }" var="cm">
							<tr id="tr${cm.pid}">
								<td align="center" bgcolor="#ffffff">
								<input type="checkbox" id="check${cm.pid}" value="${cm.pid}">
								<input type="hidden" id="uid${cm.pid}" value="${cm.uid}">
								</td>
								<td bgcolor="#ffffff" align="center" style="width:300px;">
									<!-- 商品图片 -->
									<a href="javascript:;" target="_blank">
										<img style="width:80px; height:80px;"
										src="${cm.pimage }"
										border="0" title="${cm.pname }" />
									</a><br />
									<!-- 商品名称 -->
									<a href="javascript:;" target="_blank" class="f6">${cm.pname }</a>
								</td>
								<td align="center" bgcolor="#ffffff" id="market${cm.pid}">${cm.market_price }元</td>
								<td align="center" bgcolor="#ffffff" id="shop${cm.pid}">${cm.shop_price }元</td>
								<td align="center" bgcolor="#ffffff">
									<input value="${cm.buynum }" id="buynum${cm.pid}"  size="4" class="inputBg" style="text-align:center;" />
								</td>
								<td align="center" bgcolor="#ffffff" id="count${cm.pid}">${cm.countPrice}元</td>
								<td align="center" bgcolor="#ffffff">
									<a href="javascript:;" onclick="deleteCartById(${cm.uid},${cm.pid})" class="f6" id="delete${cm.pid}">删除</a>
								</td>
							</tr>
						</c:forEach>
						</c:if>
							<tr>
								<td colspan="6" style="text-align:right;padding-right:10px;font-size:25px;">
									购物金额小计&nbsp;<font color="red" id="sumPrice">00.00</font>元，
									共为您节省了&nbsp;<font color="red" id="reducedPrice">00.00</font>元
									<a href="javascript:;" onclick="submitPreOrder()"><input value="去结算" type="button" class="btn" /></a>
								</td>
								<td style="padding-right:10px;font-size:25px;" align="center">
								<a href="javascript:;" onclick="deleteCarts()"><input value="删除" type="button" class="btn" /></a>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="blank"></div>
		<div class="blank5"></div>
	</div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>
