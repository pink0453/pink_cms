/**
 * 初始化玩家充值详情对话框
 */
var OrderRecordInfoDlg = {
    orderRecordInfoData : {}
};

/**
 * 清除数据
 */
OrderRecordInfoDlg.clearData = function() {
    this.orderRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderRecordInfoDlg.set = function(key, val) {
    this.orderRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OrderRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.OrderRecord.layerIndex);
}

/**
 * 收集数据
 */
OrderRecordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('price')
    .set('productId')
    .set('productAmount')
    .set('productName')
    .set('amount')
    .set('userId')
    .set('myCode')
    .set('nickname')
    .set('gameType')
    .set('agentId')
    .set('agentGameId')
    .set('from')
    .set('status')
    .set('remark')
    .set('createTime')
    .set('editTime');
}

/**
 * 提交添加
 */
OrderRecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/orderRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.OrderRecord.table.refresh();
        OrderRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OrderRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/orderRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.OrderRecord.table.refresh();
        OrderRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderRecordInfoData);
    ajax.start();
}

$(function() {

});
