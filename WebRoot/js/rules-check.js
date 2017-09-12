$(function() {
	var isUsedFlag = "${used}";// //后台传入的标记位
	console.log("是否被使用（1表示被使用，0表示未被使用）：" + isUsedFlag);
	if (isUsedFlag === 0) {
		$("#used").hide();
		$("#notUsed").show();
		$(".header")
				.html(
						"&nbsp;<span class='glyphicon glyphicon-pencil'></span>&nbsp;&nbsp;规则修改");//更换标题上的文字
	} else {
		$("#notUsed").hide();
		$("#used").show();
		$(".header")
				.html(
						"&nbsp;<span class='glyphicon glyphicon-exclamation-sign'></span>&nbsp;&nbsp;规则修改警告");
	}
    
	// 使用jQuery Form插件实现无刷新提交表单
	$("#myForm").ajaxForm(function() {
		$("#myModal").modal("show");
	});
});