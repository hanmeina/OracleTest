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

<title>metaDataDeleteWarning</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/metadata_tree_format.css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<style type="text/css">
body {
	margin: 0;
	padding: 0 5px 0 0;
	font-family: Microsoft YaHei, SimHei, sans-serif;
}
div{
	display: none;
}

table{
	font-size: 14px;
}
table thead {
	font-weight: 600;
}

p {
	font-size: 18px;
}
.fuckP{
	font-size: 18px;
	padding: 10px;
}

.header{
	margin:0;
	padding:10px;
	background-color: #ECF0F1;
}

.header span{
	color: #d9534f;
	line-height: 24px;
}


.ultree{
	padding: 0;
	margin: 0;
	padding-left: 15px;
	border: 1px dashed silver;
}
.ultree li {
	padding-top: 5px;
	padding-bottom: 5px;
	list-style: none;
	text-indent: 3em;
}
.ultree h1 {
	font-size:24px;
	text-indent: 0em;
	padding: 0;
	margin: 10px 0 0 0;
}

.ultree h2 {
	font-size:20px;
	text-indent: 2em;
	padding: 0px;
	margin: 0px;
}

.ultree h3 {
	font-size:16px;
	text-indent: 4.5em;
	padding: 0px;
	margin: 0px;
}
.ultree h4 {
	font-size:16px;
	text-indent: 6em;
	padding: 0px;
	margin: 0px;
}
.ultree h5 {
	font-size:16px;
	text-indent: 8em;
	padding: 0px;
	margin: 0px;
}
.ultree h6 {
	font-size:16px;
	text-indent: 10em;
	padding: 0px;
	margin: 0px;
}
.ultree span{
	padding: 0 5px;
}
/* 改变表格斑马线颜色 */
.table-striped>tbody>tr:nth-child(odd)>td,.table-striped>tbody>tr:nth-child(odd)>th
{
	background-color: #f2f4f4
}
</style>

<script type="text/javascript">
	window.onload = function() {
		var oSpan = document.getElementById("span");
		var oSpan2 = document.getElementById("span2");
		var flag = "${selfOrChildrenInRules}";
		
		//高亮小树
		var aChildrenNameList = ${childrenNameList };//数组，要变高亮的内容
		var sChiledrenName = "";					
		for(var i=0;i<aChildrenNameList.length;i++){//变高亮
			$(".ulTree>li span:contains("+aChildrenNameList[i]+")").css("background","#d5e7d3");
			console.log(i+"---->"+aChildrenNameList[i]);
			sChiledrenName += "【"+aChildrenNameList[i]+"】";
		}
		$("#childrenName").html(sChiledrenName);
		
		//审核状态从012变为，待审核，审核通过，未通过
		//0.待审核  1.审核通过  2.审核未通过
		//遍历表格
		var aTable = $(".table");	//选中table，可能会有两张表格
		for(var i=0;i<aTable.length;i++){
			for(var j=0;j<aTable[i].tBodies[0].rows.length; j++){
				if(aTable[i].tBodies[0].rows[j].cells[8].innerHTML==0){//这里的8指的是第8列,第8列是审核状态
					aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "待审核";
				};
				if(aTable[i].tBodies[0].rows[j].cells[8].innerHTML==1){
					aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "审核通过";
				};
				if(aTable[i].tBodies[0].rows[j].cells[8].innerHTML==2){
					aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "未通过";
				};
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
			};
		}
		
		//控制我的小树中的第一行的内容和格式
		$(".ultree>li:first").html("<h"+"${thisMetaData.level}"+">"+"${thisMetaData.name}"+"</h"+"${thisMetaData.level}"+">");
		
		//1:集合中只有当前节点，没有子孙节点
		//2:集合中没有当前节点，只有子孙节点
		//3:集合中既有当前节点，又有子孙节点
		switch (flag) {
		case "1":
			$("#div1").show();
			break;
		case "2":
			oSpan.innerHTML = "虽然当前节点【"+"${thisMetaData.name}"+"】没有在规则库中被引用，但是其子孙节点";
			oSpan2.innerHTML = "在规则库中被引用";
			$("#div2").show();
			break;
		case "3":
			$("#div1").show();
			$("#div2").show();
			break;
		default:
			break;
		}
		
	};
</script>
</head>

<body>
	<h3 class="header">&nbsp;<span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;&nbsp;删除警告</h3>
	<!-- S div1 -->
	<div id="div1">
		<hr>
		<p>
			您正在尝试删除的元数据【${thisMetaData.name }】在规则库中被引用<br>规则引用详情如下：
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
				<c:forEach items="${thisDataRulesList}" var="rule">
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
						<td>${rule.state }</td>
						<td>${rule.info }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- E div1 -->
	<!-- S div2 -->
	<div id="div2">
		<hr>
		<p>
			<span id="span">同时，【${thisMetaData.name }】的子孙节点</span>
			<span id="childrenName"></span>
			<span id="span2">也在规则库中被引用</span><br>
			
			<ul class="ulTree">
				<li></li>
				<c:forEach items="${littleTree }" var="littleTree">
					<li>${littleTree.name }</li>
				</c:forEach>
			</ul>
			<p></p>
			<span class="fuckP">规则引用详情如下：</span>
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
				<c:forEach items="${childrenRulesList}" var="rule">
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
						<td>${rule.state }</td>
						<td>${rule.info }</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
	<!-- E div2 -->
	
	<hr>
	<p>
		请前往<a href="rules/delete.action">规则删除</a>页面先删除相关规则。
	</p>
	<p>
		或返回<a href="metaData/delete.action">元数据删除</a>页面。
	</p>

</body>
</html>