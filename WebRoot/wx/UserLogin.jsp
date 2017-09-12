<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<html>

  <head>
		<title>管理员登录</title>
		 <link href="../CSS/main.css" rel="stylesheet">
		<script src="../js/createXMLHTTP.js" language="JavaScript"
		type="text/javascript"></script>
<script language=javascript>
/* var xmlHttp;
	function check()
	{		
		name=userLogin.username.value;  //判断唯一性
		pwd=userLogin.pwd.value;
	    createXMLHTTP();
		xmlHttp.open("GET","../user/validatelogin.action?"+encodeURI("username="+name)+"&pwd="+pwd+"&t="+Math.random(), false);//设置请求信息头
		xmlHttp.send(null); //发送请求
		var gtext=xmlHttp.responseText;
		if(gtext.indexOf("usernameNull")!=-1)
	    {
	    	
	    	alert("用户名不能为空！");
	    	userLogin.username.focus();
	    	userLogin.username.value="";
	    	return;
	    }
	    if(gtext.indexOf("pwdNull")!=-1)
	    {
	    	alert("密码不能为空！");
	    	userLogin.pwd.focus();
	    	userLogin.pwd.value="";
	    	return;
	    }    	
	    if(gtext.indexOf("notExist")!=-1)
	    {
	    	
	    	alert("用户名不存在！请重新输入");
	    	userLogin.username.focus();
	    	userLogin.username.value="";
	    	return;
	    }
	    if(gtext.indexOf("notMatch")!=-1)
	    {
	    	alert("密码错误！请重新输入");
	    	userLogin.pwd.focus();
	    	userLogin.pwd.value="";
	    	return;
	    }
		userLogin.submit();
	} */
</script>
  </head>

<body >
<div class="login">
<div class="content-login">
	<% 
		String alter = (String)request.getAttribute("alter");
		session.setAttribute("systabs", "1");
	%>
	<p align="center" >请登录</p>
<form name="userLogin" action="../User/login.action" method="post">
	<TABLE align="center" border=1 rules=none>
		   <TR>
		   <TD>用户名：</TD>
		   <TD>
		   		<input name="username" type="text" id="username" size="22" />
		   </TD>
		   </tr>
		   <TR>
		   <TD>密      码：</TD>
		   <TD>
		   		<input name="pwd" type="password" id="pwd" size="22" />
		   </TD>
		   </tr>
		   <tr>
		   <td>&nbsp;</td>
		   <TD align="center">
		   		<input type="submit" value="登  录" >
		   </TD>
		   </TR>
		</TABLE>
  </form>
  <p>&nbsp;</p>
</div>
</div>
</body>
</html>
