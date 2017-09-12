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
	<h4 id="title2">推理流程</h4>
	<img alt="" src="RBR/pic/reasoning.svg" class="img-responsive">
	<div id="words">
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基于规则推理包括勾选质量状态和推理结果两大内容。</p>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户通过勾选定性的质量问题、输入定量的质量状态，得到调整方案、推理链图及相关的规则。</p>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RBR系统对问题求解过程基本步骤为：对定量问题预处理、匹配规则、启动规则推理引擎、执行推理。</p>
	</div>
</body>
</html>
