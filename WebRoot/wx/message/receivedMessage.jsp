<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>案例推理子系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
<link rel="stylesheet" href="css/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/respond.min.js"></script>

</head>
  
  <script type="text/javascript">
</script>
  
  
  
  
  <body>
  
  <div class="container-fluid"  id="mainContent"  style="border:0 solid black;margin:  0 0 0 0;">
  <div style="border:0 solid black;margin: 1 10 0 0px;">
  	<div class="btn-group" style="margin: 1 10 0 250px;">
	  <a href="wx/message/sourceParameter.jsp" target="content"><button class="btn btn-large">推理原始参数</button></a>
	  <!-- <button class="btn btn-large">推理结果参数</button> -->
	  <a href="wx/message/workMessage.jsp" target="content"><button class="btn btn-large">质量判定结果</button></a>
	  <a href="wx/message/auditMessage.jsp" target="content"><button class="btn btn-large">决策审核结果</button></a>
	</div>
</div>
</div>
  </body>
  </html>