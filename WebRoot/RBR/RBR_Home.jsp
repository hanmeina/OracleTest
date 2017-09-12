<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>规则推理子系统</title>
<!-- Bootstrap -->

<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/index.css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

<style type="text/css">
.NavBg {
	background: #E6E6FA;
}

.nav-tabs>li.active>a,.nav-tabs>li.active>a:hover,.nav-tabs>li.active>a:focus
	{ /* background-color: #ECF0F1; */
	
}

hr {
	margin: 15px 0;
}
</style>

<script type="text/javascript">
	$(function() {
		/* 	//鼠标滑过左边导航，背景高亮
		 $("#guide_ul li").hover(function(){
		 $(this).addClass("NavBg");
		 },function(){
		 $(this).removeClass("NavBg");
		 }); */

		//左边导航默认载入的时候首页高亮
		$("#homeNav").addClass("NavBg");
			
	
		//点击左边导航，Tab跟着变换
		$("#homeNav").click(function() {
			$("#li:eq(0)").tab('show');
			$("#homeNav").parent().children().removeClass("NavBg");
			$(this).addClass("NavBg");	
		});
		
		$("#homeNav~li:lt(5)").click(function() {
			$('#myTab li:eq(1) a').tab('show');
			$("#homeNav").parent().children().removeClass("NavBg");
			$(this).addClass("NavBg");
		});

		$("#homeNav~li:gt(4):lt(6)").click(function() {
			$('#myTab li:eq(2) a').tab('show');
			$("#homeNav").parent().children().removeClass("NavBg");
			$(this).addClass("NavBg");
		});

		$("#homeNav~li:gt(10):lt(7)").click(function() {
			$('#myTab li:eq(3) a').tab('show');
			$("#homeNav").parent().children().removeClass("NavBg");
			$(this).addClass("NavBg");
		});
		$("#homeNav~li:gt(13):lt(3)").click(function() {
			$('#myTab li:eq(4) a').tab('show');
			$("#homeNav").parent().children().removeClass("NavBg");
			$(this).addClass("NavBg");
		});
		$("#homeNav~li:gt(16):lt(3)").click(function() {
			$('#myTab li:eq(5) a').tab('show');
			$("#homeNav").parent().children().removeClass("NavBg");
			$(this).addClass("NavBg");
		});
		//$("#homeNav~li:last").click(function() {
		$("#homeNav~li:gt(19):lt(2)").click(function() {
			$('#myTab li:eq(6) a').tab('show');
			$("#homeNav").parent().children().removeClass("NavBg");
			$(this).addClass("NavBg");
		});

		//点击Tab，左边导航跟着变换，同时改变iframe中的内容
		$("#myTab a:first").click(function() {
			$("#homeNav").parent().children().removeClass("NavBg");
			$("#homeNav").addClass("NavBg");
			$("#iframe").attr("src", "intro/RBRintroduce.action");
		});
		$("#myTab a:eq(1)").click(function() {
			$("#homeNav").parent().children().removeClass("NavBg");
			$("#homeNav~li:eq(0)").addClass("NavBg");
			$("#iframe").attr("src", "metaData/tab.action");
		});
		$("#myTab a:eq(2)").click(function() {
			$("#homeNav").parent().children().removeClass("NavBg");
			$("#homeNav~li:eq(5)").addClass("NavBg");
			$("#iframe").attr("src", "rules/tab.action");
		});
		$("#myTab a:eq(3)").click(function() {
			$("#homeNav").parent().children().removeClass("NavBg");
			$("#homeNav~li:eq(11)").addClass("NavBg");
			$("#iframe").attr("src", "reasoning/tab.action");
		});
		$("#myTab a:eq(4)").click(function() {
			$("#homeNav").parent().children().removeClass("NavBg");
			$("#homeNav~li:eq(14)").addClass("NavBg");
			$("#iframe").attr("src", "myCenter/tab.action");
		});
		$("#myTab a:eq(5)").click(function() {
			$("#homeNav").parent().children().removeClass("NavBg");
			$("#homeNav~li:eq(17)").addClass("NavBg");
			$("#iframe").attr("src", "message/tab.action");
		});
		$("#myTab a:eq(6)").click(function() {
			$("#homeNav").parent().children().removeClass("NavBg");
			$("#homeNav~li:eq(20)").addClass("NavBg");
			$("#iframe").attr("src", "log/logMessage.action");
		});
	});
</script>

</head>
 
