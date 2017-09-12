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
	$("a.click").click(function () {  //单击事件
                  var id = $(this).attr("id");//1. 获得a标签id（首页为例：1）
                  //2. 切换到首页对应的标签tab_1中
                  //   根据获得的id拼接字符串得到相应tab的id串，注意tab的id命名规则
                  var tabid = "tab_"+id;
                  var $tab = $("#"+tabid);//3. 获取相应tab，注意选择器中含有变量的写法
                  var activeId = $("li.active").eq(0).attr("id"); //获取当前被激活的tab
                  document.getElementById(activeId).className=""; //4. 将当前激活的tab设为不激活
                  $tab.removeClass().addClass("active");  //5. 激活点击菜单对应的tab   
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
  <!-- <div class="navbar">
  <div class="navbar-inner" >
    <a class="brand" href="#" style="margin-left: 280px;">连铸质量保证主控系统</a>
    <ul class="nav">
      <li class="active"><a href="#">CBR</a></li>
      <li><a href="#">MBR</a></li>
      <li><a href="#">RBR</a></li>
    </ul>
    
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
</div> -->
  
  
   <div class="container-fluid" style="margin-left: 300px;"><!-- border: 1 solid red;s -->
	<div class="row-fluid" >
		<div class="span9" >
			<div class="row-fluid" >
				<div class="span12" >
					<div class="page-header" style="width: 1160px;">
							<img alt="140x140" src="img/logo.png" />
							<span style="margin: 0 0 0 400;"><!-- 消息(1)| -->
							<c:out value="${sessionScope.roleName}" /> |
							<c:out value="${sessionScope.userName}" /> 
							<a>注销</a></span>
						
						
						
					</div>
					<div class="row-fluid" >
						<div class="span12">
							<div class="row-fluid">
								<div  class="span3"  style="border: 1 solid #CCCCCC;margin:  -10 0 0 0 ">
								<div style="margin: 10 10 0 10px" >
									<ul class="nav nav-list" >
									
									
									 <li  id="list1">
											<a id="a1" class="click" href="/wx/iframeMain.jsp" target="content">
											<h4><span class="icon-home icon-1x " ></span>&nbsp;首页</h4></a>
									</li>
									<li class="divider">
									</li> 
									
									<!-- 开始 -->
									<!-- 定义标记变量，作为a标签的id（直接用c:forEach标签的varStatus属性获取遍历到的计数序号即可）-->
									<!-- <c:set var="index" value="1"></c:set> -->
									<c:forEach var="item" items="${sessionScope.mapAuthority}" varStatus="status">
										<!-- 获取父菜单 -->
										<li  id="list3" style="border-bottom: 1 solid #CCCCCC">	
												<a id="${status.count}" class="click" 
														href="${item.key.menuurl}" target="content">
												<h4><span class="icon-user-md " ></span>
													&nbsp;<c:out value="${item.key.name}"/>
												</h4></a>
										</li>
										<!-- 获取子菜单 -->
										<c:forEach var="authority" items="${item.value}" >	
											<li style="margin: -5 0 -10 0">
												<a id="${status.count}" class="click" target="content" 
														style="oration: none;" href="${authority.menuurl}">
													<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<c:out value="${authority.name}" default="wang"/>
													</h5>
												</a>
											</li>
										</c:forEach>
										<!--标记变量自增 -->
										<%-- <c:set var="index" value="${index + 1}"></c:set> --%>
										<li class="divider"></li>
									</c:forEach>
									<!-- 结束 -->
								
									
									<!-- <li  id="list3" style="border-bottom: 1 solid #CCCCCC">	
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

									<li style="margin: -5 0 -10 0">
										<a href="wx/message/receivedMessage.jsp" style="text-decoration: none;" onclick="productlinetab()" target="content">
											<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已收消息</h5></a>
									</li>
									<li style="margin: -5 0 -10 0">
										<a href="/CBR/reviewcase.jsp" style="text-decoration: none;" onclick="productlinetab()" target="content">
										<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已发消息</h5></a>
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
								 	<li class="divider"></li> -->	
										
					
									</ul>
									</div>
								</div>
								
								
							
								<div class="span9"  id="mainContent"  style="border-left: 1 solid #CCCCCC;margin:  -10 0 10 10;">
								<div style="margin: 0 0 0 20px;">
								<div>
								<!-- 保存首页对应标签下的内容，不同子系统的对应链接不同 -->
								<c:set var="firstUrl" value=""></c:set>
								<ul class="nav nav-tabs" style="width: 100%;">
								<c:forEach var="item" items="${sessionScope.mapAuthority}" varStatus="status">
									<!-- 标记当前标签是否选中 -->
									<c:set var="isActive" value=""></c:set>
									<!-- 默认只选中第一个标签 -->
									<c:if test="${status.count==1}">
										<c:set var="isActive" value="active"></c:set>
										<!-- 保存首页标签对应内容链接 -->
										<c:set var="firstUrl" value="${item.key.menuurl}"></c:set>
									</c:if>
									<li class="${isActive}" id="tab_${status.count}">
								    	<a><c:out value="${item.key.name}"/></a>
								  	</li>
								</c:forEach>
								</ul>
								</div>
								<!-- 默认显示首页标签下的内容 -->
								<iframe  src="${firstUrl}" name="content" id="contentFrame" 
									scrolling="yes" frameborder="0" height="1000px" width="900px" >
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
