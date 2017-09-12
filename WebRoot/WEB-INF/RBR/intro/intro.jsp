<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'intro.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
* {
	font-family: Microsoft YaHei, SimHei, sans-serif;
}

#title2 {
	font-weight: bold;
	margin-left: 10px;
	font-family: Microsoft YaHei, SimHei, sans-serif;
}

#words {
	padding: 5px 10px 5px 10px;
}
</style>

<link href="css/bootstrap.min.css" rel="stylesheet">

</head>

<body>
	<h4 id="title2">系统流程</h4>
	<img alt="" src="RBR/pic/RBR.svg" class="img-responsive">
	<div id="words">
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基于规则的推理（RBR）是一种基于经验知识进行推理的人工智能技术，它是用规则来表达知识，质量缺陷状态启动规则引擎，从而获得当前问题的解决方法。</p>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们的规则库内容包括连铸生产过程中出现的质量波动描述和专家多年的解决方案的经验积累，提供最专业的解决方案。</p>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;典型的RBR问题求解过程基本步骤为：勾选质量缺陷、启动规则引擎、调用规则库、推理出结果。</p>
	</div>
</body>
</html>