<body>

	<!-- 栅格布局 -->
	<div class="container">

		<div id="header" class="row">

			<div class="span12">
				<img alt="" src="RBR/pic/top_02.jpg"> <span
					style="margin: 0 0 0 400;"><a href="#">消息 (1)</a> | 专家 |
					Name <a>返回首页</a> </span>
			</div>

		</div>

		<hr class="divider">

		<!-- context -->
		<div class="row">

			<!-- nav list -->
			<div id="left" class="col-xs-2">
				<ul id="guide_ul" >
					<li id="homeNav" class="eneditable_li"><a
						href="intro/RBRintroduce.action" target="contentFrame"><span
							class="glyphicon glyphicon-home"></span> 首页 </a>
					</li>
					<!-- 					<li class="guide_words_pos"><a  -->
					<!-- 						href="intro/RBRTree" target="contentFrame"> 树形结构</a> -->
					<!-- 					</li> -->
					<!-- <li class="guide_words_pos"><a
						href="intro/RBRechart.action" target="contentFrame">查看</a>
					</li> -->
					<hr>
					<li id="metaDataManageNav" class="eneditable_li"><a
						href="metaData/tab.action" target="contentFrame"> <span
							class="glyphicon glyphicon-folder-close"></span> 元数据管理 </a>
					</li>
					<li class="guide_words_pos"><a
						href="metaData/queryMetaData.action" target="contentFrame">查看</a>
					</li>
					<li class="guide_words_pos"><a
						href="metaData/add.action?scrollY=0" target="contentFrame">增加</a>
					</li>
					<li class="guide_words_pos"><a href="metaData/change.action"
						target="contentFrame">修改</a>
					</li>
					<li class="guide_words_pos"><a href="metaData/delete.action"
						target="contentFrame">删除</a>
					</li>
					<hr>
					<li class="eneditable_li"><a href="rules/tab.action"
						target="contentFrame"> <span
							class="glyphicon glyphicon-folder-close"></span> 规则管理 </a>
					</li>
					<li class="guide_words_pos"><a href="rules/query.action"
						target="contentFrame">查看</a>
					</li>
					<li class="guide_words_pos"><a href="rules/add.action"
						target="contentFrame">增加</a>
					</li>
					<li class="guide_words_pos"><a href="rules/change.action"
						target="contentFrame">修改</a>
					</li>
					<li class="guide_words_pos"><a href="rules/delete.action"
						target="contentFrame">删除</a>
					</li>
					<li class="guide_words_pos"><a href="rules/exam.action"
						target="contentFrame">审核</a>
					</li>
					<hr>
					<li class="eneditable_li"><a href="reasoning/tab.action"
						target="contentFrame"><span class="glyphicon glyphicon-stats"></span>
							基于规则推理 </a>
					</li>
					<li class="guide_words_pos"><a href="reasoning/selectFastQualityCondition.action"
						target="contentFrame">快速推理</a>
					</li>
					<li class="guide_words_pos"><a href="reasoning/selectQualityCondition.action"
						target="contentFrame">高级推理</a>
					</li>
					<hr>
					<li class="eneditable_li"><a href="rules/tab.action"
						target="contentFrame"> <span class="glyphicon glyphicon-user"></span>
							个人中心 </a>
					</li>

					<li class="guide_words_pos"><a href="myCenter/metaData.action"
						target="contentFrame">我的元数据</a>
					</li>

					<li class="guide_words_pos "><a href="#systemSetting"
						class="nav-header collapsed" data-toggle="collapse">我的规则<span
							class="caret"></span> </a>
						<ul id="systemSetting" class="nav nav-list collapse secondmenu">
							<li class="guide_words_pos"><a
								href="myCenter/passedRules.action" target="contentFrame">通过</a>
							</li>
							<li class="guide_words_pos"><a
								href="myCenter/notpassRules.action" target="contentFrame">未通过</a>
							</li>
							<li class="guide_words_pos"><a
								href="myCenter/sleepRules.action" target="contentFrame">休眠</a>
							</li>
							<li class="guide_words_pos"><a
								href="myCenter/pendingRules.action" target="contentFrame">待审核</a>
							</li>
						</ul></li>
					<hr>
					<li class="eneditable_li"><a href="message/tab.action"
						target="contentFrame"> <span
							class="glyphicon glyphicon-envelope"></span> 消息管理 </a>
					</li>
					<li class="guide_words_pos"><a href="message/receive.action"
						target="contentFrame">已收消息</a>
					</li>
					<li class="guide_words_pos"><a href="message/send.action"
						target="contentFrame">已发消息</a>
					</li>
					<hr>
					<li class="eneditable_li"><a href="log/logMessage.action"
						target="contentFrame"><span
							class="glyphicon glyphicon-calendar"></span> 日志 </a>
					</li>
					<li class="guide_words_pos"><a href="log/query.action"
						target="contentFrame">查询</a>
					</li>
					<!--  
					<li class="guide_words_pos"><a 
						href="log/showTimeLine" target="contentFrame">时间轴</a>
					</li>
					-->
				</ul>
			</div>

			<!-- tabs -->
			<div class="col-xs-10">
				<!-- Nav tabs -->
				<ul id="myTab" class="nav nav-tabs" role="tablist">
					<li class="active"><a href="#homeTab" role="tab"
						data-toggle="tab">RBR介绍</a>
					<li><a href="#metaDataTab" role="tab" data-toggle="tab">元数据管理</a>
					</li>
					<li><a href="#rulesTab" role="tab" data-toggle="tab">规则管理</a>
					</li>
					</li>
					<li><a href="#reasonalTab" role="tab" data-toggle="tab">基于规则推理</a>
					</li>
					<li><a href="#myCenterTab" role="tab" data-toggle="tab">个人中心</a>
					</li>
					<li><a href="#messageTab" role="tab" data-toggle="tab">消息管理</a>
					</li>
					<li><a href="#logsTab" role="tab" data-toggle="tab">日志记录</a>
					</li>
				</ul>

				<iframe id="iframe" src="intro/RBRintroduce.action"
					name="contentFrame" id="contentFrame" frameborder="0"
					scrolling="auto" style="overflow-x:hidden; overflow-y:auto;"
					width="100%" height="730px"></iframe>
			</div>
		</div>
		<hr>

		<!-- footer -->
		<div class="row">
			<div class="col-xs-12">
				<p class="text-center">Copyright &copy; 2016, 中国重型机械研究院股份公司</p>
			</div>
		</div>
	</div>

</body>
</html>
