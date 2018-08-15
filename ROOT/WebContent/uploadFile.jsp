<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传图片</title>
</head>
<body>
<h2>上传图片</h2>
<form action="submitServlet" enctype="multipart/form-data" method="post">
  <input type="text" name="username" /><br />
  <input type="file" name="myfile" /><br/>
  <input type="file" name="myfile2" /><br/>
  <input type="submit" />
</form>
</body>
</html>