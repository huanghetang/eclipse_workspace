<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript">
/* 	function getXhr(){
		return new XMLHttpRequest();
	}
	var xhr  = getXhr();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var xx = xhr.responseText;
			alert(xx);
		}
		
	}
	xhr.open("post","${pageContext.request.contextPath}/ajax",true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.send("name='景甜'"); */
/*	$(function(){
 		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/ajax",
			data:"name='热巴'",
			success:function(data){
				alert(data);
			}
		}); */
		
/* 		$.get(
			"${pageContext.request.contextPath}/ajax",
			"name=娜扎",
			function(msg){
				alert(msg);
			},
			"text"
		) */;
/* 		$.post(
				"${pageContext.request.contextPath}/ajax",
				"name=娜扎",
				function(msg){
					alert(msg);
				},
				"text"
			); */
	//});
</script>


<script type="text/javascript">
 $(function() {
	 
	 function get(ele,value){
			$.get(
					"${root}/ajax",
					"pid="+value,
					function(data){
						$(data).each(function(){
							var opt = '	<option value="'+this.id+'">'+this.name+'</option>';
							ele.append(opt);
						});
					},
					"json"
				);
	 }
	 
	//初始化省
	var pro = $("#province");
	get(pro,0);

/* 	var city = $("#city");
	//绑定onchange
	pro.change(function(){
		city.prop("length",1);
		area.prop("length",1);
		get(city,this.value);
	});

	//县绑定onchange事件
	var area = $("#area");
	city.change(function(){
		area.prop("length",1);
		get(area,this.value);
	}); */
	
	$("#province,#city").change(
		function(){
			$(this).nextAll().prop("length",1);
			get($(this).next(),this.value);
		}		
	
	);
	
	

	}); 



	/* $(function() {
		var pro = $("#province");
		$.get("${root}/ajax",
			"pid=0",
			function(data){
			//遍历循环数组
			$(data).each(function(){
				//当前被遍历循环到的元素，都要执行的函数
				//创建option标签
				//this :前被遍历循环到的元素
				var opt = '<option value="'+this.id+'">'+this.name+'</option>';
				//将标签添加到select标签中
				pro.append(opt);
			});
		},
		"json");
	
		var city = $("#city");
		//绑定onchange
		pro.change(function(){
			city.prop("length",1);
			area.prop("length",1);
				$.get(
					"${root}/ajax",		
					"pid="+this.value,
					function(data){
						$(data).each(function(){
							var opt = '	<option value="'+this.id+'">'+this.name+'</option>';
							city.append(opt);
						});
					},
					"json"
				);
			});
		
		//县绑定onchange事件
		var area = $("#area");
		city.change(function(){
			area.prop("length",1);
			$.get(
				"${root}/ajax",
				"pid="+this.value,
				function(data){
					$(data).each(function(){
						var opt = '	<option value="'+this.id+'">'+this.name+'</option>';
						area.append(opt);
					});
				},
				"json"
			);
			});
		
		}); */
</script>




</head>
<body>
<div>this is default.jsp</div>

<select name="province" id="province">
	<option value="">--请选择省--</option>
</select>
<select name="city" id="city">
	<option value="">--请选择市--</option>
</select>
<select name="area" id="area">
	<option value="">--请选择县--</option>
</select>
<%-- 当前在线人数为：${onlineNum }(人),<a href="${pageContext.request.contextPath}/index.jsp"></a> --%>
</body>

</html>