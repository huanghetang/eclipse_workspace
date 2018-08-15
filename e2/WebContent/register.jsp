<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<%@include file="inc/common_head.jsp"%>



<script type="text/javascript">
	var email = false;
	var nick = false;
	var pass = false;
	var yzm = false;
	var agree = false;
	$(function(){
		//账号
		$("#username").bind("blur",function(){
			var name = username.value;
			if(!name){//不填undefined
				$("#username_notice").html("不能为空");
				email = false;
				return;
			}
			//邮箱正则表达式
			var model = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
			if(!model.test(name)){//不满足
				$("#username_notice").html("格式错误");
				email = false;
				return;
			}
			$.post(
				"RegisterServlet?method=checkName",
				{"name":name},
				function(data){
					if(data ==1){
						email =true;
						$("#username_notice").html("<font style='color:green'>√</font>");
					}else if(data ==-1){
						email = false;
						$("#username_notice").html("<font style='color:red'>用户名已占用</font>");
					}
				}
			);
		});
		//昵称
		$("#nickname").bind("blur",function(){
			var nic = nickname.value;
			if(!nic){//undefined
				nick = false;
				$("#nickname_notice").html("不能为空");
				return;
			}
			nick = true;
			$("#nickname_notice").html("<font style='color:green'>√</font>");
		});
		//密码 -- 
		$("#password1").bind("blur",function(){
			var pw = password1.value;
			//由数字和字母组成，并且要同时含有数字和字母，且长度要在8-16位之间。
			var model = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
			if(!model.test(pw)){
				$("#password_notice").html("密码不合法");
				$("#pwd_lower,#pwd_middle,#pwd_high").css({"border-bottom":"2px solid #ccc"});
				pass = false;
				return;
			}
			pass = true;
			$("#password_notice").html("<font style='color:green'>√</font>");
		});
		//密码强度
		$("#password1").bind("keyup",function(){
			var len = password1.value.length;
			if(len>=1){
				$("#pwd_lower").css({"border-bottom":"2px solid #228B22"});
			}
			if(len>=8){
				$("#pwd_middle").css({"border-bottom":"2px solid #228B22"});
			}
			if(len>=10){
				$("#pwd_high").css({"border-bottom":"2px solid #228B22"});
			}
		}); 
		//确认密码
		$("#conform_password").bind("blur",function(){
			var pass = password1.value;
			var password = conform_password.value;
			if(password!=pass){
				$("#conform_password_notice").html("姿势不对");
				return;
			}
			$("#conform_password_notice").html("<font style='color:green'>√</font>");
			
		});
		
		//验证码
		$("#captcha").bind("blur",function(){
			var codeValue = captcha.value;
			if(!codeValue){
				$("#captcha_notice").html("不能为空");
				yzm = false;
				return;
			}
			yzm = true;
			$.get(
				"RegisterServlet?method=checkyzm",		
				"yzm="+codeValue,
				function(data){
					if(data==1){
						$("#captcha_notice").html("<font style='color:green'>√</font>");
					}else{
						$("#captcha_notice").html("姿势不对");
					}
				},
				"text"
			);
			
		});
		
	});
	
	





	//获取手机验证码
	function getPhoneCode(){
		if(!phoneState){//没有通过验证
			return;
		}
		//清空事件
		$("#adisabled").removeAttr("onclick");
		$("#adisabled").unbind();
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
				$("#adisabled").bind("click",function(){
					getPhoneCode();
				});
			}
		}, 1000);
		
		//发送ajax
			$.post(
				"PhoneCodeServlet?method=getPhoneCode",		
				{"code":phone.value},
				function(data){
					if(data==1){
						alert("验证码已发送");
						$("#phoneCheckcode_notice").html(""); //清空验证码错误信息
					}else{
						alert("验证码发送失败");
					}
				}
			);
	}

	//手机验证通过
	var phoneState = false;
	$(function(){
		//验证手机号合法性
		$("#phone").blur(function(){
			var code = phone.value;
			var model = /^1[3|5|6|8]\d{9}$/;
			if(model.test(code)){
				$("#phone_notice").html("<span style='color:green'>√</span>");
				phoneState = true;
			}else{
				$("#phone_notice").html("<span style='color:red'>×</span>");
				phoneState = false;
			}
		});
		
		//检测手机验证码
		$("#phoneCheckcode").change(function(){
			var code = phoneCheckcode.value;
			if(code =='' || code.length!=4){//手机验证码简单校验
				$("#phoneCheckcode_notice").html("<span style='color:red'>格式错误</span>");
				return;
			}
			//后台校验手机验证码
			$.post(
				"PhoneCodeServlet?method=checkPhoneCode",
				{"code":code},		
				function(data){
					if(data==1){//验证成功
						$("#phoneCheckcode_notice").html("<span style='color:green'>√</span>");
					}else if(data==-2){
						$("#phoneCheckcode_notice").html("<span style='color:red'>验证码过期</span>");
					}else{
						$("#phoneCheckcode_notice").html("<span style='color:red'>填写错误</span>");
					}
				}
			);
		});
		
		
		
	});
	//表单提交校验
	function register(){
		//验证条款
		var check = $("#agreement").prop("checked");
		agree = check;
		console.log(agree);
		console.log(email);
		console.log(nick);
		console.log(pass);
		console.log(yzm);
		console.log(phoneState);
		//接受条款
		if(agree && email && nick && pass && yzm && phoneState){
			//alert(22);
			return true;
		}
		alert(33);
		return false;
	}
	
