<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function changeCode(){
		var img = document.getElementById("img");
		img.src = "/checkcode?tim="+new Date().getTime();
	}

</script>
</head>
<body>
<form action="/session">
<table>
	<tr>
	<td>用户名</td>
	<td><input type="text" name="username" /></td>
	</tr>
	
	<tr>
	<td>密码</td>
	<td><input type="text" name="password" /></td>
	</tr>
	
	<tr>
	<td>验证码</td>
	<td><input type="text" name="code" /></td>
	</tr>
	<tr>
	<td></td>
	<td><img id="img" alt="..." src="/checkcode" onclick="changeCode()"><span>${msg}</span></td>
	</tr>
	<tr><td></td><td><input type="submit"/></td></tr>
</table>
</form>




</body>
</html>