/**
 * 初始化开房统计详情对话框
 */
var OpenRoomInfoDlg = {
    openRoomInfoData : {}
};

/**
 * 清除数据
 */
OpenRoomInfoDlg.clearData = function() {
    this.openRoomInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OpenRoomInfoDlg.set = function(key, val) {
    this.openRoomInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OpenRoomInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OpenRoomInfoDlg.close = function() {
    parent.layer.close(window.parent.OpenRoom.layerIndex);
}

/**
 * 收集数据
 */
OpenRoomInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
OpenRoomInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/openRoom/add", function(data){
        Feng.success("添加成功!");
        window.parent.OpenRoom.table.refresh();
        OpenRoomInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.openRoomInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OpenRoomInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/openRoom/update", function(data){
        Feng.success("修改成功!");
        window.parent.OpenRoom.table.refresh();
        OpenRoomInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.openRoomInfoData);
    ajax.start();
}

$(function() {

});
