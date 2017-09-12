<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'iframe.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="css/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/respond.min.js"></script>

  </head>

  
  <body>
  <div></br>
	
	&nbsp;&nbsp;连铸生产质量保证主控系统主要包括的功能有：</br></br>
	&nbsp;&nbsp;1. 提供进入
	<a href="/CBR/selectline.jsp" style="text-decoration: none;" onclick="productlinetab()" target="content" >
	基于案例推理</a>、
	<a href="RBR_Home.jsp" style="text-decoration: none;" onclick="productlinetab()" target="content" >
	基于规则推理</a>和
	<a href="/CBR/reviewcase.jsp" style="text-decoration: none;" onclick="productlinetab()" target="content" >
	基于模型推理</a>
	的操作入口。</br></br>
	&nbsp;&nbsp;2. 用户权限管理：主要包括用户、角色的增删改查和授权，以及权限的增删改查。</br></br>
	&nbsp;&nbsp;3. 多系统协作管理：用于定义与决策系统、质量判定系统、设备运行状态监测系统三大推理系统的交互的消息格式。</br></br>
	&nbsp;&nbsp;4. 公告与消息：包括公告管理和消息管理，其中，公告管理用于对所有人都能看到的消息进行维护。消息管理针对的消息来源包括：
	与决策系统、质量判定系统、设备运行状态监测系统三大推理系统的交互消息。</br></br>
	&nbsp;&nbsp;5. 日志与审计：包括日志管理和审计管理，其中，日志管理用于维护系统操作信息。审计管理主要对日志数据进行分析，得到操作链。</br></br>
	</div>
	
	
  </body>
</html>
