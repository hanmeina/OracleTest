<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  	function checkPasswords() {
        var pass1 = document.getElementById("password1");
        var pass2 = document.getElementById("password2");
 
        if (pass1.value != pass2.value)
            pass1.setCustomValidity("两次输入的密码不匹配");
        else
            pass1.setCustomValidity("");
    }
    
    function out(i){
    var v = i.validity;
    if(true === v.valueMessing){
        i.setCustomValidity("请填写些字段");
    }else{
        if(true === v.patternMismatch){
            i.setCustomValidity("请输入4位数字的代码");
        }else{
            i.setCustomValidity("");
        }
    }
}
  </script>
  
  <body>

    <form name="register1" id="register1">
          <p><label for="runnername">RunnerName:</label>
             <input id="runnername"name="runnername" type="text" placeholder="First and last name" required autofocus/>
          </p>
          <p><label for="phone">Tel #:</label>
             <input id="phone" name="phone" type="text" pattern="\d{3}-\d{4}-\d{4}"
                    placeholder="xxx-xxxx-xxxx"/></p>
          <p><label for="emailaddress">E-mail:</label>
             <input id="emailaddress" name="emailaddress" type="email" 
                    placeholder="For confirmation only"/></p>
          <p><label for="dob">DOB:</label>
             <input id="dob" name="dob" type="date"
                    placeholder="MM/DD/YYYY"/></p>
          <p>Count:<input type="number" id="count" name="count" min="0" max="100" step="10"/></p>
          <p><label for="style">Shirt style:</label>
               <input id="style" name="style" type="text" list="stylelist" title="Years of participation" 
                autocomplete="off"/></p>
            <datalist id="stylelist">
             <option value="White" label="1st Year"/>
             <option value="Gray" label="2nd - 4th Year"/>
             <option value="Navy" label="Veteran (5+ Years)"/>
            </datalist>
            
           <p><label for="password1">New Password:</label>
		    <input type="password" id="password1" onchange="checkPasswords()"></p>
		    <p><label for="password2">Confirm Password:</label>
		    <input type="password" id="password2" onchange="checkPasswords()"></p>
           
         <fieldset>
            <legend>Expectations:</legend>
            <p>
            <label for="confidence">Confidence:</label>
            <input id="confidence" name="level" type="range"
                   onchange="setConfidence(this.value)"
                   min="0" max="100" step="5" value="0"/>
            <span id="confidenceDisplay">0%</span></p>
            <p><label for="notes">Notes:</label>
               <textarea id="notes" name="notes" maxLength="100"></textarea></p>
         </fieldset>
         
         <input type="text" required pattern="^d{4}$" oninput="out(this)" placeholder="请输入代码" >
 
         <p><input type="submit" name="register" value="Submit" /></p>
        </form>
  </body>
</html>
