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

<title>My JSP 'examingRule.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/rules-delete.css">
<style type="text/css">
body {
	margin: 0;
	padding: 0 5px 0 0;
	font-family: Microsoft YaHei, SimHei, sans-serif;
}

.header {
	margin: 0;
	padding: 10px;
	background-color: #ECF0F1;
}

.header span {
	color: #d9534f;
	line-height: 24px;
}

table thead {
	font-weight: 600;
}

p {
	font-size: 18px;
}

textarea { /* 禁止文本域被拖动 */
	resize: none;
}

.cancel {
	width: 122px;
	margin-right: 10px;
}

.ok {
	width: 120px;
}

/* 模态框背景 */
.modal-backdrop {
	background-color: #E6E6FA;
}

.modal-backdrop.in {
	filter: alpha(opacity =         50);
	opacity: .5;
}
</style>
</head>

<body scroll="no" style="overflow-x:hidden">
	<div class="container-fluid">
		<!-- 标题 -->
		<div class="row">
			<h3 class="header">标题也是可变的~啦啦啦</h3>
			<hr>
		</div>

		<!-- 被引用状态区域 -->
		<div class="row">
			<p>当前修改的规则是：</p>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<td>规则编号</td>
						<td>规则前件（可信度）（权重）</td>
						<td>规则后件（可信度）</td>
						<td>规则可信度</td>
						<td>创建者</td>
						<td>创建时间</td>
						<td>审核状态</td>
						<td>备注</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${rule.id }</td>
						<td><c:forEach items="${rule.conditionSet }" var="condition">
							${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
							</c:forEach>
						</td>
						<td><c:forEach items="${rule.conclusionSet }"
								var="conclusion">
							${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
							</c:forEach>
						</td>
						<td>${rule.reliability }</td>
						<td>${rule.userName }</td>
						<td>${rule.submitTime }</td>
						<td status>${rule.state }</td>
						<td>${rule.info }</td>
					</tr>
				</tbody>
			</table>
			<hr>
			<div id="notUsed">
				<p>您正在修改的规则【${rule.id }】没有在规则库中引用，可以删除</p>
			</div>

			<div id="used">
				<p>您正在修改的规则【${rule.id }】已被引用，引用详情如：</p>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td align="center">日志编号</td>
							<td align="center">子系统</td>
							<td align="center">操作</td>
							<td align="center">操作对象</td>
							<td align="center">操作人</td>
							<td align="center">操作时间</td>
							<td align="center">内容</td>
							<td align="center">推理链</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${logList}" var="log">
							<tr>
								<td>${log.id }</td>
								<td>${log.subSystem.name }</td>
								<td>${log.operate.name}</td>
								<td>${log.operateSubject.name}</td>
								<td>${log.userName }</td>
								<td>${log.operateTime }</td>
								<td>${log.content }</td>
								<td>${log.reasoningTree.treeId }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<p>被使用过的规则不能被删除，若坚持删除，将休眠该规则</p>
			</div>
			<div class="row section">
				<div class="col-xs-12">
					<hr>
					<h4>
						<span class="glyphicon glyphicon-question-sign text-primary"></span>
						是否确认删除：
					</h4>
					<a href="rules/delete.action" class="btn btn-default btn-sm">返回删除页面</a>
					<button type="button" class="btn btn-primary btn-sm ok"
						data-toggle="modal" data-target="#myModal">确认</button>

				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<form action="rules/deleteConfirm.action?id=${rule.id }"
						method="post">
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


			<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
			<script type="text/javascript" src="js/bootstrap.min.js"></script>
			<script src="js/rules-table-show2.js"></script>
			<script src="js/rules-delete.js"></script>

			<script type="text/javascript">
				var used = "${used}";
				console.log("冲突规则后台传到页面的数据--   " + used);

				if (used === "0") {
					console.log("当前规则未被使用");
					$("#used").hide();
					$("#notUsed").show();
					$(".header")
							.html(
									"&nbsp;<span class='glyphicon glyphicon-trash text-primary'></span>&nbsp;&nbsp;规则删除");//更换标题上的文字
				} else {
					console.log("当前规则已被使用");
					$("#notUsed").hide();
					$("used").show();
					$(".header")
							.html(
									"&nbsp;<span class='glyphicon glyphicon-exclamation-sign'></span>&nbsp;&nbsp;规则删除警告");
				};
			</script>
</body>
</html>
