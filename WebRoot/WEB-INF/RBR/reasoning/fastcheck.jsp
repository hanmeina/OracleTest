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
<title>reasoning Check</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/reasoning-check.css">


<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/reasoning-check.js"></script>

</head>
<body>
	<div class="wrap">
		<h1>勾选输入质量状态</h1>
		<div class="left">
			<div class="col-xs-12">
				<c:forEach items="${metaDataList}" var="metaData">
					<div class="checkbox" level="${metaData.level }">
						<label> <input type="checkbox"  value="${metaData.name}"
							meta-id="${metaData.id }">
							<div style="font-size:16px">${metaData.name }</div> </label>
					</div>
				</c:forEach>
			</div>
		</div> 
	</div>

	<div class="right">
		<h1>
			<div class="right-name">已选内容预览</div>
		</h1>
		<form id="form" name="form" action="reasoning/result.action" method="post">
			<div class="col-xs-12">
				<table class="table table-bordered table-striped " id="test">
					<thead>
						<tr>
							<td>内容</td>
						</tr>
					</thead>
					<tbody>
						<tr exampletr>
							<td>勾选质量状态后，本行将被自动删除</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 清空 提交按钮 -->
			<div class="row">
				<div class="col-xs-12 text-right buttons-area">
					<a class="btn btn-default clear-all"
						href="reasoning/selectFastQualityCondition.action" role="button">清空</a>
					<input type="hidden" name="idstr" value=""/>
					<input id="text1" type="hidden" name="fastOrAllRule" value="1"/>
					<button id="btn1" class="btn btn-primary ok" >快速推理</button>
				</div>
			</div>
		</form>
		<!-- <button class="btn btn-primary ok">开始推理</button> -->
	</div>
	
	<!-- 模态框 -->
			<div class="modal fade" id="leftErrorModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-warning-sign"></span> 勾选状态不能为空
							</h4>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default ok" data-dismiss="modal">确定</button>
						</div>
					</div>
				</div>
			</div>
	
</body>
</html>