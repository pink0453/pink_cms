/**
 * 兑换收入统计管理初始化
 */
var Conversion = {
    id: "ConversionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Conversion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '日期', field: 'timeStr', visible: true, align: 'center', valign: 'middle'},
            {title: '代理收入', field: 'agentMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '系统收入', field: 'systemMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '总收入', field: 'totalMoney', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Conversion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Conversion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加兑换收入统计
 */
Conversion.openAddConversion = function () {
    var index = layer.open({
        type: 2,
        title: '添加兑换收入统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/conversion/conversion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看兑换收入统计详情
 */
Conversion.openConversionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '兑换收入统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/conversion/conversion_update/' + Conversion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除兑换收入统计
 */
Conversion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/conversion/delete", function (data) {
            Feng.success("删除成功!");
            Conversion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("conversionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询兑换收入统计列表
 */
Conversion.search = function () {
    var queryData = {};
    queryData['date'] = $("#condition").val();
    Conversion.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Conversion.initColumn();
    var table = new BSTable(Conversion.id, "/conversion/list", defaultColunms);
    table.setPaginationType("client");
    Conversion.table = table.init();
});
