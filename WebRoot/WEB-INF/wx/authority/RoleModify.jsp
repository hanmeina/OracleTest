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
  <link rel="stylesheet" href="css/bootstrap3.css" rel="stylesheet"></link>
</head>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
function checkName(i){
	var v = i.validity;
	var name = $("input[name='rolename']").val();//获取输入的name值
	if(true == v.patternMismatch){
        i.setCustomValidity("角色名由小于10位的汉字组成！");
        return;
    }else{
    	i.setCustomValidity("");
    } 
	$.post("Role/checkRoleName.action",  
                    {rolename:  name
                    },  
                    function(data){  
                    	//alert(data);                                                                             
                        var str=""; //提示信息
                        if(data==1){
                        	str="角色名已存在！";
                        }
                        i.setCustomValidity(str);
                                                     
                    });
}    
</script> 
  <body>
  
  
	<div >
	<center>修改角色</center><br><br>
	<form class="form-horizontal" role="form" action="Role/modifyRole.action" method="post">
  	<input type="hidden" name="roleid" value="<c:out value='${requestScope.role.roleid}'/>">
  <div class="form-group">
    <label class="col-sm-2 control-label">角色名：</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" name="rolename" value="<c:out value='${requestScope.role.rolename}'/>" onchange="checkName(this)" pattern="[\u4e00-\u9fa5]{1,10}" placeholder="角色名由小于10位的汉字组成！" required autofocus>
    </div>
  </div>
  <center><button type="submit" class="btn btn-default">保存</button></center>
</form>
	
	
	
	
 	


	
</div>
<script type="text/javascript" src="js/bootstrap-typeahead.js"></script>
</body>
</html>
