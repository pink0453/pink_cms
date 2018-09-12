/**
 * 初始化系统抽水统计详情对话框
 */
var SystemWaterInfoDlg = {
    systemWaterInfoData : {}
};

/**
 * 清除数据
 */
SystemWaterInfoDlg.clearData = function() {
    this.systemWaterInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SystemWaterInfoDlg.set = function(key, val) {
    this.systemWaterInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SystemWaterInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SystemWaterInfoDlg.close = function() {
    parent.layer.close(window.parent.SystemWater.layerIndex);
}

/**
 * 收集数据
 */
SystemWaterInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
SystemWaterInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/systemWater/add", function(data){
        Feng.success("添加成功!");
        window.parent.SystemWater.table.refresh();
        SystemWaterInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.systemWaterInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SystemWaterInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/systemWater/update", function(data){
        Feng.success("修改成功!");
        window.parent.SystemWater.table.refresh();
        SystemWaterInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.systemWaterInfoData);
    ajax.start();
}

$(function() {

});
