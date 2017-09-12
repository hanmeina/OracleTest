$(document).ready(function() {

	/**
	 * 勾选checkbox
	 */
	$("[type=checkbox]").change(function() {
		$('[exampletr]').remove();
		checkboxShowValue(this);
	});
	
	//快速推理
	$('#btn1').click(function() {
		var formTable = document.getElementById("form"); 
		var id_array = new Array();  
		$('input[type="checkbox"]:checked').each(function(){  
		    id_array.push($(this).attr('meta-id'));//向数组中添加元素id  
		});  
		var idstr = id_array.join(',');//将数组元素连接起来以构建一个字符串
		formTable.idstr.value = idstr;
		if(idstr === ""){
			$('#leftErrorModal').modal('show');
			return false;
		}
		else{
			return true;
		}
		
		formTable.submit();
	});
	
	//高级推理
	$('#btn2').click(function() {
		var formTable = document.getElementById("form"); 
		var id_array=new Array();  
		$('input[type="checkbox"]:checked').each(function(){  
		    id_array.push($(this).attr('meta-id'));//向数组中添加元素id  
		});  
		
		var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串
		formTable.idstr.value = idstr;
		if(idstr == ""){
			$('#leftErrorModal').modal('show');
			return false;
		}else{
			return true;
		}
		formTable.submit();
	});
	
	
	
});





/**
 * 判断checkbox是否选中
 * 
 * @param {element}
 *            element 
 */
function checkboxShowValue(element) {
	console.log(element);
	//选中
	if (element.checked === true) {
		addCheckedNameAndIdToTable(element.getAttribute("meta-id"),
				element.value);
	}
	//未选中
	if (element.checked === false) {
		removeTrInTable(element.getAttribute("meta-id"));
	}
}

/**
 * 给表中添加选中的质量状态信息
 * 
 * @param {String}
 *            name 名称
 * @param {String}
 *            id 
 */
function addCheckedNameAndIdToTable(id, name) {
	$('tbody').append(
			'<tr metaid-intable="' + id + '"><td>' + name + '</td></tr>');
}

/**
 * 根据ID从表中移除一条记录
 * 
 * @param {String}
 *            id ´«ÈëµÄid
 */
function removeTrInTable(id) {
	$('[metaid-intable=' + id + ']').remove();
}
