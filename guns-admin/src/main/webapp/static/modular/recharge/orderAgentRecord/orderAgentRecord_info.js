/**
 * 初始化代理充值明细详情对话框
 */
var OrderAgentRecordInfoDlg = {
    orderAgentRecordInfoData : {}
};

/**
 * 清除数据
 */
OrderAgentRecordInfoDlg.clearData = function() {
    this.orderAgentRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderAgentRecordInfoDlg.set = function(key, val) {
    this.orderAgentRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderAgentRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OrderAgentRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.OrderAgentRecord.layerIndex);
}

/**
 * 收集数据
 */
OrderAgentRecordInfoDlg.collectData = function() {
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
OrderAgentRecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/orderAgentRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.OrderAgentRecord.table.refresh();
        OrderAgentRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderAgentRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OrderAgentRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/orderAgentRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.OrderAgentRecord.table.refresh();
        OrderAgentRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderAgentRecordInfoData);
    ajax.start();
}

$(function() {

});
