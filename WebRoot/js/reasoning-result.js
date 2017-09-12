$(document).ready(function() {
	examStatus();
	
	if (treeData === "") {
		$('#leftErrorModal').modal('show');	
	}
	
	$('#btn').click(function(){
		//window.open('selectQualityCondition.action');
		//window.location.href=='selectQualityCondition.action'; 
		//window.navigate("/selectQualityCondition.action");
		window.history.back(-1); 
	});
});

