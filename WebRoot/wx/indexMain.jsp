<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>案例推理子系统</title>
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
  
  <script type="text/javascript">
$(document).ready(function () {
	$("a.click").click(function () {  //双击事件
                  var id = $(this).attr("id");//1. 获得a标签id（首页为例：a1）
                  //alert(id);
                  //2. 切换到首页对应的标签tab_a1中
                  //   根据获得的id拼接字符串得到相应tab的id串，注意tab的id命名规则
                  var tabid = "tab_"+id;
                  //alert(tabid);
                  var $tab = $("#"+tabid);//3. 获取相应tab，注意选择器中含有变量的写法
                  var activeId = $("li.active").eq(0).attr("id"); //获取当前被激活的tab
                  //alert(activeId);
                  document.getElementById(activeId).className="";
                  //$("li.active").eq(0).removeClass.addClass(""); //4. 将当前激活的tab设为不激活
                  $tab.removeClass().addClass("active");
                  
                  
              });
});
function changeTab(){
	var $a = $(window.event.srcElement); //获取DOM事件对象
	alert($a.tagName);
	//var id = $a.attr("data");
	//alert("1"+$a.attr("href"));
}


function productlinetab()
{

	document.getElementById('tab_3').className="active";
	document.getElementById('tab_a1').className="";
	document.getElementById('tab_2').className="";
	
	//document.getElementById('list2').className="active";
	//document.getElementById('list1').className="";
	//document.getElementById('list3').className="";

	
}
function casetab()
{

	document.getElementById('tab_2').className="active";
	document.getElementById('tab_a1').className="";
	document.getElementById('tab_3').className="";
	
	//document.getElementById('list3').className="active";
	//document.getElementById('list1').className="";
	//document.getElementById('list2').className="";
}
function hometab()
{

	document.getElementById('tab_a1').className="active";
	document.getElementById('tab_2').className="";
	document.getElementById('tab_3').className="";
}

</script>
  
  
  
  
  <body>
  <base target="content"/>
  <div class="navbar">
  <div class="navbar-inner" >
    <a class="brand" href="#" style="margin-left: 280px;">连铸质量保证主控系统</a>
    <!-- <ul class="nav">
      <li class="active"><a href="#">CBR</a></li>
      <li><a href="#">MBR</a></li>
      <li><a href="#">RBR</a></li>
    </ul> -->
    
    <ul class="nav" style="margin-left: 800px;font-size: 16px;">
					<li><a href="#"><span class="icon-comment"></span>
							消息 (1)</a>
					</li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><span class="icon-user"></span>
							黄励哲&nbsp;|&nbsp;<span class="caret"></span> </a>
						<ul class="dropdown-menu" role="menu" style="width: 150px;">
							<li><a href="#"><span class="icon-cog"></span>
									设置</a></li>
							<li class="divider"></li>
							<li><a href="#"><span class="icon-signout"></span> 退出</a></li>
						</ul>
					</li>
				</ul>
  </div>
