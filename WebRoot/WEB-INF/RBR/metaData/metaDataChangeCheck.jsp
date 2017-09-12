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
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jquery.form.js"></script>
<script src="js/bootstrap.min.js"></script>

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
/* S modal fade*/
.modal-backdrop {
	background-color: #E6E6FA;
}

.modal-backdrop.in {
	filter: alpha(opacity =   50);
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
/* E modal fade */
</style>

<script type="text/javascript">
	$(function() {
		var isUsedFlag = "${isUsed}";//后台传入的标记位
		/* console.log("是否被使用（1表示被使用，0表示未被使用）：" + isUsedFlag); */
		if (isUsedFlag == 0) {
			$("#used").hide();
			$("#notUsed").show();
			$(".header")
					.html(
							"&nbsp;<span class='glyphicon glyphicon-pencil'></span>&nbsp;&nbsp;元数据修改");//更换标题上的文字
		} else {
			$("#notUsed").hide();
			$("used").show();
			$(".header")
					.html(
							"&nbsp;<span class='glyphicon glyphicon-exclamation-sign'></span>&nbsp;&nbsp;元数据修改警告");
		}

		//审核状态从012345变为，待审核，审核通过，未通过，休眠，通过，删除规则
		//0.待审核  1.审核通过  2.审核未通过 3.休眠 4.通过 5删除规则
		//遍历表格
		var aTable = $(".table"); //选中table，可能会有两张表格
		for (var i = 0; i < aTable.length; i++) {
			/* console.log(aTable[i]);
			console.log(aTable[i].tBodies[0].rows.length); */
			for (var j = 0; j < aTable[i].tBodies[0].rows.length; j++) {
				if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML == 0) {//这里的8指的是第8列,第8列是审核状态
					aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "待审核";
				}
				;
				if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML == 1) {
					aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "审核通过";
				}
				;
				if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML == 2) {
					aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "未通过";
				}
				;
				if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML === '3') {
					aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "休眠";
				}
				;
				if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML === '4') {
					aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "规则使用过";
				}
				;
				if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML === '5') {
					aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "删除规则";
				}
				;
			}
			
		}

		//使用jQuery Form插件实现无刷新提交表单
		$("#myForm").ajaxForm(function() {
			$("#myModal").modal("show");
		});
	});
</script>
</head>

<body scroll="no" style="overflow-x:hidden">
	<!--S container-fluid -->
	<div class="container-fluid">
		<%-- <p>
			整个规则list：${rulesListThisData }<br>
			<c:forEach items="${rulesListThisData }" var="rule">
				规则id：${rule.id }<br>
				规则前件集：${rule.conditionSet }<br>
				<c:forEach items="${rule.conditionSet }" var="condition">
					前件中的元数据：${condition.metaData }<br>
					前件中的元数据name：${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
				</c:forEach>
				规则后件集：${rule.conclusionSet }<br>
				<c:forEach items="${rule.conclusionSet }" var="conclusion">
					后件中的元数据：${conclusion.metaData }<br>
					后件中的元数据name：${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
				</c:forEach>
			</c:forEach>
		</p> --%>


		<!--start 标题 -->

		<!-- S row -->
		<div class="row">
			<h3 class="header">标题也是可变的~啦啦啦</h3>
			<hr>
		</div>

		<!-- S 被引用状态区域 -->
		<div class="row">
			<!-- S notUsed -->
			<div id="notUsed">
				<p>您正在修改的元数据【${metaDataNormal.name }】没有在规则库中引用，请在下面修改并保存</p>
			</div>
			<!-- E notUsed -->
			<!-- S used -->
			<div id="used">
				<p>
					您正在修改的元数据【${metaDataNormal.name }】在规则库中被引用<br>规则引用详情如下：
				</p>
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
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${rulesListThisData}" var="rule">
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
								<td>${rule.managerName }</td>
								<td>${rule.passTime }</td>
								<td>${rule.state }</td>
								<td>${rule.info }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<p>
					如果修改当前元数据【${metaDataNormal.name }】，相关的规则也会改变。<br>若坚持修改，请在下面编辑并保存
				</p>
			</div>
			<!-- E used -->
			<hr>
		</div>
		<!-- E 被引用状态区域 -->
		<!--S 表单区域 -->
		<div class="row">
			<form id="myForm"
				action="metaData/changeCheckPost.action?id=${metaDataNormal.id }"
				method="post">
				<div class="row">
					<!--S 第一列开始 -->
					<div class="col-xs-6">
						<div class="form-group">
							<label for="metaDataName">元数据内容：</label>
							<textarea class="form-control" rows="5" id="metaDataName"
								name="metaDataName" required="required"
								onKeypress="javascript:if(event.keyCode == 32)event.returnValue = false;">${metaDataNormal.name }</textarea>
						</div>
						<div class="form-group">
							<label for="metaDataReliability">元数据可信度</label> <input
								type="number" class="form-control" id="metaDataReliability"
								name="metaDataReliability"
								value="${metaDataNormal.reliability }" min="1" max="100"
								required="required">
						</div>
					</div>
					<!--E 第一列结束 -->
					<!--S 第二列开始 -->
					<div class="col-xs-6">
						<div class="form-group">
							<label for="metaDataId">元数据id</label> <input type="text"
								class="form-control" id="metaDataId"
								value="${metaDataNormal.id }" readonly>
						</div>
						<div class="form-group">
							<label for="metaDataManagerName">当前管理员（您的登陆名）</label> <input
								type="text" class="form-control" id="metaDataManagerName"
								name="metaDataManagerName" value="userName" readonly>
						</div>
						<div class="form-group">
							<label for="metaDataDate">当前日期</label> <input type="text"
								class="form-control" id="metaDataDate" name="metaDataDate"
								value="${metaDataDataNow }" readonly>
						</div>
					</div>
					<!--E 第二列结束 -->
				</div>

				<hr>
				<a href="metaData/change.action" class="btn btn-default cancel"
					role="button">返回元数据修改</a>
				<button type="submit" class="btn btn-primary ok">保存修改</button>
			</form>

		</div>
		<!--E 表单区域 -->
	</div>
	<!-- E row -->
	<!-- S container-fluid -->

	<!-- 修改提交后弹出的模态框Modal -->
	<!-- S modal fade-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-ok"></span> 修改成功！
					</h4>
				</div>
				<div class="modal-footer">
					<a href="metaData/change.action" class="btn btn-primary "
						role="button">确认</a>
				</div>
			</div>
		</div>
	</div>
	<!-- E modal fade-->
</body>
</html>
