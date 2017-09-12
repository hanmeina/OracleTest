<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Tree.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/reasoning-result.css">
<style type="text/css">
		.modal-backdrop {
			background-color: #E6E6FA;
		}
		.modal-backdrop.in {
			filter: alpha(opacity =   50);
			opacity: .8;
		}
		.glyphicon-warning-sign{
		color: #d9534f;
		}
		.glyphicon-ok {
		color: #468847;
		}
		</style>

<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/rules-table-show.js"></script>
<script type="text/javascript" src="js/reasoning-result.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div id="header" class="col-sm-12">
				<h3 class="my-title">
					<span class="glyphicon glyphicon-hand-right"></span> 推理结果
				</h3>
			</div>
		</div>
		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		<div id="main" style="height:350px;width:900px"></div>
		<lable for="">推理结果</lable>
		<div id="a"></div>
		<hr>
		<div class="row">
			<div class="col-md-12">
				<lable for="">使用到的规则</lable>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td>规则编号</td>
							<td>规则前件（可信度）（权重）</td>
							<td>规则后件（可信度）</td>
							<td>规则可信度</td>
							<td>创建者</td>
							<td>创建时间</td>
							<td>审核员</td>
							<td>审核时间</td>
							<td>审核状态</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${usedRulesList}" var="rule">
							<tr>
								<td>${rule.id }</td>
								<td><c:forEach items="${rule.conditionSet }"
										var="condition">
									${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
									</c:forEach>
								</td>
								<td><c:forEach items="${rule.conclusionSet }"
										var="conclusion">
									${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
									</c:forEach>
								</td>
								<td>${rule.reliability }</td>
								<td>${rule.userName }</td>
								<td>${rule.submitTime }</td>
								<td>${rule.managerName }</td>
								<td>${rule.passTime }</td>
								<td>${rule.state }</td>
								<td>${rule.info }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<!-- 模态框 -->
			<div class="modal fade" id="leftErrorModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-warning-sign"></span> 无相关规则，请前往高级推理重新推理
							</h4>
						</div>
						<div class="modal-footer">
							<button type="button" id="btn" class="btn btn-default ok" data-dismiss="modal"  onclick="func()">确定</button>
						</div>
					</div>
				</div>
			</div>



	<!-- ECharts单文件引入 -->
	<script src="echart2.2.7/echarts.js"></script>
	<script type="text/javascript">


/*仅限于绘制lineStyle为broken时使用*/
/*为了去除echarts树图的跟节点 和与跟节点相互连接线段，修改了echarts2.2.7/echart/tree文件中_buildBrokenLine: function(e, t, i)函数，使绘制树图时不绘制与根节点相连接的线段，具体修改如下*/	
/*_buildBrokenLine: function(e, t, i) {
            var a = U.clone(t);
            a.type = "solid";
            var o = [],
                r = e.layout.position[0],
                s = e.layout.position[1],
                l = i.orient,
                h = e.children[0].layout.position[1],
                m = r,
                V = s + (h - s) * (1 - n),
                d = e.children[0].layout.position[0],
                p = V,
                c = e.children[e.children.length - 1].layout.position[0],
                u = V;
            if ("horizontal" === l) {
                var y = e.children[0].layout.position[0];
                m = r + (y - r) * (1 - n), V = s, d = m, p = e.children[0].layout.position[1], c = m, u = e.children[e.children.length - 1].layout.position[1]
            }
            //此处增加了判断绘制线段是否是根节点，若要还原，删除if(this.tree.root.layout.position[0]!==r)与下面for循环中的if(this.tree.root.layout.position[0]!==r)即可
            if(this.tree.root.layout.position[0]!==r) o.push(this._getLine(r, s, m, V, a)), o.push(this._getLine(d, p, c, u, a));
            for (var g = 0; g < e.children.length; g++) if(this.tree.root.layout.position[0]!==r) y = e.children[g].layout.position[0], h = e.children[g].layout.position[1], "horizontal" === l ? p = h : d = y, o.push(this._getLine(d, p, y, h, a));
            this.shapeList = this.shapeList.concat(o)
        }*/
/*为了不显示根节点，现做法是将根节点的color修改为白色('rgba(255,255,255,255)'),将根节点的name修改为null，emphasis的color也修改为白色*/	
	
	
	    var treeData='<%=request.getAttribute("treeData")%>';
		var root = new Array();
		var json = JSON.parse(treeData);
		

		//根节点不显示
		json.name = "";
		json.itemStyle = {
			normal : {
				color : 'rgba(255,255,255,255)'
			},
			label : {
				show : false
			},
			emphasis : {
				color : 'rgba(255,255,255,255)'
			}
		};

		function setColor(json) {
			for ( var i = 0; i < json.length; i++) {
				if (json[i]) {
					var tmp = {
						normal : {
							color : "#4883b4"
						}
					};
					tmp.normal.color = json[i].color;
					json[i].itemStyle = tmp;
					setColor(json[i].children);
				}
			}
			return;
		}

		//推理结果
		function ret(json) {
			var r = [];
			for ( var i = 0; i < json.children.length; i++) {
				r[i] = json.children[i].name;
			}
			return r;
		}
		var str = ret(json);
		document.getElementById('a').innerHTML = str;

		setColor(json.children);

		root.push(json);

		// 路径配置
		require.config({
			paths : {
				echarts : 'echart2.2.7'
			}
		});

		// 使用
		require([ 'echarts', 'echarts/chart/tree' // 使用柱状图就加载bar模块，按需加载
		], function(ec) {
			// 基于准备好的dom，初始化echarts图表
			var myChart = ec.init(document.getElementById('main'));
			var option = {
				title : {
					text : '推理链展示'
				},
				toolbox : {
					show : false,
					feature : {
						mark : {
							show : false,
						},
						dataView : {
							show : false,
							readOnly : false,
						},
						restore : {
							show : false,
						},
						saveAsImage : {
							show : false,
						}
					}
				},
				series : [ {
					name : '树图',
					type : 'tree',
					orient : 'horizontal', // vertical horizontal
					//direction : 'inverse',
					rootLocation : {
						x : 900,
						y : 170
					}, // 根节点位置  {x: 100, y: 'center'}

					direction : 'inverse',
					nodePadding : 40,
					layerPadding : 225,
					hoverable : true,
					roam : true,
					symbol : 'circle',
					symbolSize : 15,
					itemStyle : {
						normal : {
							color : '#cc5555',
							label : {
								show : true,
								position : 'bottom',
								formatter : "{b}",
								textStyle : {
									color : 'rgba(0,0,0,255)',
									fontSize : 15,
									align : 'center',
									baseline : 'top'
								}
							},
							lineStyle : {
								color : '#ccc',
								width : 2,
								//color : 'rgba(237,125,49,255)',
								type : 'broken', // 'curve'|'broken'|'solid'|'dotted'|'dashed'
							},
						},
						emphasis : {

							//color : '#4883b4',
							label : {
								show : false
							},
							borderWidth : 0
						}
					},

					data : root

				} ]
			};

			// 为echarts对象加载数据 
			myChart.setOption(option);
		});
	</script>



</body>
</html>