<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>changeWarning</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

   <link href="css/bootstrap.min.css" rel="stylesheet">
   <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script  type="text/javascript" src="js/bootstrap.min.js"></script>
	<!--  <script src="js/rules-table-show2.js"></script>-->
	<script src="js/jquery.form.js"></script>

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
	filter: alpha(opacity = 50);
	opacity: .5;
}

.glyphicon-ok {
	color: #468847;
}

.modal-footer a {
	margin-right: 10px;
	padding-left: 29px;
	padding-right: 29px;
}
</style>

<script type="text/javascript">
		$(function() {
			var isUsedFlag = "${used}";//后台传入的标记位
			console.log("是否被使用（1表示被使用，0表示未被使用）：" + isUsedFlag);
			if (isUsedFlag == 0) {
				$("#used").hide();
				$("#notUsed").show();
				$(".header")
						.html(
								"&nbsp;<span class='glyphicon glyphicon-pencil'></span>&nbsp;&nbsp;规则修改");//更换标题上的文字
			} else {
				$("#notUsed").hide();
				$("used").show();
				$(".header")
						.html(
								"&nbsp;<span class='glyphicon glyphicon-exclamation-sign'></span>&nbsp;&nbsp;规则修改警告");
			}
			
			
			
		
		//使用jQuery Form插件实现无刷新提交表单
		$("#myForm").ajaxForm(function() {
			$("#myModal").modal("show");
		});
		
		
		
		//审核状态从012345变为，待审核，审核通过，未通过，休眠，通过，删除规则
		//0.待审核  1.审核通过  2.审核未通过 3.休眠 4.通过 5删除规则
		//遍历表格
		var aTable = $(".table"); //选中table，可能会有两张表格
		for (var i = 0; i < aTable.length; i++) {
			// alert(aTable[i].tBodies[0].rows.length);
			 console.log(aTable[i]);
			console.log(aTable[i].tBodies[0].rows.length); 
			for (var j = 0; j < aTable[i].tBodies[0].rows.length; j++) {
				if (aTable[i].tBodies[0].rows[j].cells[6].innerHTML == 0) {//这里的8指的是第8列,第8列是审核状态
					alert(aTable[i].tBodies[0].rows[j].cells[8].innerHTML);
					aTable[i].tBodies[0].rows[j].cells[6].innerHTML = "待审核";
				}
				;
				if (aTable[i].tBodies[0].rows[j].cells[6].innerHTML == 1) {
				alert(aTable[i].tBodies[0].rows[j].cells[6].innerHTML);
					aTable[i].tBodies[0].rows[j].cells[6].innerHTML = "审核通过";
				}
				;
				if (aTable[i].tBodies[0].rows[j].cells[6].innerHTML == 2) {
					aTable[i].tBodies[0].rows[j].cells[6].innerHTML = "未通过";
				}
				;
				if (aTable[i].tBodies[0].rows[j].cells[6].innerHTML === '3') {
					aTable[i].tBodies[0].rows[j].cells[6].innerHTML = "休眠";
				}
				;
				if (aTable[i].tBodies[0].rows[j].cells[6].innerHTML === '4') {
					aTable[i].tBodies[0].rows[j].cells[6].innerHTML = "规则使用过";
				}
				;
				if (aTable[i].tBodies[0].rows[j].cells[6].innerHTML === '5') {
					aTable[i].tBodies[0].rows[j].cells[6].innerHTML = "删除规则";
				}
				;
			}
			
		}
		
		});
	</script>
</head>

<body scroll="no" style="overflow-x:hidden">
    <!--S container-fluid  -->
	<div class="container-fluid">

		<!--S 标题 -->
		<div class="row">
			<h3 class="header">标题也是可变的~啦啦啦</h3>
			<hr>
		</div>
        <!--E 标题 -->
		<!--S 被引用状态区域 -->
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
							</c:forEach></td>
						<td><c:forEach items="${rule.conclusionSet }"
								var="conclusion">
							${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
							</c:forEach></td>
						<td>${rule.reliability }</td>
						<td>${rule.userName }</td>
						<td>${rule.submitTime }</td>
						<td>${rule.state}</td>
						  <!--   <c:choose>
						         <c:when test="${rule.state == '1'}">  
								          <td>审核通过</td>
								   </c:when>
								   <c:when test="${rule.state == '4'}"> 
								        <td>规则使用过</td>
						 		   </c:when>
						    </c:choose>-->
						<td>${rule.info }</td>
					</tr>
				</tbody>
			</table>
			<div id="notUsed">
				<p>您正在修改的规则【${rule.id }】没有在规则库中引用，请在下面编辑并保存</p>
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
				<p>若坚持修改，请在下面编辑并保存</p>
			</div>


			<!--S 表单区域 -->
			<div class="row">
				<form id="myForm"
					action="rules/changeCheckPOST.action?id=${rule.id }" method="post">
					<!-- S  col-xs-6-->
					<div class="col-xs-6">
					     <!-- S form-group -->
						<div class="form-group">
							<label for="reliability">规则可信度</label> <input type="number"
								class="form-control" id="reliability" name="reliability"
								value="${rule.reliability }" min="1" max="100"
								required="required">
						</div>
						<div class="form-group">
							<label for="sort">该规则为基本规则：</label> <br> <input type="radio" 
								name="sort" id="sort1" value="1" checked="checked" >是 <input type="radio"
								name="sort" id="sort2" value="0">否
						</div>
					
						<hr>
						<div class="form-group">
							<a href="rules/change.action" class="btn btn-default cancel" role="button" >返回规则修改</a>
							<button type="submit" class="btn btn-primary ok">保存修改</button>
						</div>
						<!-- E form-group -->
					</div>
					<!-- E col-xs-6-->
				</form>
			</div>
			<!--E 表单区域 -->
		</div>
		<!--E 被引用状态区域 -->
	</div>
    <!--E container-fluid  -->
	
	<!-- 修改提交后弹出的模态框Modal -->
	<!-- S modal fade -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<!-- S modal-dialog modal-sm -->
		<div class="modal-dialog modal-sm">
		     <!-- S modal-content -->
			<div class="modal-content">
			    <!-- S modal-header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-ok"></span> 修改成功！
					</h4>
				</div>
				 <!-- E modal-header -->
				 <!-- S modal-footer -->
				<div class="modal-footer">
					<a href="rules/change.action" class="btn btn-primary "
						role="button">确认</a>
				</div>
				<!-- E modal-footer -->
			</div>
			<!-- E modal-content -->
		</div>
		<!-- E modal-dialog modal-sm -->
	</div>
	<!-- E modal fade -->

	
	
	
	
</body>
</html>
