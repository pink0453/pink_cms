/**
 * 初始化兑换收入统计详情对话框
 */
var ConversionInfoDlg = {
    conversionInfoData : {}
};

/**
 * 清除数据
 */
ConversionInfoDlg.clearData = function() {
    this.conversionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConversionInfoDlg.set = function(key, val) {
    this.conversionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConversionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ConversionInfoDlg.close = function() {
    parent.layer.close(window.parent.Conversion.layerIndex);
}

/**
 * 收集数据
 */
ConversionInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name');
}

/**
 * 提交添加
 */
ConversionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/conversion/add", function(data){
        Feng.success("添加成功!");
        window.parent.Conversion.table.refresh();
        ConversionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.conversionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ConversionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/conversion/update", function(data){
        Feng.success("修改成功!");
        window.parent.Conversion.table.refresh();
        ConversionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.conversionInfoData);
    ajax.start();
}

$(function() {

});
