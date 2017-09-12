<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>log</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/log-query.css">



<script type="text/javascript">
	window.onload = function() {
		var btn = document.getElementById("btn");
		var formTable = document.getElementById("f");
		var s = document.getElementById("s");
		var o = document.getElementById("o");
		var os = document.getElementById("os");

		btn.onclick = function() {
			s.options[s.options.selectedIndex].selected = true;
			o.options[o.options.selectedIndex].selected = true;
			os.options[os.options.selectedIndex].selected = true;
			if ((s.value == 0 && o.value == 0 && os.value == 0)) {
				alert("查询内容不能为空");
			} else {

				if (s.value == "") {
					s.value = "0";
				}
				if (o.value == "") {
					o.value = "0";
				}
				if (os.value == "") {
					os.value = "0";
				}
				formTable.submit();
			}
		};
	};
</script>
</head>
<body>
	<div>
		<form id="f" action="log/querySearch.action" class="form-inline"
			method="post">
			<table>
				<tr valign="baseline">
					<td><select id="s" name="subSystem"
						style="height: 35px;width: 200px;">
							<option value="0">请选择子系统</option>
							<c:forEach items="${formTag}" var="ft">
								<c:if test="${ft.key lt 5 }">
									<option value="${ft.key }">${ft.value }</option>
								</c:if>
							</c:forEach>
					</select></td>
					<td><select id="o" name="operate"
						style="height: 35px;width: 200px;">
							<option value="0">请选择操作</option>
							<c:forEach items="${formTag}" var="ft">
								<c:if test="${ft.key ge 5 && ft.key lt 16 }">
									<option value="${ft.key }">${ft.value }</option>
								</c:if>
							</c:forEach>
					</select></td>


					<td><select id="os" name="operateSubject"
						style="height: 35px;width: 200px;">
							<option value="0">请选择操作对象</option>
							<c:forEach items="${formTag}" var="ft">
								<c:if test="${ft.key ge 16 && ft.key lt 24 }">
									<option value="${ft.key }">${ft.value }</option>
								</c:if>
							</c:forEach>
					</select></td>


					<td valign="middle"><input id="btn" type="button" class="btn btn-primary" value="搜索" />
					</td>
				</tr>

			</table>
		</form>
	</div>

	<div>
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<TD align="center">日志编号</TD>
					<TD align="center">子系统</TD>
					<TD align="center">操作</TD>
					<TD align="center">操作对象</TD>
					<TD align="center">操作人</TD>
					<TD align="center">操作时间</TD>
					<TD align="center">内容</TD>
					<TD align="center">推理链</TD>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty pm.datas}">
					<c:forEach items="${pm.datas}" var="log">
						<tr>
							<td>${log.id }</td>
							<td>${log.subSystem.name }</td>
							<td>${log.operate.name}</td>
							<td>${log.operateSubject.name}</td>
							<td>${log.userName }</td>
							<td>${log.operateTime }</td>
							<td>${log.content }</td>
							<td><a
								href="reasoning/getInferenceTree.action?id=${log.reasoningTree.treeId}">${log.reasoningTree.treeId
									}</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty pm.datas}">
					<tr>
						<td colspan="5" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
					</tr>
				</c:if>

			</tbody>
		</table>
	</div>
	<div class="footer_div" style="color: #3498db; font-size: 16px;  margin-left: 350px">
		<c:if test="${pm.isSearch eq 0}">
			<pg:pager url="log/query.action" items="${pm.total }"
				export="currentPageNumber=pageNumber">
				<pg:param name="parentId" />
				<pg:first>
					<a href="${pageUrl}" mce_href="${pageUrl}">首页</a>
				</pg:first>
				<pg:prev>
					<a href="${pageUrl }" mce_href="${pageUrl }">&lt;</a>
				</pg:prev>
				<pg:pages>
					<c:choose>
						<c:when test="${currentPageNumber eq pageNumber }">
							<font color="red">${pageNumber }</font>
						</c:when>
						<c:otherwise>
							<a href="${pageUrl }" mce_href="${pageUrl }">${pageNumber }</a>
						</c:otherwise>
					</c:choose>
				</pg:pages>
				<pg:next>
					<a href="${pageUrl }" mce_href="${pageUrl }">&gt;</a>
				</pg:next>
				<pg:last>
					<a href="${pageUrl }" mce_href="${pageUrl }">尾页</a>
				</pg:last>
			</pg:pager>
		</c:if>

		<c:if test="${pm.isSearch eq 1}">
			<pg:pager url="log/logSearch.action" items="${pm.total }"
				export="currentPageNumber=pageNumber">
				<pg:param name="subSystem" value="${pm.subSystem}" />
				<pg:param name="operate" value="${pm.operate}" />
				<pg:param name="operateSubject" value="${pm.operateSubject}" />
				<pg:first>
					<a href="${pageUrl}" mce_href="${pageUrl}">首页 </a>
				</pg:first>
				<pg:prev>
					<a href="${pageUrl }" mce_href="${pageUrl }">&lt;</a>
				</pg:prev>
				<pg:pages>
					<c:choose>
						<c:when test="${currentPageNumber eq pageNumber }">
							<font color="red">${pageNumber }</font>
						</c:when>
						<c:otherwise>
							<a href="${pageUrl }" mce_href="${pageUrl }">${pageNumber }</a>
						</c:otherwise>
					</c:choose>
				</pg:pages>
				<pg:next>  
		        <<a href="${pageUrl }" mce_href="${pageUrl }">&gt;</a>
				</pg:next>
				<pg:last>
					<a href="${pageUrl }" mce_href="${pageUrl }">尾页</a>
				</pg:last>
			</pg:pager>
		</c:if>
	</div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/respond.min.js"></script>
	
</body>
</html>