</script>


</head>
<body>
<!-- <body class="index_page" style="min-width:1200px;overflow-x:hidden;background:#fff;" > -->
<%@include file="inc/header.jsp"%>

	<div class="block block1">
		<div class="blank"></div>
		<div class="usBox">
			<div class="usBox_1">
				<div class="login_tab">
					<ul>
						<li onclick="location.href='login.jsp';">
							<a href="javascript:;">用户登录</a>
						</li>
						<li class="active">用户注册</li>
					</ul>
				</div>
				<form action="RegisterServlet?method=userRegister" method="post" name="formUser"
					onsubmit="return register();">
					<table width="100%" border="0" align="left" cellpadding="5"
						cellspacing="3">
						<tr>
							<td width="25%" align="right">邮箱</td>
							<td width="65%"><input name="email" type="text"
								id="username" class="inputBg" /> <span id="username_notice"style="color:#FF0000"> *</span></td>
						</tr>
						<tr>
							<td align="right">昵称</td>
							<td><input name="nickname" type="text"
								id="nickname" 
								class="inputBg" /> <span id="nickname_notice"
								style="color:#FF0000"> *</span></td>
						</tr>
						<tr>
							<td align="right">密码</td>
							<td><input name="password" type="password" id="password1" class="inputBg" />
								<span style="color:#FF0000"
								id="password_notice"> *</span></td>
						</tr>
						<tr>
							<td align="right">密码强度</td>
							<td>
								<table width="145" border="0" cellspacing="0" cellpadding="1">
									<tr align="center">
										<td width="33%" style="border-bottom:2px solid #ccc;" id="pwd_lower">弱</td>
										<td width="33%" style="border-bottom:2px solid #ccc;" id="pwd_middle">中</td>
										<td width="33%" style="border-bottom:2px solid #ccc;" id="pwd_high">强</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="right">确认密码</td>
							<td><input name="confirm_password" type="password" 
								id="conform_password"
								 class="inputBg" />
								<span style="color:#FF0000"
								id="conform_password_notice"> *</span></td>
						</tr>
						<!-- 手机号 -->
						<tr>
							<td align="right">手机号</td>
							<td><input name="phone" type="text" id="phone" class="inputBg" />
								<span style="color:#FF0000 "id="phone_notice">*</span>
							</td>
						</tr>
						<tr>
							<td align="right">手机验证码</td>
							<td><input type="text" size="8" name="phoneCheckcode" id="phoneCheckcode"class="inputBg" />
								 <span style="color:#FF0000"id="phoneCheckcode_notice"> *</span>
								<a href="javascript:;" onclick="getPhoneCode()" id="adisabled">获取验证码</a>
								<span id="expired_time"></span>
								</td>
						</tr>
						
						<tr>
							<td align="right">验证码</td>
							<td><input type="text" size="8" name="checkcode" id="captcha"
								class="inputBg" /> <span style="color:#FF0000"
								id="captcha_notice"> *</span></td>
								
						</tr>
						<tr>
							<td align="right"></td>
							<td><img src="validatecode.jsp"
								style="vertical-align:middle;cursor:pointer;width:130px;height:35px;margin-top:-2px;"
								onClick="src='validatecode.jsp?'+Math.random()" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><label> <input name="agreement" type="checkbox" id="agreement"
									value="1" checked="checked" /> 我已看过并接受《<a
									href="javascript:;" style="color:blue" target="_blank">用户协议</a>》
							</label><span>${pageContext.session},${code },${sessionScope.code }</span></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td align="left">
								<input type="submit" value="" class="us_Submit_reg">
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
					</table>
				</form>
				<div class="blank"></div>
			</div>
		</div>
	</div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>