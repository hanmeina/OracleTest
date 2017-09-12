<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>metaDataQuery</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/metadata_tree_format.css" rel="stylesheet">

<style type="text/css">
#search {
	position: fixed;
	top: 0;
	left: 0;
	background-color: #ECF0F1;
	width: 100%;
	height: auto;
	padding-top: 10px;
	padding-bottom: 10px;
	padding-left: 24px;
}

body {
	padding-top: 50px;
	font-family: Microsoft YaHei, SimHei, sans-serif;
}

#btn2 {
	display: none;
}

#btn3 {
	display: none;
}

#span1 {
	display: none;
}

#top {
	position: fixed;
	right: 10px;
	bottom: 10px;
	width: 60px;
	height: 60px;
	background-color: #ECF0F1;
	text-align: center;
	line-height: 60px;
	font-size: small;
	filter: alpha(opacity :           70);
	opacity: 0.7;
}
</style>

<script type="text/javascript">
	window.onload = function() {
		//alert(document.body.scrollHeight);//显示整个页面的高度
		var oTxt = document.getElementById("txt1");
		var oBtn = document.getElementById("btn1");
		var oBtnNext = document.getElementById("btn2");
		var oBtnPre = document.getElementById("btn3");
		var oUl = document.getElementById("ul1");
		var aLi = oUl.getElementsByTagName("li");
		var oSpan = document.getElementById("span1");
		var oTop = document.getElementById("top");

		//点击搜索按钮
		oBtn.onclick = function() {

			if (oTxt.value != "") {//如果输入框不为空，才搜索

				oBtnNext.style.display = "none";//先隐藏下一个按钮
				oBtnPre.style.display = "none";//先隐藏上一个按钮
				oSpan.style.display = "none";//先隐藏

				var array = []; //建立一个空数组，存放i
				//遍历li
				for ( var i = 0; i < aLi.length; i++) {
					var sTxt = oTxt.value; //输入框中的字
					var sLi = aLi[i].innerText; //获取li中的文字

					aLi[i].style.background = "";//每点击一次搜索就把所有的背景去掉

					if (sLi.search(sTxt) != -1) { //若搜索到
						array.push(i); //把它推入数组中
					}
				}

				//判断array数组
				if (array.length == 0) {
					//未找到相关信息
					oSpan.style.display = "inline"; //显示第几个
					oSpan.innerHTML = "0/0       未找到相关信息";
				} else if (array.length == 1) {
					//只找到一条信息
					oSpan.style.display = "inline"; //显示第几个
					oSpan.innerHTML = "1/1";
					aLi[array[0]].style.background = "#c7e2ba"; //变高亮
					window.scrollTo(0, aLi[array[0]].offsetTop - 100);//滚屏到相应位置
				} else {
					//找到多条信息
					oBtnNext.style.display = "inline"; //显示按钮
					oBtnPre.style.display = "inline"; //显示按钮
					oSpan.style.display = "inline"; //显示第几个
					oSpan.innerHTML = "1/" + array.length;//第一个
					aLi[array[0]].style.background = "#c7e2ba"; //变高亮
					window.scrollTo(0, aLi[array[0]].offsetTop - 100);//滚屏到相应位置

					//点击下一个按钮
					var n = 0; //第n个
					oBtnNext.onclick = function() {
						if (n < array.length - 1) {
							//清空其他的高亮
							for ( var i = 0; i < array.length; i++) {
								aLi[array[i]].style.background = "";
							}

							n++;
							oSpan.innerHTML = (n + 1) + "/" + array.length;
							aLi[array[n]].style.background = "#c7e2ba"; //变高亮
							window.scrollTo(0, aLi[array[n]].offsetTop - 100);//滚屏到相应位置

						}
					};
					//点击上一个按钮
					oBtnPre.onclick = function() {
						if (n > 0) {
							//清空其他的高亮
							for ( var i = 0; i < array.length; i++) {
								aLi[array[i]].style.background = "";
							}

							n--;
							oSpan.innerHTML = (n + 1) + "/" + array.length;
							aLi[array[n]].style.background = "#c7e2ba"; //变高亮
							window.scrollTo(0, aLi[array[n]].offsetTop - 100);//滚屏到相应位置
						}
					};
				}
			}
		};

		//点击回到顶部
		oTop.onclick = function() {
			window.scrollTo(0, 0);//滚屏到相应位置			
		};
	};
</script>
</head>

<body>

	<div id="search" class="form-inline">
		<input id="txt1" type="text" class="form-control"
			style="width: 200px;">
		<input id="btn1" type="button"
			class="btn btn-primary" value="搜索"> 
	    <input id="btn3" type="button" value="上一个" class="btn btn-default"> <span
			id="span1">0/0</span> <input id="btn2" type="button" value="下一个"
			class="btn btn-default">
	</div>

	<div id="items">
		<ul id="ul1">
			<c:forEach items="${metaDataList }" var="metaData">
				<li>${metaData.name }</li>
			</c:forEach>
		</ul>
	</div>

	<div id="top">
		<a href="javascript:;">回到顶部</a>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
