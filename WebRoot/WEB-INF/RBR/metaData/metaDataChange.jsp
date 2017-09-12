<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>metaDataChange</title>

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
	right: 10px;
	bottom: 10px;
	width: 60px;
	height: 60px;
	background-color: #ECF0F1;
	text-align: center;
	line-height: 60px;
	font-size: small;
	filter: alpha(opacity :                     70);
	opacity: 0.7;
}
/* S items*/
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

/*E items*/

/* 模态框中的取消和确认按钮 */
.cancel{
	width:76px;
	margin-right: 5px;
}
.ok{
	width: 78px;
}
</style>

<script type="text/javascript">
	window.onload = function() {
		var oUl = document.getElementById("ul1");
		var aLi = oUl.getElementsByTagName("li");
		var oTop = document.getElementById("top");
		var oTxtId = document.getElementById("id");
		var oTxtInput = document.getElementById("inputText");

		for ( var i = 0; i < aLi.length; i++) {

			aLi[i].onmouseover = function() {
				this.style.background = "#c7e2ba";
			};
			aLi[i].onmouseout = function() {
				this.style.background = "";
			};

			//鼠标点击
			/* aLi[i].onclick = function() {

				//获取inputText
				oTxtInput.value = this.innerText;

				//获取id
				//tempString的格式如下
				//<span>7</span><a href="#" data-toggle="modal" data-target="#myModal"><h3>板坯规格</h3></a> 
				var tempString = this.innerHTML; //这一行的内容，包括隐藏的span标签
				var pattern = /<span>([0-9]*)/; //匹配例
				var matches = pattern.exec(tempString);//匹配
				var id = matches[1]; //得到pid
				oTxtId.value = id; //赋给隐藏的input
			}; */
		}

		//点击回到顶部
		oTop.onclick = function() {
			window.scrollTo(0, 0);//滚屏到相应位置			
		};
	};
</script>
</head>

<body>

	<!-- <div id="intro">移动鼠标选择，点击条目，可在弹出的对话框中进行修改</div> -->
	
	<div id="intro">移动鼠标选择，点击条目，进行修改</div>
     <!-- S items -->
	<div id="items">
		<ul id="ul1">
			<c:forEach items="${metaDataList }" var="metaData">
				<li><a href="metaData/changeCheck.action?id=${metaData.id }">${metaData.name }</a></li>
			</c:forEach>
		</ul>
	</div>
     <!-- E items -->
	<div id="top">
		<a href="javascript:;">回到顶部</a>
	</div>

	<!-- Modal -->
	<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">在下面文本框中进行修改</h4>
					</div>
					<div class="modal-body">
						<input id="inputText" type="text" name="inputText"
							class="form-control" required="required"
							onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;">
						<input id="id" type="hidden" name="id" value="">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary ok">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div> -->


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
