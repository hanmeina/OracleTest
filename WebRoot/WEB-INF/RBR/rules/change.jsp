<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		
		<title>rules Change</title>
		
		<meta http-equiv="pragma" content="no-cache" >
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/rules-delete.css">

		<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>

		<!--  <script type="text/javascript"  src="js/rules-table-show.js" charset="utf-8" ></script>-->
		<script type="text/javascript" src="js/rules-query.js"></script>
	</head>
	
	<body>
        <!-- S container-fluid -->
		<div class="container-fluid">
		    <!-- S row -->
			<div class="row">
				<div id="header" class="col-sm-12">
					<h3 class="my-title"><span class="glyphicon glyphicon-pencil text-primary"></span> 修改规则</h3>
				</div>
			</div>
			<!-- E row -->
			
			<div class="row">
			    <!-- S col-md-12 -->
				<div class="col-md-12">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<td>规则编号</td>
								<td>规则前件（可信度）（权重）</td>
								<td>规则后件（可信度）</td>
								<td>规则可信度</td>
								<td>创建者</td>
								<td>创建时间</td>
								<td>审核员</td>
								<td>审核时间</td>
								<td>审核状态</td>
								<td>备注</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody>
						  <c:forEach items="${allRulesList}" var="rule">
							<tr>
								<td>${rule.id }</td>
								<td>
									<c:forEach items="${rule.conditionSet }" var="condition">
									${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
									</c:forEach>
								</td>
								<td>
									<c:forEach items="${rule.conclusionSet }" var="conclusion">
									${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
									</c:forEach>
								</td>
								<td>${rule.reliability }</td>
								<td>${rule.userName }</td>
								<td>${rule.submitTime }</td>
								<td>${rule.managerName }</td>
								<td>${rule.passTime }</td>
								<c:choose>
								   <c:when test="${rule.state == '1'}">  
								          <td>审核通过</td>
								   </c:when>
								   <c:when test="${rule.state == '4'}"> 
								        <td>规则使用过</td>
								   </c:when>
								</c:choose>
								<td>${rule.info }</td>
					           	<!--<a href="metaData/${metaData.id }/deleteWarning">${metaData.name }</a> -->
								<td><a href="rules/changeCheck.action?id=${rule.id }" class="btn btn-primary">修改</a>
							  </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- E col-md-12 -->
			</div>
			
		</div>
		<!-- E container-fluid -->
	</body>
</html>