</div>
  
  
   <div class="container-fluid" style="margin-left: 300px;"><!-- border: 1 solid red;s -->
	<div class="row-fluid" >
		<div class="span9" >
			<div class="row-fluid" >
				<div class="span12" >
					<div class="page-header" style="width: 1160px;">
							<img alt="140x140" src="img/logo.png" />
							<span style="margin: 0 0 0 400;">消息(1)|高级管理员 |黄励哲 <a>注销</a></span>
						
						
						
					</div>
					<div class="row-fluid" >
						<div class="span12">
							<div class="row-fluid">
								<div  class="span3"  style="border: 1 solid #CCCCCC;margin:  -10 0 0 0 ">
								<div style="margin: 10 10 0 10px" >
									<ul class="nav nav-list" >
									
									<li  id="list1">
											<a id="a1" class="click" href="iframeMain.jsp" target="content">
											<h4><span class="icon-home icon-1x " ></span>&nbsp;首页</h4></a>
									</li>
									<li class="divider">
									</li>
									
									<li  id="list3" style="border-bottom: 1 solid #CCCCCC">	
											<a href="wx/Tab3Main.jsp" onclick="casetab()" target="content">
											<h4><span class="icon-user-md " ></span>&nbsp;基于领域知识推理</h4></a>
										</li>
									<li style="margin: -5 0 -10 0">
										<a href="/CBR/selectline.jsp" style="oration: none;" onclick="casetab()" target="content">
											<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基于案例推理</h5></a>
									</li>
									<li style="margin: -5 0 -10 0">
										<a href="/CBR/reviewcase.jsp" style="text-decoration: none;" onclick="casetab()" target="content">
										<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基于规则推理</h5></a>
									</li>
									<li style="margin: -5 0 -10 0">
										<a href="/CBR/reviewcase.jsp" style="text-decoration: none;" onclick="casetab()" target="content">
										<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基于模型推理</h5></a>
									</li>
									<li class="divider"></li>
									
									<li  id="list3" style="border-bottom: 1 solid #CCCCCC">	
										<a href="wx/Tab3Main.jsp" onclick="casetab()" target="content">
										<h4><span class="icon-user-md " ></span>&nbsp;基于角色访问控制</h4></a>
									</li>

									<li style="margin: -5 0 -10 0">
										<a href="wx/authority/searchUser.jsp" style="text-decoration: none;" onclick="casetab()" target="content">
											<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户管理</h5></a>
									</li>
									<li style="margin: -5 0 -10 0">
										<a href="wx/authority/searchRole.jsp" style="text-decoration: none;" onclick="casetab()" target="content">
										<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;角色管理</h5></a>
									</li>
									<li style="margin: -5 0 -10 0">
										<a href="wx/authority/searchAuthority.jsp" style="text-decoration: none;" onclick="casetab()" target="content">
										<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权限管理</h5></a>
									</li>
								 	<li class="divider"></li>
									
									<li  id="list3" style="border-bottom: 1 solid #CCCCCC">	
										<a href="wx/Tab3Main.jsp" onclick="casetab()" target="content">
										<h4><span class="icon-user-md " ></span>&nbsp;多系统协作管理</h4></a>
									</li>

									<li style="margin: -5 0 -10 0">
										<a href="/CBR/selectline.jsp" style="text-decoration: none;" onclick="casetab()" target="content">
											<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;与决策系统协作</h5></a>
									</li>
									<li style="margin: -5 0 -10 0">
										<a href="/CBR/reviewcase.jsp" style="text-decoration: none;" onclick="casetab()" target="content">
										<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;与质量判定系统协作</h5></a>
									</li>
									<li style="margin: -5 0 -10 0">
										<a href="/CBR/reviewcase.jsp" style="text-decoration: none;" onclick="casetab()" target="content">
										<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;与设备运行状态监测系统协作</h5></a>
									</li>
								 	<li class="divider"></li>
										
									<li  id="list3" style="border-bottom: 1 solid #CCCCCC">	
										<a href="wx/Tab2Main.jsp" onclick="productlinetab()" target="content">
										<h4><span class="icon-user-md " ></span>&nbsp;消息管理</h4></a>
									</li>

								 	<li class="divider"></li>	
									
									<li  id="list3" style="border-bottom: 1 solid #CCCCCC">	
										<a href="wx/Tab2Main.jsp" onclick="productlinetab()" target="content">
										<h4><span class="icon-user-md " ></span>&nbsp;公告管理</h4></a>
									</li>

									<li style="margin: -5 0 -10 0">
										<a href="/CBR/selectline.jsp" style="text-decoration: none;" onclick="productlinetab()" target="content">
											<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布公告</h5></a>
									</li>
									<li style="margin: -5 0 -10 0">
										<a href="/CBR/reviewcase.jsp" style="text-decoration: none;" onclick="productlinetab()" target="content">
										<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已发公告</h5></a>
									</li>
								 	<li class="divider"></li>
										
									<li  id="list3" style="border-bottom: 1 solid #CCCCCC">	
										<a href="wx/Tab3Main.jsp" onclick="casetab()" target="content">
										<h4><span class="icon-user-md " ></span>&nbsp;日志与审计</h4></a>
									</li>

									<li style="margin: -5 0 -10 0">
										<a href="/CBR/selectline.jsp" style="text-decoration: none;" onclick="casetab()" target="content">
											<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日志管理</h5></a>
									</li>
									<li style="margin: -5 0 -10 0">
										<a href="/CBR/reviewcase.jsp" style="text-decoration: none;" onclick="casetab()" target="content">
										<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;审计管理</h5></a>
									</li>
								 	<li class="divider"></li>	
										
					
									</ul>
									</div>
								</div>
								
								
							
								<div class="span9"  id="mainContent"  style="border-left: 1 solid #CCCCCC;margin:  -10 0 10 10;">
								<div style="margin: 0 0 0 20px;">
								<div>
								<ul class="nav nav-tabs" style="width: 100%;">
								  <li class="active" id="tab_a1">
								    <a>主控系统介绍</a>
								  </li>
								  <li id="tab_2"><a >基于角色访问控制</a></li>
								  <li id="tab_3"><a >公告与消息</a></li>
								</ul>
																
								
								</div>
								<iframe  src="wx/iframeMain.jsp" name="content" id="contentFrame" scrolling="yes" frameborder="0" height="1000px" width="900px" >

									</iframe>
									</div>
								</div>												
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="span12" align="center" style="border-top: 1 solid #CCCCCC;margin-left: 0" >
		<span style="font-size: 14px;" >京ICP备11008151号 &nbsp;&nbsp;&nbsp;联系我们:029-888888888</span>
		</div >
		
		</div>
		
		
	</div>
</div>


   <!-- test -->

  
   
  </body>
</html>
