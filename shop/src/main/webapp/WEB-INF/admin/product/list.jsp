<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/admin/product/toAdd?pageNum="+${pageBean.pageNum};
			}
			
			//批量删除
			function batchDel(){
				var b = confirm("你确定要删除吗?");
				if(b){
				var ids = "";//","拼接所有id,必须初始化"",不然就是拼接的时候就是undefined
				$("#selectId:checked").each(function(index,ele){
					if(index==0){
						ids +=$(ele).val();
					}else{
						ids +=","+$(ele).val();
					}
				}); 
				alert("选中的ids"+ids);
				$.ajax({
					url:"${pageContext.request.contextPath}/admin/product/banchDel",
					type: "post",
					data:"pageNum=${pageBean.pageNum}&pids="+ids,
					dataType:"json",//预期返回值类型,(如果后台返回json,这里不写也是json)
					success: function(msg){
						//拿到了pageBean对象,但是不知道怎么重新加载数据
					     alert(msg.total+"已经拿到了pageBean对象,但是不知道怎么重新加载数据,location.href吧");
					     window.location.href = "${pageContext.request.contextPath}/admin/product/list?pageNum="+${pageBean.pageNum};
					   }
				});
				alert(111);
				}
			}
			
		</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
					</TD>
				</tr>
				<tr>
		<!-- 			<td class="ta_01" align="left">
						

					</td> -->
					<td class="ta_01" align="right">
					<button type="button" id="add" name="add" value="删除"
							class="button_del" onclick="batchDel()">
							删除</button>
					
					
						<button type="button" id="add" name="add" value="添加"
							class="button_add" onclick="addProduct()">
							&#28155;&#21152;</button>

					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="5%"><input type="checkbox" name="selectAllId"  onclick="selectAll()"></td>
								<td align="center" width="5%">序号</td>
								<td align="center" width="20%">商品图片</td>
								<td align="center" width="20%">商品名称</td>
								<td align="center" width="20%">商品价格</td>
								<td align="center" width="20%">是否热门</td>
								<td width="5%" align="center">编辑</td>
								<td width="5%" align="center">删除</td>
							</tr>
							<c:forEach items="${pageBean.data}" var="product">
							<tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<!-- 全选 checkbox -->
								<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="5%">
									<input type="checkbox" name="selectId" value="${product.pid}" id="selectId">
								</td>
								
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="5%">${product.pid}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="20%"><img width="40" height="45" src="${root}/${product.pimage}"></td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="20%">${product.pname}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="20%">${product.shopPrice}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"width="20%">
									<c:if test="${ product.isHot==1 }">是</c:if><c:if test="${ product.isHot==0 }">不是</c:if>
								</td>
								<td align="center" style="HEIGHT: 22px">
								<a href="${root}/admin/product/toEdit?pageNum=${pageBean.pageNum}&pid=${product.pid}">
										<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
								</a>
								</td>

								<td  align="center" style="HEIGHT: 22px">
								<a href="${root}/admin/product/deleteById?pageNum=${pageBean.pageNum}&pid=${product.pid}">
								 <img src="${pageContext.request.contextPath}/images/i_del.gif"
								 	width="16" height="16" border="0" style="CURSOR: hand">
								</a></td>
							</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<!-- 显示 分页条-->
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
					<c:forEach items="${pageBean.bar}" var="currentPageNumber">
						<a href="${pageContext.request.contextPath }/admin/product/list?pageNum=${currentPageNumber}">
							<span <c:if test="${pageBean.pageNum == currentPageNumber }">style="color: red;"</c:if>><u><i>${currentPageNumber}&nbsp;&nbsp;</i></u></span>
							
						</a>
					</c:forEach>
					</td>
				</tr>
			
			</TBODY>
		</table>
	</form>
</body>
</HTML>

