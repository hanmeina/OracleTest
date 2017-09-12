<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Big Tree</title>
    <link rel="stylesheet" href="../css/bootstrap3.css" rel="stylesheet"></link>
    <link href="../wdTree/wdTree/css/tree.css" rel="stylesheet" type="text/css" />
    <link href="../wdTree/wdTree/sample-css/page.css" rel="stylesheet" type="text/css" />
    
    <style type="text/css">
    body {
		color:#000; /* MAIN BODY TEXT COLOR */
		font-family:"Lucida Grande","Lucida Sans Unicode",Arial,Verdana,sans-serif; /* MAIN BODY FONTS */
		font-size: 1.8em;
		}
	.demo{
      margin:10px auto; text-align:center;
      width:360px;
    }
    .docs{
      margin-left: 265px;
    }
    a.button{
      font-size: 0.8em;
      margin-right: 4px;
    }
    </style>
    <meta charset="UTF-8" />
    
    <!--code highlighter file import-->
    <script src="../wdTree/highlighter/scripts/shCore.js" type="text/javascript"></script>
    <script src="../wdTree/highlighter/scripts/shBrushJScript.js" type="text/javascript"></script>
    <script src="../wdTree/highlighter/scripts/shBrushCss.js" type="text/javascript"></script>  
    <link href="../wdTree/highlighter/styles/shCore.css" rel="stylesheet" type="text/css" />
    <link href="../wdTree/highlighter/styles/shThemeDefault.css" rel="stylesheet" type="text/css" /> 
    <script type="text/javascript">
        SyntaxHighlighter.config.clipboardSwf = '../wdTree/highlighter/scripts/clipboard.swf';
    	SyntaxHighlighter.all();
	  </script>
    <!--end code highlighter-->
</head>
<body>
  <div style="padding:10px;"> 
  <div class="demo">
    <!-- <div style="margin-bottom:5px;">
        <a class="button" href="javascript:void(0);" id="showchecked">Get Selected Nodes</a>
        <a class="button" href="javascript:void(0);" id="showcurrent">Get Current Node</a>
    </div> -->
    <!-- wdTree在此处生成 -->
    <div style="margin-bottom:8px;text-align:left;border-bottom: #c3daf9 1px solid; border-left: #c3daf9 1px solid; width: 350px; height: 500px; overflow: auto; border-top: #c3daf9 1px solid; border-right: #c3daf9 1px solid;">
        <div id="tree" >
            
        </div>
    </div>

    <center><button id="submit" type="button" class="btn btn-default" >保存</button></center>
  </div>
  <div id="oErrorLog">
	</div>
  </div>
    <script src="../wdTree/wdTree/src/jquery.js" type="text/javascript"></script>
    <script src="../wdTree/wdTree/src/Plugins/jquery.tree.js" type="text/javascript"></script>
    <!-- <script src="../wdTree/wdTree/data/tree1.js" type="text/javascript"></script> -->
    <script type="text/javascript">
        var userAgent = window.navigator.userAgent.toLowerCase();
        $.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);
        $.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);
        $.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie && /msie 6\.0/i.test(userAgent);
		//获取后台传来的roleid并保存成全局变量
		var roleid = "<%=request.getAttribute("roleid")%>";
        //创建树，数据来自后台，先定义根节点
        function createNode(){
        	var root = {
			    "id" : "0",
			    "text" : "权限列表",
			    "value" : "86",
			    "showcheck" : false,
			    complete : true,
			    "isexpand" : true,
			    "checkstate" : 0,
			    "hasChildren" : true
			};
			//获取后台数据
			var v = "<%=request.getAttribute("js")%>";
			eval(v); //将后台传来的js代码嵌到此处执行
			root["ChildNodes"] = arr; //arr在后台js代码中定义，保存子树信息
  			return root;
        }
        
        
        //显示树
        function load() {        
            var o = { showcheck: true       
            };
            
            o.data = [createNode()]; //创建树                 
            $("#tree").treeview(o);  //显示树
             //设定保存按钮方法
             $("#submit").click(function(e){
             	var s=$("#tree").getCheckedNodes();
             	if(s==''){
             		alert("请勾选权限！");
             		return;
             	}
             	//alert(s);
             	//提交修改权限请求
             	window.location.href=
             		"modifyAuthority.action?roleid="+roleid+"&authoritys="+s;
             });
        }   
        if( $.browser.msie6)
        {
            load();
        }
        else{
            $(document).ready(load);
        }
    </script>
    
</body>
</html>