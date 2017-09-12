<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Big Tree</title>
  
    <link href="css/tree.css" rel="stylesheet" type="text/css" />
    <link href="sample-css/page.css" rel="stylesheet" type="text/css" />
    
    <style type="text/css">
    body {
		color:#000; /* MAIN BODY TEXT COLOR */
		font-family:"Lucida Grande","Lucida Sans Unicode",Arial,Verdana,sans-serif; /* MAIN BODY FONTS */
		}
		.demo{
      float:left;
      width:260px;
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
    <script src="../highlighter/scripts/shCore.js" type="text/javascript"></script>
    <script src="../highlighter/scripts/shBrushJScript.js" type="text/javascript"></script>
    <script src="../highlighter/scripts/shBrushCss.js" type="text/javascript"></script>  
    <link href="../highlighter/styles/shCore.css" rel="stylesheet" type="text/css" />
    <link href="../highlighter/styles/shThemeDefault.css" rel="stylesheet" type="text/css" /> 
    <script type="text/javascript">
        SyntaxHighlighter.config.clipboardSwf = '../highlighter/scripts/clipboard.swf';
    	  SyntaxHighlighter.all();
	  </script>
    <!--end code highlighter-->
</head>
<body>
  <div style="padding:10px;"> 
  <div class="demo">
    <div style="margin-bottom:5px;">
        <a class="button" href="javascript:void(0);" id="showchecked">Get Selected Nodes</a>
        <a class="button" href="javascript:void(0);" id="showcurrent">Get Current Node</a>
    </div>
    <div style="border-bottom: #c3daf9 1px solid; border-left: #c3daf9 1px solid; width: 250px; height: 500px; overflow: auto; border-top: #c3daf9 1px solid; border-right: #c3daf9 1px solid;">
        <div id="tree">
            
        </div>
        
    </div>
  </div>
  <div class="docs">
    <fieldset>
      <legend>Huge Data Tree</legend>
      This demo show performance of a tree with 10,000 node loaded in one time.<br>
      You can expand/collapse and check/uncheked nodes to see how quickly it response.<br>
    </fieldset>
    
    
     <fieldset>
      <legend>Get Checked Nodes</legend>
      The following lines show how to get checked nodes.
       <pre class="brush:js;">
          $("#showchecked").click(function(e){
                var s=$("#tree").getCheckedNodes();
                if(s !=null)
                alert(s.join(","));
                else
                alert("NULL");
            });
        </pre>
    </fieldset>
    
     <fieldset>
      <legend>Get Current Node</legend>
      The following lines show how to get current node.
       <pre class="brush:js;">
            $("#showcurrent").click(function(e){
                var s=$("#tree").getCurrentNode();
                if(s !=null)
                    alert(s.text);
                else
                    alert("Current node is nothing");
             });
        </pre>
    </fieldset>
  </div>
  </div>
    <script src="src/jquery.js" type="text/javascript"></script>
    <script src="src/Plugins/jquery.tree.js" type="text/javascript"></script>
    <script src="data/tree1.js" type="text/javascript"></script>
    <script type="text/javascript">
         var userAgent = window.navigator.userAgent.toLowerCase();
        $.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);
        $.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);
        $.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie && /msie 6\.0/i.test(userAgent);
        function load() {        
            var o = { showcheck: true
            //onnodeclick:function(item){alert(item.text);},        
            };
            o.data = treedata;                  
            $("#tree").treeview(o);            
            $("#showchecked").click(function(e){
                var s=$("#tree").getCheckedNodes();
                if(s !=null)
                alert(s.join(","));
                else
                alert("NULL");
            });
             $("#showcurrent").click(function(e){
                var s=$("#tree").getCurrentNode();
                if(s !=null)
                    alert(s.text);
                else
                    alert("NULL");
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