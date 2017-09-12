<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'searchcase.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  <link rel="stylesheet" href="css/bootstrap3.css" rel="stylesheet"></link>
</head>
  <script type="text/javascript">
   
</script> 
  <body>
  
  
	<div>
	<center>修改用户</center><br><br>
	<form class="form-horizontal" role="form">
  <div class="form-group">
    <label class="col-sm-2 control-label">用户名：</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="exampleInputEmail1" value="a">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword" class="col-sm-2 control-label">新密码：</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPassword" >
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword" class="col-sm-2 control-label">再次输入新密码：</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPassword" >
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">描述：</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="exampleInputEmail1" value="345565">
    </div>
  </div>
  <center><button type="submit" class="btn btn-default">保存</button></center>
</form>
	
	
	
	
 	


	
</div>
<script type="text/javascript" src="js/bootstrap-typeahead.js"></script>
</body>
</html>
