/**
 * 当前规则已被使用过，不能被删除
 * @return {[type]} [description]
 */
function deleteError() {
    $("#deletearea h5").attr("class", "text-danger");
    $("#deleteboolean").attr("class", "glyphicon glyphicon-remove");
    $("#deletearea button").removeClass("hidden");
    $("[delete]").removeClass("hidden");
    
}
/**
 * 当前规则未被使用过，可以删除
 * @return {[type]} [description]
 */
function deletePass() {
    $("#deletearea h5").attr("class", "text-success");
    $("#deleteboolean").attr("class", "glyphicon glyphicon-ok");
}