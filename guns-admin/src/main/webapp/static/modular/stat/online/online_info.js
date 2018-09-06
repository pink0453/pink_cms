/**
 * 初始化在线统计详情对话框
 */
var OnlineInfoDlg = {
    onlineInfoData : {}
};

/**
 * 清除数据
 */
OnlineInfoDlg.clearData = function() {
    this.onlineInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OnlineInfoDlg.set = function(key, val) {
    this.onlineInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OnlineInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OnlineInfoDlg.close = function() {
    parent.layer.close(window.parent.Online.layerIndex);
}

/**
 * 收集数据
 */
OnlineInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
OnlineInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/online/add", function(data){
        Feng.success("添加成功!");
        window.parent.Online.table.refresh();
        OnlineInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.onlineInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OnlineInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/online/update", function(data){
        Feng.success("修改成功!");
        window.parent.Online.table.refresh();
        OnlineInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.onlineInfoData);
    ajax.start();
}

$(function() {

});
