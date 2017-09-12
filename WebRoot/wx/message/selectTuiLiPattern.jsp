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
  	<!-- <div class="btn-group" style="margin: 1 10 0 150px;">
	  <a href="wx/message/sourceParameter.jsp" target="content"><button class="btn btn-large">质量设备监测参数</button></a>
	  <button class="btn btn-large">推理结果参数</button>
	  <button class="btn btn-large">任务通知</button>
	</div> -->
	
	<table  style="margin-left: 250px">
  <caption><h4>推理</h4></caption>
  <tr><td>&nbsp;</td></tr>  
  <tr><td> 选择推理方式&nbsp;&nbsp; &nbsp;  </td><td>  <select class="input-xlarge">
      <option>基于案例</option>
      <option>基于规则</option>
      <option>基于模型</option></select></td></tr>
      
    <!-- <tr><td> &nbsp;&nbsp; &nbsp;  </td><td>  <select class="input-xlarge">
      <option>自动</option>
      <option>人工</option>
      </select></td></tr> -->
        <tr><td>&nbsp;</td></tr>  
       <tr><td  colspan="2">
       <a href="wx/message/tuiLiResultZiDong.jsp"class="btn btn-large btn-block btn-primary" >提交</a>
       </td></tr>  
  
  </table>
</div>
</div>
  </body>
  </html>