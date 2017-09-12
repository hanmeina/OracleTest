<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reviewcase.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

<link rel="stylesheet" href="css/bootstrap.css" type="text/css"></link>	

<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/respond.min.js"></script>


  </head>
  
  <body>
  <div class="container-fluid"  id="mainContent"  style="border:0 solid black;margin:  0 0 0 0;">
  <div style="border:0 solid black;margin: 1 10 0 0px;">
	<div class="btn-group" style="margin: 1 10 0 250px;">
	  <a href="wx/message/sourceParameter.jsp" target="content"><button class="btn btn-large">推理原始参数</button></a>
	  <!-- <button class="btn btn-large">推理结果参数</button> -->
	  <a href="wx/message/workMessage.jsp" target="content"><button class="btn btn-large">质量判定结果</button></a>
	  <a href="wx/message/auditMessage.jsp" target="content"><button class="btn btn-large">决策审核结果</button></a>
	</div>
	
	
	<div  id="accordion2" style="border:0 solid black;margin: 20 0 0 0px;">
 	<center>推理原始参数</center>
 	<br>
  <div >

       

    <!-- <div id="collapseOne" class="accordion-body collapse in"> -->
      <div class="accordion-inner">
      <form action="/CBR/Search_result.jsp" method="post">
		<table align="center">

			<tr><td>来源：
				<select id="product_search" name="node_name"  >
					<option>质量判定系统</option>
					<option>设备监测系统</option>
					<option>生产管理系统</option>
				</select>
     		<!-- </td></tr>
     		
			<tr><td> -->
			<input type="submit" class="btn" value="搜索"/></td></tr>
			
		</table>
	</form>
      </div>
    <!-- </div> -->
  </div>
</div>
	
	   <div style="border:0 solid black;margin: 20 0 0 0px;"><!-- table -->
	<table class="table" align="center" >
	<!-- <caption><h4>推理原始参数</h4></caption> -->
	<thead>
		<tr>
			<th><a>全选</a></th>
			<th>编号</th>
			<th>标题</th>
			<th>创建时间</th>
			<th>来源</th>
			<th>状态</th>
			<th>操作</th>

		</tr>
	</thead>
	<tbody>
		<tr >
			<td><input type="checkbox" ></td>
			<td>1</td>
			<td>aa</td>
			<td>2013/07</td>
			<td>质量判定系统</td>
			<td>待处理</td>
			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">查看</a>
				<!-- <a href="wx/message/selectTuiLiPattern.jsp" role="button" class="btn" data-toggle="modal">推理</a> -->
			</td>
			
		</tr>
		<tr >
			<td><input type="checkbox" ></td>
			<td>2</td>
			<td>bb</td>
			<td>2013/07</td>
			<td>质量判定系统</td>
			<td>待处理</td>
			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">查看</a>
				<!-- <a href="#myModal" role="button" class="btn" data-toggle="modal">推理</a> -->
			</td>
		</tr>
		<tr >
			<td><input type="checkbox" ></td>
			<td>2</td>
			<td>cc</td>
			<td>2013/07</td>
			<td>设备监测系统</td>
			<td>待处理</td>
			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">查看</a>
				<!-- <a href="#myModal" role="button" class="btn" data-toggle="modal">推理</a> -->
			</td>
		</tr>
		<tr >
			<td><input type="checkbox" ></td>
			<td>3</td>
			<td>dd</td>
			<td>2013/07</td>
			<td>设备监测系统</td>
			<td>待处理</td>
			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">查看</a>
				<!-- <a href="#myModal" role="button" class="btn" data-toggle="modal">推理</a> -->
			</td>
		</tr>
		<tr >
			<td><input type="checkbox" ></td>
			<td>4</td>
			<td>ee</td>
			<td>2013/05</td>
			<td>生产管理系统</td>
			<td>待处理</td>
			<td>
				<a href="#myModal" role="button" class="btn" data-toggle="modal">查看</a>
				<!-- <a href="#myModal" role="button" class="btn" data-toggle="modal">推理</a> -->
			</td>
		</tr>
		<!-- <tr >
			<td></td><td></td> <td></td> 
			
			<td></td> <td></td>  		
		</tr> -->
	</tbody>
</table>
	
</div><!-- table -->
<table align="center" >
	<tr>
		<td><a href="#myModal" role="button" class="btn" data-toggle="modal">手动输入参数</a>&nbsp;&nbsp;</td> 
	 
		<td><a href="wx/message/selectTuiLiPattern.jsp" role="button" class="btn" data-toggle="modal">推理</a></td>
	</tr>
	</table>
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

	
	
                
	
	



<!-- Button to trigger modal -->

 
<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">质量设备监测参数</h3>
  </div>
  <div class="modal-body">
    <p>过热度:15~35℃</p>
    <p>浇筑开始位置:450~600mm</p>
    <p>结晶器窄面冷却水量:≤650L/min</p>
     <p>切割温度:≥800℃</p>
     <p>切割速度:100~500mm/min</p>
  </div>
  <div class="modal-footer">
    <button class="btn btn-primary">确定</button>
     <button class="btn btn-danger">取消</button>
  </div>
</div>
  </body>
</html>
