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
  <link rel="stylesheet" href="css/bootstrap3.css" rel="stylesheet"></link>
</head>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
/* $(document).ready(function(){
     $("input[name='username']").focus();
}); */
function checkName(i){
	var v = i.validity;
	/* if(true === v.valueMessing){
        i.setCustomValidity("请填写些字段");
    } */
	//alert($("input[name='username']").val());
	//var $input = $(window.event.srcElement); //获取DOM事件对象
	//alert("1:"+$input.val());
	var name = $("input[name='username']").val();//获取输入的name值
	if(true == v.patternMismatch){
        i.setCustomValidity("用户名由小于20位的数字或英文字母组成！");
        return;
    }else{
    	i.setCustomValidity("");
    } 
	/* if(name==""){
		$input.parent().next().html("<font color='red'>用户名不能为空！</font>");
		$("input[name='username']").focus();
		return;
	} */
	$.post("User/checkUserName.action",  
                    {username:  name
                    },  
                    function(data){                                                                               
                        //$("#sum").replaceWith('<span id="sum">'+ data + '</span>'); 
                        //alert(data); 
                        var str=""; //提示信息
                        if(data==1){
                        	str="用户名已存在！";
                        	//$("input[name='username']").focus();
                        }/* else{
                        	//$("input[name='pwd']").focus();
                        } */
                        //$input.parent().next().html("<font color='red'>"+str+"</font>");
                        i.setCustomValidity(str);
                                                     
                    });
}  
function checkPwd(){
	var str=""; //提示信息
	var pwdDOM = $("input[name='pwd']")[0];
	var v = pwdDOM.validity;
	if(true == v.patternMismatch){
        pwdDOM.setCustomValidity("密码由6到20位的数字或英文字母组成！");
        return;
    }else{
    	pwdDOM.setCustomValidity("");
    }
	
	var pwd = $("input[name='pwd']").val();//获取输入的repwd值
	var repwd = $("input[name='repwd']").val();//获取输入的repwd值
	if (pwd != repwd)
    	str = "两次输入的密码不匹配";
    pwdDOM.setCustomValidity(str);
        
}
</script> 
  <body>
  
  
	<div>
	<center>添加用户</center><br><br>
	<form class="form-horizontal" role="form" action="User/addUser.action" method="post">
  <div class="form-group">
    <label class="col-sm-2 control-label">用户名：</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" name="username" onchange="checkName(this)" pattern="[A-Za-z0-9]{1,20}" placeholder="用户名由小于20位的数字或英文字母组成" required autofocus>
    </div>
    <!-- 保存提示信息的标签 -->
    <div></div>
  </div>
  <div class="form-group">
    <label for="inputPassword" class="col-sm-2 control-label">密码：</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" name="pwd" onchange="checkPwd()" pattern="[A-Za-z0-9]{6,20}" placeholder="密码由6到20位的数字或英文字母组成" required>
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword" class="col-sm-2 control-label">再次输入密码：</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" name="repwd" onchange="checkPwd()" required>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">角色：</label>
    <div class="col-sm-6">
      <select class="form-control" name="roleid">
      	<c:forEach var="role" items="${requestScope.roleList}" >
      		<option value='<c:out value="${role.roleid}"/>'><c:out value="${role.rolename}"/></option>
      	</c:forEach>
      </select>
    </div>
  </div>
  <center><button type="submit" class="btn btn-default">保存</button></center>
</form>
	
	
	
 	


	
</div>
<script type="text/javascript" src="js/bootstrap-typeahead.js"></script>
</body>
</html>
