/**
 * 初始化代理返利统计详情对话框
 */
var AgentFlInfoDlg = {
    agentFlInfoData : {}
};

/**
 * 清除数据
 */
AgentFlInfoDlg.clearData = function() {
    this.agentFlInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AgentFlInfoDlg.set = function(key, val) {
    this.agentFlInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AgentFlInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AgentFlInfoDlg.close = function() {
    parent.layer.close(window.parent.AgentFl.layerIndex);
}

/**
 * 收集数据
 */
AgentFlInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
AgentFlInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/agentFlStat/add", function(data){
        Feng.success("添加成功!");
        window.parent.AgentFl.table.refresh();
        AgentFlInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.agentFlInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AgentFlInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/agentFlStat/update", function(data){
        Feng.success("修改成功!");
        window.parent.AgentFl.table.refresh();
        AgentFlInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.agentFlInfoData);
    ajax.start();
}

$(function() {

});
