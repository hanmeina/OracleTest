<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP 2222page. <br>
    <%
	List<String> list=new ArrayList<String>();    //创建List集合的对象
	list.add("简单是可靠的先决条件");                //向List集合中添加元素
	list.add("兴趣是最好的老师");
	list.add("知识上的投资总能得到最好的回报");
	request.setAttribute("list",list);            //将List集合保存到request对象中
	%>
	<b>遍历List集合的全部元素：</b><br>
	
	<c:forEach items="${requestScope.list}" var="keyword" varStatus="status">
	    ${status.index}&nbsp;${keyword}<br>
	</c:forEach>
	<b>遍历List集合中第1个元素以后的元素（不包括第1个元素）：</b><br>
	<c:forEach items="${requestScope.list}" var="keyword" varStatus="status" begin="1">
	    ${status.index}&nbsp;${keyword}<br>
	</c:forEach>
  </body>
</html>
