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

<title>My JSP 'examingRule.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/rules-exam.css">


<style type="text/css">
    /* 模态框背景 */
.modal-backdrop {
    background-color: #E6E6FA;
}
.modal-backdrop.in {
    filter: alpha(opacity = 50);
    opacity: .5;
}
</style>
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div id="header" class="col-xs-12">
				<h3 class="my-title">
					<span class="glyphicon glyphicon-list-alt text-primary"></span>
					执行审核
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<h5>
					<strong>当前待审核规则为：</strong>
				</h5>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<td>规则编号</td>
							<td>规则前件（可信度）（权重）</td>
							<td>规则后件（可信度）</td>
							<td>规则可信度</td>
							<td>创建者</td>
							<td>创建时间</td>
							<td>审核状态</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${rule.id }</td>
							<td><c:forEach items="${rule.conditionSet }"
									var="condition">
							${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
								</c:forEach></td>
							<td><c:forEach items="${rule.conclusionSet }"
									var="conclusion">
							${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
								</c:forEach></td>
							<td>${rule.reliability }</td>
							<td>${rule.userName }</td>
							<td>${rule.submitTime }</td>
							<td status>${rule.state }</td>
							<td>${rule.info }</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row section" id="containarea">
			<div class="col-xs-12">
				<h5 class="uncheck">
					<span class="glyphicon glyphicon-info-sign"></span> 
					检查条件包含规则 <span id="containboolean"></span>
				</h5>
				<button class="btn btn-default btn-xs hidden" type="button"
					data-toggle="collapse" data-target="#contain-rules"
					aria-expanded="false" aria-controls="contain-rules">查看详情
					▼</button>
				<div contain class="collapse hidden" id="contain-rules">
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
							<c:forEach items="${containListRules}" var="rule">
								<tr>
									<td>${rule.id }</td>
									<td><c:forEach items="${rule.conditionSet }"
											var="condition">
							${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
										</c:forEach></td>
									<td><c:forEach items="${rule.conclusionSet }"
											var="conclusion">
							${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
										</c:forEach></td>
									<td>${rule.reliability }</td>
									<td>${rule.userName }</td>
									<td>${rule.submitTime }</td>
									<td>${rule.managerName }</td>
									<td>${rule.passTime }</td>
									<td status>${rule.state }</td>
									<td>${rule.info }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row section" id="containedarea">
			<div class="col-xs-12">
				<h5 class="uncheck">
					<span class="glyphicon glyphicon-info-sign"></span>
					 检查条件被包含规则 <span id="containedboolean"></span>
				</h5>
				<button contained class="btn btn-default btn-xs hidden" type="button"
					data-toggle="collapse" data-target="#contained-rules"
					aria-expanded="false" aria-controls="contained-rules">查看详情
					▼</button>
				<div contained class="collapse hidden" id="contained-rules">
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
							<c:forEach items="${containedListRules}" var="rule">
								<tr>
									<td>${rule.id }</td>
									<td><c:forEach items="${rule.conditionSet }"
											var="condition">
							${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
										</c:forEach></td>
									<td><c:forEach items="${rule.conclusionSet }"
											var="conclusion">
							${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
										</c:forEach></td>
									<td>${rule.reliability }</td>
									<td>${rule.userName }</td>
									<td>${rule.submitTime }</td>
									<td>${rule.managerName }</td>
									<td>${rule.passTime }</td>
									<td status>${rule.state }</td>
									<td>${rule.info }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row section" id="deliverarea">
			<div class="col-xs-12">
				<h5 class="uncheck">
					<span class="glyphicon glyphicon-info-sign"></span>
					 检查传递性冗余 <span id="deliverduplicateboolean"></span>
				</h5>
				<button  deliver class="btn btn-default btn-xs hidden" type="button"
					data-toggle="collapse" data-target="#parse-equal-rules2"
					aria-expanded="false" aria-controls="parse-equal-rules2">查看详情
					▼</button>
				<div deliver class="collapse hidden" id="parse-equal-rules2">
					<div>相关推理链为：</div>
					<div>
						<h1>这里是一张推理链的图！</h1>
					</div>
					<div>相关规则为：</div>
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
							<c:forEach items="${deliverDuplicateRulesList}" var="rule">
                                <tr>
                                    <td>${rule.id }</td>
                                    <td><c:forEach items="${rule.conditionSet }"
                                            var="condition">
                            ${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
                                        </c:forEach></td>
                                    <td><c:forEach items="${rule.conclusionSet }"
                                            var="conclusion">
                            ${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
                                        </c:forEach></td>
                                    <td>${rule.reliability }</td>
                                    <td>${rule.userName }</td>
                                    <td>${rule.submitTime }</td>
                                    <td>${rule.managerName }</td>
                                    <td>${rule.passTime }</td>
                                    <td status>${rule.state }</td>
                                    <td>${rule.info }</td>
                                </tr>
                            </c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<div class="row section" id="conditionedarea">
			<div class="col-xs-12">
				<h5 class="uncheck">
					<span class="glyphicon glyphicon-info-sign"></span>
					 检查条件冲突 <span id="conditionedboolean"></span>
				</h5>
				<button conditioned class="btn btn-default btn-xs hidden" type="button"
					data-toggle="collapse" data-target="#conditioned-rules"
					aria-expanded="false" aria-controls="conditioned-rules">查看详情
					▼</button>
				<div conditioned class="collapse hidden" id="conditioned-rules">
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
							<c:forEach items="${conditionlist}" var="rule">
								<tr>
									<td>${rule.id }</td>
									<td><c:forEach items="${rule.conditionSet }"
											var="condition">
							${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
										</c:forEach></td>
									<td><c:forEach items="${rule.conclusionSet }"
											var="conclusion">
							${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
										</c:forEach></td>
									<td>${rule.reliability }</td>
									<td>${rule.userName }</td>
									<td>${rule.submitTime }</td>
									<td>${rule.managerName }</td>
									<td>${rule.passTime }</td>
									<td status>${rule.state }</td>
									<td>${rule.info }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<div class="row section" id="conditionarea">
			<div class="col-xs-12">
				<h5 class="uncheck">
					<span class="glyphicon glyphicon-info-sign"></span>
					 检查结论冲突 <span id="conditionboolean"></span>
				</h5>
				<button condition class="btn btn-default btn-xs hidden" type="button"
					data-toggle="collapse" data-target="#condition-rules"
					aria-expanded="false" aria-controls="condition-rules">查看详情
					▼</button>
				<div condition class="collapse hidden" id="condition-rules">
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
										</c:forEach></td>
									<td><c:forEach items="${rule.conclusionSet }"
											var="conclusion">
							${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
										</c:forEach></td>
									<td>${rule.reliability }</td>
									<td>${rule.userName }</td>
									<td>${rule.submitTime }</td>
									<td>${rule.managerName }</td>
									<td>${rule.passTime }</td>
									<td status>${rule.state }</td>
									<td>${rule.info }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
        <div class="reference text-info">参考信息：</div>
		<div class="row section" id="sameleftarea">
			<div class="col-xs-12">
				<h5 class="text-info">
					<span class="glyphicon glyphicon-info-sign"></span>
					是否有前件相同规则<span id="sameleftboolean"></span>
				</h5>
				<button class="btn btn-default btn-xs hidden" type="button"
					data-toggle="collapse" data-target="#left-equal-rules"
					aria-expanded="false" aria-controls="left-equal-rules">查看详情
					▼</button>
				<div class="collapse hidden" id="left-equal-rules">
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
							<c:forEach items="${sameLeftRules}" var="rule">
                                <tr>
                                    <td>${rule.id }</td>
                                    <td><c:forEach items="${rule.conditionSet }"
                                            var="condition">
                            ${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
                                        </c:forEach></td>
                                    <td><c:forEach items="${rule.conclusionSet }"
                                            var="conclusion">
                            ${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
                                        </c:forEach></td>
                                    <td>${rule.reliability }</td>
                                    <td>${rule.userName }</td>
                                    <td>${rule.submitTime }</td>
                                    <td>${rule.managerName }</td>
                                    <td>${rule.passTime }</td>
                                    <td status>${rule.state }</td>
                                    <td>${rule.info }</td>
                                </tr>
                            </c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row section" id="samerightarea">
			<div class="col-xs-12">
				<h5 class="text-info">
					<span class="glyphicon glyphicon-info-sign"></span>
					是否有后件相同规则<span id="samerightboolean"></span>
				</h5>
				<button sameright class="btn btn-default btn-xs hidden" type="button"
					data-toggle="collapse" data-target="#right-equal-rules"
					aria-expanded="false" aria-controls="right-equal-rules">查看详情
					▼</button>
				<div sameright class="collapse hidden" id="right-equal-rules">
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
							<c:forEach items="${sameRightRules}" var="rule">
                                <tr>
                                    <td>${rule.id }</td>
                                    <td><c:forEach items="${rule.conditionSet }"
                                            var="condition">
                            ${condition.metaData.name }（${condition.metaData.reliability }）（${condition.weight }）<br>
                                        </c:forEach></td>
                                    <td><c:forEach items="${rule.conclusionSet }"
                                            var="conclusion">
                            ${conclusion.metaData.name }（${conclusion.metaData.reliability }）<br>
                                        </c:forEach></td>
                                    <td>${rule.reliability }</td>
                                    <td>${rule.userName }</td>
                                    <td>${rule.submitTime }</td>
                                    <td>${rule.managerName }</td>
                                    <td>${rule.passTime }</td>
                                    <td status>${rule.state }</td>
                                    <td>${rule.info }</td>
                                </tr>
                            </c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row section">
			<div class="col-xs-12">
            <div class="line"></div>
				<h4>
					<span class="glyphicon glyphicon-question-sign text-primary"></span>
					是否通过审核：
				</h4>
                <a href="rules/exam.action" class="btn btn-default btn-sm">返回规则审核</a>
				<button class="btn btn-default btn-sm cancel" type="button" data-toggle="modal" data-target="#rejectModal">不通过</button>
				<button id="okbtn" class="btn btn-primary btn-sm ok" disabled type="button" data-toggle="modal" data-target="#passModal">通过</button>
			</div>
		</div>
	</div>


    <!-- rejectModal -->
    <div class="modal fade" id="rejectModal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <form action="rules/examReject.action?id=${rule.id}" method="post">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            <span class="glyphicon glyphicon-warning-sign text-warning"></span>
                            是否确认不通过？
                        </h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default cancel"
                            data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary ok">确认</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- passModal -->
    <div class="modal fade" id="passModal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel2" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <form action="rules/examPass.action?id=${rule.id}" method="post">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel2">
                            <span class="glyphicon glyphicon-warning-sign text-warning"></span>
                            是否确认通过？
                        </h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default cancel"
                            data-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary ok">确认</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script src="js/rules-table-show2.js"></script>
    <script src="js/rules-exam.js"></script>

    <script type="text/javascript">

    var containListRules = "${containListRules}";
    var containedListRules = "${containedListRules}";
    var deliverDuplicateRulesList = "${deliverDuplicateRulesList}";
    var sameLeftRules = "${sameLeftRules}";
    var sameRightRules = "${sameRightRules}";
    var hasConflict="${hasConflict}";

    console.log("传递性冗余规则后台传到页面的数据---->"+deliverDuplicateRulesList);
    console.log("冲突规则后台传到页面的数据---->"+hasConflict);
    console.log("前件相同的规则---->"+sameLeftRules);
    console.log("后件相同的规则---->"+sameRightRules);

    if (containListRules!="") {//若包含规则不为空，即没通过第一条
        console.log("未通过包含");
        containError();
    }else if(containedListRules!=""){//被包含规则不为空，即没通过第二条
        console.log("未通过被包含");
        containPass();
        containedError();
    }else if(deliverDuplicateRulesList!=""){
        console.log("未通过传递性冗余检查");
        containPass();
        containedPass();
        deliverError();
    }
    else{
        containPass();
        containedPass();
        deliverPass();
    }


    if(hasConflict!="0"){
        if(hasConflict=="2"){
        console.log("未通过条件冲突检查");
        conditionedError();
        }
        if(hasConflict=="1"){
        console.log("未通过结论冲突检查");
        conditionedPass();
        conditionError();}
    }else{
        conditionedPass();
        conditionPass();
    }

    //前件相同或后件相同规则
    if (sameLeftRules=="[]") {
        sameLeftRulesEmpty();
    } else{
        sameLeftRulesExist();
    }
    if (sameRightRules=="[]") {
        sameRightRulesEmpty();
    } else{
        sameRightRulesExist();
    };
    </script>

</body>
</html>
