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
	<h4 id="title2">规则管理</h4>
	<img alt="" src="RBR/pic/rules.svg" class="img-responsive">
	<div id="words">
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RBR子系统主要针对产生式表现形式的知识。</p>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;产生式规则通常用于表示事物间的启发式关联，其基本形式为：</p>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;P => Q</p>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;或者：</p>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IF P then Q</p>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;P为规则激活使用的条件（或称前提）；Q则指示规则激活时（即规则条件部分满足时）应该执行的动作（或应该得出的结论）。</p>

	</div>
</body>
</html>
