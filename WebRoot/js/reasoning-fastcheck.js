$(document).ready(function() {

	/**
	 * ¼àÌýcheckbox¸Ä±äµÄ·½·¨
	 */
	$("[type=checkbox]").change(function() {
		$('[exampletr]').remove();
		checkboxShowValue(this);
	});
	
	$('#btn').click(function() {
		var formTa = document.getElementById("formfast"); 
		var id_array=new Array();  
		$('input[type="checkbox"]:checked').each(function(){  
		    id_array.push($(this).attr('meta-id'));//向数组中添加元素id  
		});  
		var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串
		formTa.idstr.value = idstr;
		formTa.submit();
	});
	
	
});

function func() {
	var id_array=new Array();  
	$('input[type="checkbox"]:checked').each(function(){  
	    id_array.push($(this).attr('meta-id'));//向数组中添加元素id  
	});  
	var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串
//	alert(idstr);
	document.form.idstr.value=idstr;
	document.form1.submit();
}
/**
 * Ã¿Ò»¸öcheckbox¸Ä±äºóµÄ¶¯×÷
 * 
 * @param {element}
 *            element ´«ÈëµÄDOMÔªËØ
 */
function checkboxShowValue(element) {
	console.log(element);
	if (element.checked === true) {
		addCheckedNameAndIdToTable(element.getAttribute("meta-id"),
				element.value);
	}
	if (element.checked === false) {
		removeTrInTable(element.getAttribute("meta-id"));
	}
}

/**
 * ½«checkedÖÐµÄnameºÍidÌí¼Óµ½±í¸ñÖÐ
 * 
 * @param {String}
 *            name ÔªÊý¾ÝÃû
 * @param {String}
 *            id ÔªÊý¾Ýid
 */
function addCheckedNameAndIdToTable(id, name) {
	$('tbody').append(
			'<tr metaid-intable="' + id + '"><td>' + name + '</td></tr>');
}

/**
 * ¸ù¾ÝidÒÆ³ý±í¸ñÖÐµÄÒ»ÐÐ
 * 
 * @param {String}
 *            id ´«ÈëµÄid
 */
function removeTrInTable(id) {
	$('[metaid-intable=' + id + ']').remove();
}
