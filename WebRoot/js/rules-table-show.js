function examStatus() {
	// 审核状态从012变为，待审核，审核通过，未通过
	// 0.待审核 1.审核通过 2.审核未通过
	// 遍历表格
	var aTable = $(".table"); //  选中table，可能会有两张表格
	for ( var i = 0; i < aTable.length; i++) {
		for ( var j = 0; j < aTable[i].tBodies[0].rows.length; j++) {
			if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML === '0') { // 这里的8指的是第8列,第8列是审核状态
				aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "待审核";
			}
			if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML === '1') {
				aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "ͨ通过";
			}
			if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML === '2') {
				aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "未通过";
			}
			if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML === '3') {
				aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "休眠";
			}
			if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML === '4') {
				aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "ͨ通过";
			}
			if (aTable[i].tBodies[0].rows[j].cells[8].innerHTML === '5') {
				aTable[i].tBodies[0].rows[j].cells[8].innerHTML = "删除规则";
			}
		}
	}
}

