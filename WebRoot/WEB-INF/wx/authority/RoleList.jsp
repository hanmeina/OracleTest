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
    
    <title>My JSP 'searchcase.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  <link rel="stylesheet" href="css/bootstrap.css" type="text/css"></link>
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/respond.min.js"></script>
</head>
  <script type="text/javascript">
   
</script> 
  <body>
	<div >
	

 	<div  id="accordion2">
 	<center>角色管理</center>
 	&nbsp;&nbsp;<a href="wx/authority/RoleAdd.jsp"  data-toggle="modal">添加角色</a>
 	<br><br>
  <div >

       

    <!-- <div id="collapseOne" class="accordion-body collapse in"> -->
      <!-- <div class="accordion-inner">
      <form action="/CBR/Search_result.jsp" method="post">
		<table align="center">

			<tr><td>角色名：
				<input id="product_search" type="text" data-provide="typeahead"
     data-source='["a", "b", "c","d"]' name="node_name"  required="required" pattern="^[\u4e00-\u9fa5]{0,}$" >
     		
		
			<input type="submit" class="btn" value="搜索"/></td></tr>
			
		</table>
	</form>
      </div> -->
    <!-- </div> -->
  </div>
</div>

<!-- 2个搜索 -->

	
	<div style="margin:0 0 0 20px;"><!-- 搜索以下div -->




	<div><!-- table -->

	<table class="table" >
	<thead>
		<tr>
			<!-- <th>编号</th> -->
			<th>角色名</th>
			<!-- <th>描述</th>
			<th>创建日期</th> -->
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="role" items="${requestScope.roleList}" >
		<tr >
			<!-- <td>1</td>
			<td>案例专家</td>
			<td>345565</td>
			<td>2014/11/1</td> -->
			<td><c:out value="${role.rolename}"/></td>
			<td>
				<a href="wx/authority/RoleModify.jsp" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="Role/delRole.action?roleid=<c:out value='${role.roleid}'/>" role="button" class="btn" data-toggle="modal">删除</a>
				<a href="Authority/getAuthorityTree.action?roleid=${role.roleid}" role="button" class="btn" data-toggle="modal">授权</a>
			</td>
		</tr>
		</c:forEach>
		<!-- <tr >
			<td>2</td>
			<td>案例操作员</td>
			<td>345565</td>
			<td>2014/11/1</td>
			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">授权</a>
			</td>
		</tr>
		<tr >
			<td>3</td>
			<td>规则专家</td>
			<td>345565</td>
			<td>2014/11/1</td>
			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">授权</a>
			</td>
		</tr>
		<tr >
			<td>4</td>
			<td>规则操作员</td>
			<td>345565</td>
			<td>2014/11/1</td>
			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">授权</a>
			</td>
		</tr>
		<tr >
			<td>3</td>
			<td>高级管理员</td>
			<td>345565</td>
			<td>2014/11/1</td>
			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">授权</a>
			</td>
		</tr>
		<tr >
			<td>4</td>
			<td>普通用户</td>
			<td>345565</td>
			<td>2014/11/1</td>
			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">授权</a>
			</td>
		</tr> -->
		
		
		
		
		
	</tbody>
</table>

	
	</div><!-- table -->
	
	
                
	</div>
	
</div>
<script type="text/javascript" src="js/bootstrap-typeahead.js"></script>
</body>
</html>
