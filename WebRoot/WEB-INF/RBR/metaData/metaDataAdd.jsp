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

<title>metaDataAdd</title>

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
	filter: alpha(opacity :           70);
	opacity: 0.7;
}

#items>ul>li>span {
	display: none;
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

/* 模态框中的取消和确认按钮 */
.cancel {
	width: 76px;
	margin-right: 5px;
}

.ok {
	width: 78px;
}
/* 模态框背景 */
.modal-backdrop {
	background-color: #E6E6FA;
}

.modal-backdrop.in {
	filter: alpha(opacity =   50);
	opacity: .5;
}

textarea{/* 禁止文本域被拖动 */
	resize: none;
}
</style>
<script type="text/javascript">
	window.onload = function() {
		var oUl = document.getElementById("ul1");
		var aLi = oUl.getElementsByTagName("li");
		var oTop = document.getElementById("top");
		var oTxtPid = document.getElementById("pid");//表单隐藏域
		var oScrollY = document.getElementById("scrollY");
		var sScrollY = "${scrollY}";		
		var oSpan = document.getElementById("span");

		window.scrollTo(0, sScrollY);

		for ( var i = 0; i < aLi.length; i++) {

			aLi[i].onmouseover = function() {
				this.style.background = "#c7e2ba";
			};
			aLi[i].onmouseout = function() {
				this.style.background = "";
			};

			//鼠标点击
			aLi[i].onclick = function() {
				//this.style.background = "#c7e2ba";
				//获取id
				var tempString = this.innerHTML; //这一行的内容，包括隐藏的span标签
               // alert(tempString);
				var pattern = /<span>([0-9]*)/; //匹配例
				
				var matches = pattern.exec(tempString);//匹配
				var pid = matches[1]; //得到pid
				oTxtPid.value = pid; //赋给隐藏的input

				//将当前节点内容添加到span中
				oSpan.innerHTML = this.innerText;

				oScrollY.value = window.scrollY;//将当前滚动条的位置传到后台
				//alert(oScrollY.value);
			};
		}

		//点击回到顶部
		oTop.onclick = function() {
			window.scrollTo(0, 0);//滚屏到相应位置			
		};
	};
</script>
</head>
<body>
	<div id="intro">移动鼠标选择，点击条目，即可在该节点增加新的条目</div>

	<div id="items">
		<ul id="ul1">
			<c:forEach items="${metaDataList }" var="metaData">
				<li><span >${metaData.id }</span><a href="#" data-toggle="modal"
					data-target="#myModal">${metaData.name }</a>
				</li>
			</c:forEach>
			
		</ul>
		
	</div>

	<div id="top">
		<a href="javascript:;">回到顶部</a>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form role="form" action="metaData/addMetaData.action" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							在【 <span id="span"></span> 】下添加子节点
						</h4>
					</div>
					<div class="modal-body">
						<!-- 下面的文本输入框已禁止空格输入，要求有内容 -->
						<div class="form-group">
							<label for="metaDataInput">元数据内容</label>
							<!-- <input type="text" id="metaDataInput" class="form-control" name="inputText"
							required="required"
							onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"
							placeholder="请输入元数据内容"> -->
							<textarea class="form-control" rows="4" id="metaDataInput" name="inputText" required="required" placeholder="请输入元数据内容" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"></textarea>
						</div>
						
						<div class="form-group">
							<label for="reliabilityInput">元数据可信度</label>
							 <input type="number" id="reliabilityInput"
							class="form-control" name="reliability" min="1" max="100" required="required" value="100"
							placeholder="请输入可信度（1~100的数字）">
						</div>

						<input id="pid" type="hidden"
							name="pid" value="">
						<input id="scrollY" type="hidden"
							name="scrollY" value="">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default cancel"
							data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary ok">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
