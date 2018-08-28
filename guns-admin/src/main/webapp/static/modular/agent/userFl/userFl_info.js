/**
 * 初始化代理返利明细详情对话框
 */
var UserFlInfoDlg = {
    userFlInfoData : {}
};

/**
 * 清除数据
 */
UserFlInfoDlg.clearData = function() {
    this.userFlInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserFlInfoDlg.set = function(key, val) {
    this.userFlInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserFlInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserFlInfoDlg.close = function() {
    parent.layer.close(window.parent.UserFl.layerIndex);
}

/**
 * 收集数据
 */
UserFlInfoDlg.collectData = function() {
    this
    .set('id');
}

/**
 * 提交添加
 */
UserFlInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userFl/add", function(data){
        Feng.success("添加成功!");
        window.parent.UserFl.table.refresh();
        UserFlInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userFlInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserFlInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userFl/update", function(data){
        Feng.success("修改成功!");
        window.parent.UserFl.table.refresh();
        UserFlInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userFlInfoData);
    ajax.start();
}

$(function() {

});
