/**
 * 初始化注册量统计详情对话框
 */
var RegisterInfoDlg = {
    registerInfoData : {}
};

/**
 * 清除数据
 */
RegisterInfoDlg.clearData = function() {
    this.registerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RegisterInfoDlg.set = function(key, val) {
    this.registerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RegisterInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RegisterInfoDlg.close = function() {
    parent.layer.close(window.parent.Register.layerIndex);
}

/**
 * 收集数据
 */
RegisterInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
RegisterInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/register/add", function(data){
        Feng.success("添加成功!");
        window.parent.Register.table.refresh();
        RegisterInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.registerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RegisterInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/register/update", function(data){
        Feng.success("修改成功!");
        window.parent.Register.table.refresh();
        RegisterInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.registerInfoData);
    ajax.start();
}

$(function() {

});
