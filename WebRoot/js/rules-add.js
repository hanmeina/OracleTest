$(document).ready(function() {

    /**
     * ¼àÌýcheckbox¸Ä±äµÄ·½·¨
     */
    $("[type=checkbox]").change(function() {
        $('[exampletr]').remove();
        checkboxShowValue(this);
    });

    /**
     * ¼àÌýradio¸Ä±ä
     */
    $('[name=right]').change(function() {
        console.log("radio");
        radioShowValue(this);
    });
    
    /**
     * µã»÷Ìá½»°´Å¥Ê±µÄ¶¯×÷
     */
    $('.ok').click(function() {
        var jsonData = createJsonData();
        console.log(jsonData);
        //judge data legal
        judgeDataLegal(jsonData);
    });

    // ¹öÒ³Ãæ²ÅÏÔÊ¾·µ»Ø¶¥²¿
    $(window).scroll(function() {
        if ($(window).scrollTop() > 100) {
            $("#top").fadeIn(500);
        } else {
            $("#top").fadeOut(500);
        }
    });
    // µã»÷»Øµ½¶¥²¿
    $("#top").click(function() {
        $("body").animate({
            scrollTop: "0"
        }, 500);
    });
});

/**
 * judgeDataLegal
 * @param {json} jsonData
 */
function judgeDataLegal(jsonData) {
    console.log(jsonData);
    console.log("----------------");
    if (jsonData.leftMap === "") {
        console.log("leftMap cannot be empty");
        $('#leftErrorModal').modal('show');
    } else if (jsonData.right === -1) {
        console.log("right cannot be empty");
        $("#myModalLabel").html('<span class="glyphicon glyphicon-warning-sign"></span> 后键不能为空');
        $('#leftErrorModal').modal('show');
    } else {
        $.ajax({
            url: 'rules/addPOST.action',
            type: 'POST',
            dataType: 'json',
            data: jsonData,
        }).done(function() {
            // console.log("success");
        }).fail(function(XMLHttpRequest, textStatus, errorThrown, data) {
            // console.log("error");
            // console.log(XMLHttpRequest.status);
            // console.log(XMLHttpRequest.readyState);
            // console.log(data);
            // console.log(textStatus);
        }).always(function(data) {
            // console.log(data.statusText);
            // console.log("complete");
            if (data.statusText == "OK") {
                console.log("great");
                $(".modal-header").html('<h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-ok"></span> 添加规则成功！</h4>');
                $(".modal-footer").html('<a href="rules/add.action" class="btn btn-primary " role="button">再添加一条规则</a>');
                $('#leftErrorModal').modal({
                    backdrop: 'static',
                    keyboard: false,
                    show: true
                });
            } else {
                console.log("******不应该出现的bug*******");
            }
        });
    }
}

/**
 * ¹¹ÔìJson¸ñÊ½µÄÊý¾Ý
 *
 * @return {json} jsonÊý¾Ý
 */
function createJsonData() {

    //query left id and weight
    var aTr = $("tbody>tr");
    var aInput = $("tbody>tr>td>input");
    var leftMapResultString = "";
    console.log(aTr);
    console.log(aTr.length);
    console.log(aInput);
    console.log(aInput.length);
    for (var i = 0; i < aTr.length; i++) {
        var metaDataId = aTr[i].getAttribute("metaid-intable");
        if (metaDataId) {
            leftMapResultString += metaDataId + ":" + aInput[i].value + ",";
        }
    }
    leftMapResultString = leftMapResultString.slice(0, -1); //delete last","
    console.log(leftMapResultString);

    //query right id
    var rightId = -1;
    var rightIdResult = $("[metaid-inrightdiv]").attr("metaid-inrightdiv");
    if (rightIdResult) {
        rightId = rightIdResult;
    }

    //query sortValue
    var sortValue = $("input[name='sort']:checked").val();
   
    
    //query reliablity
    var reliablityResult = $("#reliablity").val();
    var commentContent = $("#comment").val();
    var usernameId = $("#metaDataManagerName").val();
    
    
    var json = {
        leftMap: leftMapResultString,
        right: rightId,
        sort: sortValue,
        reliablity: reliablityResult,
        comment: commentContent,
        username: usernameId
    };
    return json;
}

/**
 * Ã¿Ò»¸öcheckbox¸Ä±äºóµÄ¶¯×÷
 *
 * @param {element}
 *            element ´«ÈëµÄDOMÔªËØ
 */function checkboxShowValue(element) {
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
    $('tbody')
        .append(
            '<tr metaid-intable="' + id + '"><td>' + name + '</td><td><input type="number" class="form-control" value="100" min="1" max="100" required="required"></td></tr>');
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

/**
 * µÃµ½µ±Ç°radioµÄÖµºÍid
 *
 * @param {element}
 *            element ´«ÈëµÄ½ÚµãÔªËØ
 */
function radioShowValue(element) {
    console.log(element);
    if (element.checked === true) {
        console.log("radio ture--->" + element.getAttribute("meta-id-right") + element.value);
        changeRightAreaContent(element.getAttribute("meta-id-right"),
            element.value);
    }
}

/**
 * ¸Ä±äºó¼þÇøµÄÄÚÈÝ
 *
 * @param {String}
 *            id ´«ÈëµÄidÖµ
 * @param {String}
 *            name ´«ÈëµÄÔªÊý¾ÝÄÚÈÝ
 */
function changeRightAreaContent(id, name) {
    $('.right-name').html(name).attr('metaid-inrightdiv', id);
}

