/**
 * 初始化金币修改详情对话框
 */
var JinbiInfoDlg = {
    jinbiInfoData : {}
};

/**
 * 清除数据
 */
JinbiInfoDlg.clearData = function() {
    this.jinbiInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
JinbiInfoDlg.set = function(key, val) {
    this.jinbiInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
JinbiInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
JinbiInfoDlg.close = function() {
    parent.layer.close(window.parent.Jinbi.layerIndex);
}

/**
 * 收集数据
 */
JinbiInfoDlg.collectData = function() {
    this
    .set('id')
    .set('jinbi');
}

/**
 * 提交添加
 */
JinbiInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/jinbi/add", function(data){
        Feng.success("添加成功!");
        window.parent.Jinbi.table.refresh();
        JinbiInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.jinbiInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
JinbiInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/jinbi/update", function(data){
        Feng.success("修改成功!");
        window.parent.Jinbi.table.refresh();
        JinbiInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.jinbiInfoData);
    ajax.start();
}

$(function() {

});
