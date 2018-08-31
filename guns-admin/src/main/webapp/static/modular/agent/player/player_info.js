/**
 * 初始化玩家列表详情对话框
 */
var PlayerInfoDlg = {
    playerInfoData : {}
};

/**
 * 清除数据
 */
PlayerInfoDlg.clearData = function() {
    this.playerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlayerInfoDlg.set = function(key, val) {
    this.playerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlayerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PlayerInfoDlg.close = function() {
    parent.layer.close(window.parent.Player.layerIndex);
}

/**
 * 收集数据
 */
PlayerInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
PlayerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/player/add", function(data){
        Feng.success("添加成功!");
        window.parent.Player.table.refresh();
        PlayerInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.playerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PlayerInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/player/update", function(data){
        Feng.success("修改成功!");
        window.parent.Player.table.refresh();
        PlayerInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.playerInfoData);
    ajax.start();
}

$(function() {

});
