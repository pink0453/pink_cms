/**
 * 系统抽水统计管理初始化
 */
var SystemWater = {
    id: "SystemWaterTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SystemWater.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '日期', field: 'timeStr', visible: true, align: 'center', valign: 'middle'},
            {title: '纸牌牛牛代理收入', field: 'niuniuPokerAgent', visible: true, align: 'center', valign: 'middle'},
            {title: '纸牌牛牛系统收入', field: 'niuniuPokerSystem', visible: true, align: 'center', valign: 'middle'},
            {title: '麻将牛牛代理收入', field: 'niuniuMjAgent', visible: true, align: 'center', valign: 'middle'},
            {title: '麻将牛牛系统收入', field: 'niuniuMjSystem', visible: true, align: 'center', valign: 'middle'},
            {title: '扎金花代理收入', field: 'zjhPokerAgent', visible: true, align: 'center', valign: 'middle'},
            {title: '扎金花系统收入', field: 'zjhPokerSystem', visible: true, align: 'center', valign: 'middle'},
            {title: '三公代理收入', field: 'sangongPokerAgent', visible: true, align: 'center', valign: 'middle'},
            {title: '三公系统收入', field: 'sangongPokerSystem', visible: true, align: 'center', valign: 'middle'},
            {title: '代理总收入', field: 'totalAgent', visible: true, align: 'center', valign: 'middle'},
            {title: '系统总收入', field: 'totalSystem', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SystemWater.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SystemWater.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加系统抽水统计
 */
SystemWater.openAddSystemWater = function () {
    var index = layer.open({
        type: 2,
        title: '添加系统抽水统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/systemWater/systemWater_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看系统抽水统计详情
 */
SystemWater.openSystemWaterDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '系统抽水统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/systemWater/systemWater_update/' + SystemWater.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除系统抽水统计
 */
SystemWater.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/systemWater/delete", function (data) {
            Feng.success("删除成功!");
            SystemWater.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("systemWaterId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询系统抽水统计列表
 */
SystemWater.search = function () {
    var queryData = {};
    queryData['date'] = $("#condition").val();
    SystemWater.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SystemWater.initColumn();
    var table = new BSTable(SystemWater.id, "/systemWater/list", defaultColunms);
    table.setPaginationType("client");
    SystemWater.table = table.init();
});
