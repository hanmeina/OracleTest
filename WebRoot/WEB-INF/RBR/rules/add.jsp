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
		<title>rules Add</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/rules-add.css">
		<style type="text/css">
		.modal-backdrop {
			background-color: #E6E6FA;
		}
		.modal-backdrop.in {
			filter: alpha(opacity =   50);
			opacity: .5;
		}
		.glyphicon-warning-sign{
		color: #d9534f;
		}
		.glyphicon-ok {
		color: #468847;
		}
		</style>
		<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/rules-add.js"></script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div id="header" class="col-xs-12">
					<h3 class="my-title">
					<span class="glyphicon glyphicon-list text-primary"></span> 添加规则
					</h3>
				</div>
			</div>
			<!-- 规则预览具体内容 -->
			<div class="row block-area">
				<div class="col-xs-12">
					<div class="row">
						<div class="col-xs-12">
							<button id="rules-preview-btn" class="btn btn-default"
							type="button" data-toggle="collapse"
							data-target="#rules-preview-div" aria-expanded="true"
							aria-controls="rules-preview-div">▼ 规则预览</button>
						</div>
					</div>
					<div class="row collapse in" id="rules-preview-div">
						<div class="col-xs-12">
							<div class="row">
								<!-- 前件区（包括前件权重）-->
								<div class="col-xs-7">
									<h5>前件：</h5>
									<table class="table table-bordered table-striped ">
										<thead>
											<tr>
												<td>内容</td>
												<td>权重</td>
											</tr>
										</thead>
										<tbody>
											<tr exampletr>
												<td>前件示例（选择前件后，本行将被自动删除）</td>
												<td><input type="number" class="form-control"
													value="100" min="1" max="100" required="required">
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- 后件区（后件，规则可信度，创建人） -->
								<div class="col-xs-5">
									<h5>后件：</h5>
									<div class="right-name">后件示例（选择后件后，本行将被自动删除）</div>
									<h5>规则可信度：</h5>
									<input id="reliablity" type="number" class="form-control" value="100" min="1"
									max="100" required="required">
									
									
									<h5>该规则为基本规则：</h5>
									
									<input type="radio" name="sort" id="sort1" value="1" >是
									<input type="radio" name="sort" id="sort2" value="0" checked>否
								
									<h5>备注：</h5>
									<textarea class="form-control" rows="4" id="comment" name="comment" required="required" placeholder="请输入备注信息" onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;"></textarea>
									<!--<input id="comment" class="form-control" type="text"  name="comment"  placeholder="请输入备注信息">  -->
									<h5>创建人：</h5>
									<input type="text" class="form-control" id="metaDataManagerName"
									name="metaDataManagerName" value="userName" readonly>
								</div>
							</div>
							<!-- 清空 提交按钮 -->
							<div class="row">
								<div class="col-xs-12 text-right buttons-area">
									<a class="btn btn-default clear-all" href="rules/add"
									role="button">清空</a>
									<button class="btn btn-primary ok">提交</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 勾选规则 -->
			<!-- 前件勾选 -->
			<div class="row block-area">
				<div class="col-xs-12">
					<div class="row">
						<div class="col-xs-12">
							<button id="leftBtn" class="btn btn-default" type="button"
							data-toggle="collapse" data-target="#leftDiv"
							aria-expanded="true" aria-controls="leftDiv">
							▼ 选择<strong>前件</strong>
							</button>
						</div>
					</div>
					<div class="collapse" id="leftDiv">
						<div class="col-xs-12">
							<c:forEach items="${allMetaDatas}" var="metaData">
							<div class="checkbox" level="${metaData.level }">
								<label> <input type="checkbox" value="${metaData.name }"
								meta-id="${metaData.id }"> ${metaData.name }</label>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<!-- 后件勾选 -->
			<div class="row block-area">
				<div class="col-xs-12">
					<div class="row">
						<div class="col-xs-12">
							<button id="rightBtn" class="btn btn-default" type="button"
							data-toggle="collapse" data-target="#rightDiv"
							aria-expanded="false" aria-controls="rightDiv">
							▼ 选择<strong>后件</strong>
							</button>
						</div>
					</div>
					<div class="row collapse" id="rightDiv">
						<div class="col-xs-12">
							<c:forEach items="${allMetaDatas }" var="metaData">
							<div class="radio" level="${metaData.level }">
								<label> <input type="radio" name="right"
									value="${metaData.name }" meta-id-right="${metaData.id }">
								${metaData.name } </label>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="top">
			<a href="javascript:;">
				<div class="arrow"></div>
				<div class="stick"></div> </a>
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
							<span class="glyphicon glyphicon-warning-sign"></span> 前键不能为空
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