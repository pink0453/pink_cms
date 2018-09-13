/**
 * 初始化机器人收入统计详情对话框
 */
var RobotScoreInfoDlg = {
    robotScoreInfoData : {}
};

/**
 * 清除数据
 */
RobotScoreInfoDlg.clearData = function() {
    this.robotScoreInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RobotScoreInfoDlg.set = function(key, val) {
    this.robotScoreInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RobotScoreInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RobotScoreInfoDlg.close = function() {
    parent.layer.close(window.parent.RobotScore.layerIndex);
}

/**
 * 收集数据
 */
RobotScoreInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
RobotScoreInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/robotScore/add", function(data){
        Feng.success("添加成功!");
        window.parent.RobotScore.table.refresh();
        RobotScoreInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.robotScoreInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RobotScoreInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/robotScore/update", function(data){
        Feng.success("修改成功!");
        window.parent.RobotScore.table.refresh();
        RobotScoreInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.robotScoreInfoData);
    ajax.start();
}

$(function() {

});
