var aTd = $("[status]");
for (var i = 0; i < aTd.length; i++) {
    switch (aTd[i].innerHTML - 0) {
        case 1:
            aTd[i].innerHTML = "审核通过";
            break;
        case 2:
            aTd[i].innerHTML = "未通过";
            break;
        case 3:
        	 aTd[i].innerHTML = "休眠";
             break;
        case 0:
            aTd[i].innerHTML = "待审核";
            break;
        case 4:
            aTd[i].innerHTML = "规则使用过";
            break;
        default:
            console.log("程序错误，审核状态错误");
            break;
    }
}