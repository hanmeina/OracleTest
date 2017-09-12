<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>决策系统</title>
    
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
  
  <body></br>
&nbsp;&nbsp;1. 案例决策操作指南：</br></br>
&nbsp;&nbsp;（1）点击
<a href="/CBR/Manage_templet.jsp" style="text-decoration: none;" onclick="productlinetab()" target="content" >
案例决策</a>
菜单，右侧操作区显示待审核的案例参数。</br></br>
&nbsp;&nbsp;（2）点击某一条案例记录后的“查看”按钮，显示案例参数详情。</br></br>
&nbsp;&nbsp;（3）根据情况在案例参数详情页面点击“通过”或“未通过”的按钮，完成审核。</br></br>
&nbsp;&nbsp;2. 推理决策操作指南：</br></br>
&nbsp;&nbsp;（1）点击
<a href="/CBR/Manage_templet.jsp" style="text-decoration: none;" onclick="productlinetab()" target="content" >
推理决策</a>
菜单，右侧操作区显示待审核的推理结果。</br></br>
&nbsp;&nbsp;（2）点击某一条案例记录后的“查看”按钮，显示推理结果详情。</br></br>
&nbsp;&nbsp;（3）根据情况在推理结果详情页面点击“通过”或“未通过”的按钮，完成审核。</br></br>
&nbsp;&nbsp;3. 业务流程图如下：</br>
<img alt="" src="img/indexMain/juece.jpg" style="margin:50 0 0 30">
	
	</div>				



					
					
										
  </body>
</html>
