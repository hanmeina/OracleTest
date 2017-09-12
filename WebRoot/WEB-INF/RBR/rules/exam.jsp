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

<title>Rules exam</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/rules-exam.css">
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div id="header" class="col-xs-12">
				<h3 class="my-title">
					<span class="glyphicon glyphicon-list-alt text-primary"></span>
					规则审核
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<h4>
					共有<strong>${waitRulesListLength}</strong>条规则待审核。详情如下：
				</h4>
			</div>
		</div>
		<div class="row rule-block">
			<div class="col-xs-12">
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
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${waitRulesList}" var="rule">
							<tr>
								<td>${rule.id }</td>
								<td><c:forEach items="${rule.conditionSet }"
										var="condition">
									${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
									</c:forEach></td>
								<td><c:forEach items="${rule.conclusionSet }"
										var="conclusion">
									${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
									</c:forEach></td>
								<td>${rule.reliability }</td>
								<td>${rule.userName }</td>
								<td>${rule.submitTime }</td>
								<c:choose>
								   <c:when test="${rule.state == '0'}">  
								          <td>待审核</td>
								   </c:when>
								  
								</c:choose>
								
								<td>${rule.info }</td>
								<td><a href="rules/examing.action?id=${rule.id }" class="btn btn-primary">审核</a>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script src="js/rules-table-show2.js"></script>
</body>
</html>