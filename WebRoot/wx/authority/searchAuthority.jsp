<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
 	<center>权限管理</center>
 	&nbsp;&nbsp;<a href="wx/authority/AuthorityAdd.jsp"  data-toggle="modal">添加父权限</a>
 	<br>
  <div >

       

    <!-- <div id="collapseOne" class="accordion-body collapse in"> -->
      <div class="accordion-inner">
      <form action="/CBR/Search_result.jsp" method="post">
		<table align="center">

			<tr><td>权限名：</td>
				<td><input type="text" data-provide="typeahead"
     data-source='["a", "b", "c","d"]' name="node_name"  required="required" pattern="^[\u4e00-\u9fa5]{0,}$" >
			     </td>
			     <td></td>
			</tr>
			
			<tr><td>父权限：</td>
				<td>
					<select>
						<option>案例管理</option>
						<option>规则元数据管理</option>
						<option>规则管理</option>
						<option>消息管理</option>
						<option>日志管理</option>
					</select>
				</td>
     			
		
			<td><input type="submit" class="btn" value="搜索"/></td></tr>
			
		</table>
	</form>
      </div>
    <!-- </div> -->
  </div>
</div>

<!-- 2个搜索 -->

	
	<div style="margin:0 0 0 20px;"><!-- 搜索以下div -->




	<div><!-- table -->

	<table class="table" >
	<thead>
		<tr>
			<th>编号</th>
			<th>权限名</th>
			<th>父权限名</th>
			<th>描述</th>

			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<tr >
			<td>1</td>
			<td>案例管理</td>
			<td>无</td>
			<td>345565</td>

			<td>
				<a href="wx/authority/AuthorityModify.jsp" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
				<a href="wx/authority/AuthorityChildAdd.jsp" role="button" class="btn" data-toggle="modal">添加子权限</a>
			</td>
		</tr>
		<tr >
			<td>2</td>
			<td>案例检索</td>
			<td>案例管理</td>
			<td>345565</td>

			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
			</td>
		</tr>
		<tr >
			<td>3</td>
			<td>案例添加</td>
			<td>案例管理</td>
			<td>345565</td>

			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
			</td>
		</tr>
		<tr >
			<td>4</td>
			<td>案例修改</td>
			<td>案例管理</td>
			<td>345565</td>

			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
			</td>
		</tr>
		<tr >
			<td>5</td>
			<td>案例删除</td>
			<td>案例管理</td>
			<td>345565</td>

			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
			</td>
		</tr>
		<tr >
			<td>6</td>
			<td>规则元数据管理</td>
			<td>无</td>
			<td>345565</td>

			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
				<a href="wx/authority/AuthorityChildAdd.jsp" role="button" class="btn" data-toggle="modal">添加子权限</a>
			</td>
		</tr>
		<tr >
			<td>7</td>
			<td>规则管理</td>
			<td>无</td>
			<td>345565</td>

			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">添加子权限</a>
			</td>
		</tr>
		<tr >
			<td>8</td>
			<td>消息管理</td>
			<td>无</td>
			<td>345565</td>

			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">添加子权限</a>
			</td>
		</tr>
		<tr >
			<td>9</td>
			<td>日志管理</td>
			<td>无</td>
			<td>345565</td>

			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">修改</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">删除</a>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">添加子权限</a>
			</td>
		</tr>
		
		
		
		
		
	</tbody>
</table>

	
	</div><!-- table -->
	
	
                <div class="pagination pagination-right"><!-- page -->
                
                  <ul contenteditable="true">
                    <li><a href="#">上一页</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">下一页</a></li>
                  </ul>
                </div>
	</div>
	
</div>
<script type="text/javascript" src="js/bootstrap-typeahead.js"></script>
</body>
</html>
