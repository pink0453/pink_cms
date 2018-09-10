/**
 * 初始化房间统计详情对话框
 */
var HourRoomInfoDlg = {
    hourRoomInfoData : {}
};

/**
 * 清除数据
 */
HourRoomInfoDlg.clearData = function() {
    this.hourRoomInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HourRoomInfoDlg.set = function(key, val) {
    this.hourRoomInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HourRoomInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HourRoomInfoDlg.close = function() {
    parent.layer.close(window.parent.HourRoom.layerIndex);
}

/**
 * 收集数据
 */
HourRoomInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
HourRoomInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hourRoom/add", function(data){
        Feng.success("添加成功!");
        window.parent.HourRoom.table.refresh();
        HourRoomInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hourRoomInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HourRoomInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hourRoom/update", function(data){
        Feng.success("修改成功!");
        window.parent.HourRoom.table.refresh();
        HourRoomInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hourRoomInfoData);
    ajax.start();
}

$(function() {

});
