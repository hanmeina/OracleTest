/**
 * ��ǰ�����ѱ�ʹ�ù������ܱ�ɾ��
 * @return {[type]} [description]
 */
function deleteError() {
    $("#deletearea h5").attr("class", "text-danger");
    $("#deleteboolean").attr("class", "glyphicon glyphicon-remove");
    $("#deletearea button").removeClass("hidden");
    $("[delete]").removeClass("hidden");
    
}
/**
 * ��ǰ����δ��ʹ�ù�������ɾ��
 * @return {[type]} [description]
 */
function deletePass() {
    $("#deletearea h5").attr("class", "text-success");
    $("#deleteboolean").attr("class", "glyphicon glyphicon-ok");
}