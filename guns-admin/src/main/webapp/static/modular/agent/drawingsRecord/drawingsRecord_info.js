/**
 * 初始化代理提现记录详情对话框
 */
var DrawingsRecordInfoDlg = {
    drawingsRecordInfoData : {}
};

/**
 * 清除数据
 */
DrawingsRecordInfoDlg.clearData = function() {
    this.drawingsRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DrawingsRecordInfoDlg.set = function(key, val) {
    this.drawingsRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DrawingsRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DrawingsRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.DrawingsRecord.layerIndex);
}

/**
 * 收集数据
 */
DrawingsRecordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('amount')
    .set('commision')
    .set('rate')
    .set('userId')
    .set('status')
    .set('createTime')
    .set('updateTime')
    .set('type')
    .set('name')
    .set('bankCard')
    .set('bankType');
}

/**
 * 提交添加
 */
DrawingsRecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/drawingsRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.DrawingsRecord.table.refresh();
        DrawingsRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.drawingsRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DrawingsRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/drawingsRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.DrawingsRecord.table.refresh();
        DrawingsRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.drawingsRecordInfoData);
    ajax.start();
}

$(function() {

});
