<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML">
<html>
<head>
<base href="<%=basePath%>">

<title>metaDataDelete</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/metadata_tree_format.css" rel="stylesheet">

<style type="text/css">
#intro {
	top: 0;
	left: 0;
	background-color: #ECF0F1;
	width: 100%;
	height: auto;
	padding-top: 10px;
	padding-bottom: 10px;
	padding-left: 24px;
	font-size: 16px;
}

body {
	margin: 0;
	padding: 0;
	font-family: Microsoft YaHei, SimHei, sans-serif;
}

#top {
	position: fixed;
	right: 25px;
	bottom: 10px;
	width: 60px;
	height: 60px;
	background-color: #ddd;
	border-radius:7px;
	filter: alpha(opacity :             80);
	opacity: 0.8;
	display: none;
}
#top .arrow{
	margin:0 auto;
	padding-top:11px;
	width:0px;
	height: 0px;
	border-right: 15px solid transparent;
	border-left: 15px solid transparent; 
	border-bottom: 15px solid #aaa;
	
}
#top .stick{
	margin:0 auto;
	padding-bottom:21px;
	width: 13px;
	border-bottom: 13px solid #ddd;
	background-color: #aaa;
}

#items a:LINK { /* 默认颜色 */
	color: black;
}

#items a:HOVER { /* 鼠标放上去的颜色 */
	/* color: blue; */
	text-decoration: none;
}

#items a:ACTIVE { /* 鼠标按住不放的颜色 */
	color: blue;
}

.undo{
	width: 76px;
	margin-left: 200px;
}
/* 模态框中的取消和确认按钮 */
.cancel{
	width:76px;
	margin-right: 5px;
}
.ok{
	width: 78px;
}

.tooltip{
	font-size: 16px;
}

</style>

<script type="text/javascript">
	window.onload = function() {
		var oUl = document.getElementById("ul1");
		var aLi = oUl.getElementsByTagName("li");

		for ( var i = 0; i < aLi.length; i++) {

			aLi[i].onmouseover = function() {
				this.style.background = "#c7e2ba";
			};
			aLi[i].onmouseout = function() {
				this.style.background = "";
			};
		}
		
		//滚页面才显示返回顶部
		$(window).scroll(function(){
     		if ($(window).scrollTop()>100){
				$("#top").fadeIn(500);
			} else {
				$("#top").fadeOut(500);
			}
		});
		//点击回到顶部
		$("#top").click(function() {
			$("body").animate({scrollTop:"0"},500);
		});
		
		//初始化tip
		$(function () {
  			$('[data-toggle="tooltip"]').tooltip();
		});
	};
</script>
</head>

<body>
    <!-- S intro -->
	<div id="intro">移动鼠标选择，点击条目，即可删除当前节点及其所有子节点<button type="button" class="btn btn-primary undo"><span></span>撤销</button></div>
    <!-- E intro -->
    <!-- S items -->
	<div id="items">
		<ul id="ul1">
			<c:forEach items="${metaDataList }" var="metaData">
				<li><a href="metaData/deleteWarning.action?id=${metaData.id }">${metaData.name }</a></li>
			</c:forEach>
		</ul>
	</div>
    <!-- E items -->
    <!-- S top --> 
	<div id="top" data-toggle="tooltip" data-placement="left" title="回到顶部">
		<a href="javascript:;">
			<div class="arrow"></div>
			<div class="stick"></div>
		</a>
	</div>
	 <!-- E top --> 

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>