<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<%@include file="inc/common_head.jsp"%>

<script type="text/javascript">
//手机验证通过
var phoneState = false;
var codeState = false;
$(function(){
	//验证手机号合法性
	$("#username").blur(function(){
		var code = username.value;
		if(!code){
			$("#username_notice").html("<span style='color:red'>不能为空</span>");
			phoneState = false;
			return;
		}
		var model = /^1[3|5|6|8]\d{9}$/;
		if(model.test(code)){
			$.post(
					"RegisterServlet?method=loginCheck",
					{"code":code},
					function(data){
						if(data==1){
							$("#username_notice").html("<span style='color:green'>√</span>");
							phoneState = true;
						}else{
							$("#username_notice").html("<span style='color:red'>该用户不存在</span>");
							phoneState = false;
						}
					}
			);
		}
	});

	//校验手机验证码
	$("#password").bind("change",function(){
		var code = password.value;
		if(!code){
			$("#password_notice").html("<span style='color:red'>不能为空</span>");
			codeState = false;
			return;
		}
		$.post(
				"PhoneCodeServlet?method=checkPhoneCode",
				{"code":code},
				function(data){
					alert(data);
					if(data==1){
						$("#password_notice").html("<span style='color:green'>√</span>");
						codeState = true;
					}else if(data == -1){
						$("#password_notice").html("<span style='color:red'>姿势不对</span>");
						codeState = false;
					}else{
						$("#password_notice").html("<span style='color:red'>验证码已过期</span>");
						codeState = false;
					}
				}
		);
	});
	
	
});




//获取验证码
function getPhoneCode(){
	if(!phoneState){
		$("#username_notice").html("<span style='color:red'>×</span>");
		return;
	}
	$.post(
			"PhoneCodeServlet?method=getPhoneCode",
			{"code":username.value},
			function(data){
				if(data==1){
					alert("验证码已发送");
					//倒计时
					setTimeOut();
					
				}else{
					alert("姿势不对");
				}
			}
	);
};

//设置验证码过期时间
function setTimeOut(){
	$("#aid").removeAttr("onclick");
	$("#aid").unbind();
	//加一个计时器倒计时 30秒
	var time=60;
	var interval = setInterval(function(){
		if(time-->0){
			$("#expired_time").html(time);
		}else{
			//删除计时器
			clearInterval(interval);
			//清空时间
			$("#expired_time").html("");
			//绑定事件
			$("#aid").bind("click",function(){
				getPhoneCode();
			});
		}
	}, 1000);
}


function userLogin(){
	console.log(phoneState);
	console.log(codeState);
	codeState = true;
	if(phoneState&&codeState){
		$("#formLogin").submit();
	}
	
}

</script>


</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block block1">
		<div class="blank"></div>
		<div class="usBox clearfix">
			<div class="usBox_1">
				<div class="login_tab">
					<ul>
						<li class="active">用户登录</li>
						<li onclick="location.href='register.jsp';">
							<a href="javascript:;">用户注册</a>
						</li>
					</ul>
				</div>
				<form name="formLogin" id="formLogin" action="RegisterServlet?method=userLogin" method="post">
					<table width="100%" border="0" align="left" cellpadding="3"
						cellspacing="5">
						<tr>
							<td width="25%" align="right">手机号</td>
							<td width="65%"><input name="username" id="username" type="text" size="25"class="inputBg" />
							<span id="username_notice"></span>
							</td>
						</tr>
						<tr>
							<td align="right">验证码</td>
							<td><input name="password" type="password" size="15" id="password"
								class="inputBg" /><span id="password_notice"></span>
								<a href="javascript:;" id="aid" onclick="getPhoneCode()">获取手机验证码</a>
								<span id=expired_time></span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="checkbox" value="1" name="remember"
								id="remember" /><label for="remember">记住用户名</label></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="left">
								<input type="button"  onclick="userLogin()" value="" class="us_Submit" />
							</td>
						</tr>
					</table>
				</form>
				<div class="blank"></div>
			</div>
		</div>
		<%@include file="inc/footer.jsp"%>
</body>
</html>