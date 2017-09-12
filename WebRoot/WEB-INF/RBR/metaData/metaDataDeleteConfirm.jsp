<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>
<head>
<base href="<%=basePath%>">

<title>metaDataDeleteConfirm</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/metadata_tree_format.css" rel="stylesheet">
<style type="text/css">
body {
	margin: 0 20px 0 25px;
	padding: 0;
	font-family: Microsoft YaHei, SimHei, sans-serif;
}

.p1 {
	font-size: 16px;
}

#items {
	padding: 0px;
	margin: 0px;
}

#items ul {
	padding: 0;
	margin: 0;
	border: 1px dashed silver;
}
.glyphicon-warning-sign{
	color: #d9534f;
}
.confirm{
	width: 100px;
	margin-left: 10px;
}
/* 模态框中的取消和确认按钮 */
.cancel{
	width:76px;
	margin-right: 4px;
}
.ok{
	width: 78px;
	margin-right: 10px;
}
/* 模态框背景 */
.modal-backdrop {
	background-color: #E6E6FA;
}
.modal-backdrop.in {
	filter: alpha(opacity = 50);
	opacity: .5;
}
</style>
<script type="text/javascript">
	window.onload = function() {
		var oDivLeaves = document.getElementById("items");
		var sHaveChildrenOrNot = "${haveChildrenOrNot}";//从后台接数据到js中
		var sMetaDataName = "${metaData.name}";
		var sMetaDataLevel = "${metaData.level}";
		var oRoot = document.getElementById("root");

		if (sHaveChildrenOrNot == 0) {
			oDivLeaves.style.display = "none";
		} else {
			oDivLeaves.style.display = "block";
			oRoot.innerHTML = "<h"+sMetaDataLevel+">" + sMetaDataName
					+ "</h"+sMetaDataLevel+">";
		}
	};
</script>

</head>

<body>
	<div>
		<h3>元数据【${metaData.name }】未被规则库引用，可以删除</h3>
		<hr>
	</div>
    <!-- S items -->
	<div id="items">
		<p class="p1">同时【${metaData.name }】下的子节点也将一起被删除，详情如下:</p>
		<ul id="ul1">
			<li id="root"></li>
			<c:forEach items="${childrenList }" var="children">
				<li>${children.name }</li>
			</c:forEach>
		</ul>
		<hr>
	</div>
	<!-- E items -->
	<p class="p1">是否确认删除？</p>
	
	<a class="btn btn-default" href="metaData/delete.action" role="button">返回删除页面</a>
	<button type="button" class="btn btn-primary confirm"
		data-toggle="modal" data-target="#myModal">确认
	</button>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<form action="metaData/deleteConfirmPOST.action?id=${metaData.id }" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-warning-sign"></span>
							再次确认是否删除！？
						</h4>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default cancel"
							data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-danger ok">确认删除</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- <form action="" method="post">
		<input type="submit" value="confirm">
	</form> -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
