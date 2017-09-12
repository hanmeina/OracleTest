/**
 * 包含出错
 * @return {[type]} [description]
 */
function containError() {
    $("#containarea h5").attr("class", "text-danger");
    $("#containboolean").attr("class", "glyphicon glyphicon-remove");
    $("#containarea button").removeClass("hidden");
    $("[contain]").removeClass("hidden");
}
/**
 * 通过包含检查
 * @return {[type]} [description]
 */
function containPass() {
    $("#containarea h5").attr("class", "text-success");
    $("#containboolean").attr("class", "glyphicon glyphicon-ok");
}
/**
 * 被包含出错
 * @return {[type]} [description]
 */
function containedError() {
    $("#containedarea h5").attr("class", "text-danger");
    $("#containedboolean").attr("class", "glyphicon glyphicon-remove");
    $("#containedarea button").removeClass("hidden");
    $("[contained]").removeClass("hidden");
}
/**
 * 通过被包含检查
 * @return {[type]} [description]
 */
function containedPass() {
    $("#containedarea h5").attr("class", "text-success");
    $("#containedboolean").attr("class", "glyphicon glyphicon-ok");
}
/**
 * 传递性冗余出错
 * @return {[type]} [description]
 */
function deliverError() {
    $("#deliverarea h5").attr("class", "text-danger");
    $("#deliverduplicateboolean").attr("class", "glyphicon glyphicon-remove");
    $("#deliverarea button").removeClass("hidden");
    $("[deliver]").removeClass("hidden");
}
/**
 * 通过传递冗余检查
 * @return {[type]} [description]
 */
function deliverPass() {
    $("#deliverarea h5").attr("class", "text-success");
    $("#deliverduplicateboolean").attr("class", "glyphicon glyphicon-ok");
    // $(".reference").attr("class","reference text-info");
    // $("#sameleftarea h5").attr("class","text-info");
    // $("#samerightarea h5").attr("class","text-info");
    $("#okbtn").removeAttr("disabled");
}

/**
 * 条件冲突出错
 * @return {[type]} [description]
 */
function conditionedError() {
    $("#conditionedarea h5").attr("class", "text-danger");
    $("#conditionedboolean").attr("class", "glyphicon glyphicon-remove");
    $("#conditionedarea button").removeClass("hidden");
    $("[conditioned]").removeClass("hidden");
}
/**
 * 通过条件冲突检查
 * @return {[type]} [description]
 */
function conditionedPass() {
    $("#conditionedarea h5").attr("class", "text-success");
    $("#conditionedboolean").attr("class", "glyphicon glyphicon-ok");
}

/**
 * 结论冲突出错
 * @return {[type]} [description]
 */
function conditionError() {
    $("#conditionarea h5").attr("class", "text-danger");
    $("#conditionboolean").attr("class", "glyphicon glyphicon-remove");
    $("#conditionarea button").removeClass("hidden");
    $("[condition]").removeClass("hidden");
}
/**
 * 通过结论冲突检查
 * @return {[type]} [description]
 */
function conditionPass() {
    $("#conditionarea h5").attr("class", "text-success");
    $("#conditionboolean").attr("class", "glyphicon glyphicon-ok");
}

/**
 * 前件相同规则不存在
 * @return {[type]} [description]
 */
function sameLeftRulesEmpty() {
    $("#sameleftboolean").html("：否");
}

/**
 * 前件相同规则存在
 * @return {[type]} [description]
 */
function sameLeftRulesExist() {
    $("#sameleftboolean").html("：是");
    $("#sameleftarea button").removeClass("hidden");
    $("#left-equal-rules").removeClass("hidden");
}

/**
 * 后件相同规则不存在
 * @return {[type]} [description]
 */
function sameRightRulesEmpty() {
    $("#samerightboolean").html("：否");
}
/**
 * 后件相同规则存在
 * @return {[type]} [description]
 */
function sameRightRulesExist() {
    $("#samerightboolean").html("：是");
    $("#samerightarea button").removeClass("hidden");
    $("#right-equal-rules").removeClass("hidden");
}