/**
 * 平台收入统计管理初始化
 */
var Plaform = {
    id: "PlaformTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Plaform.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '日期', field: 'timeStr', visible: true, align: 'center', valign: 'middle'},
            {title: '兑换金额', field: 'conversionMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '占比', field: 'conversionRate', visible: true, align: 'center', valign: 'middle'},
            {title: '抽水金额', field: 'waterMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '占比', field: 'waterRate', visible: true, align: 'center', valign: 'middle'},
            {title: '机器人收入', field: 'robotMoney', visible: true, align: 'center', valign: 'middle'},
            {title: '占比', field: 'robotRate', visible: true, align: 'center', valign: 'middle'},
            {title: '系统总收入', field: 'totalMoney', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Plaform.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Plaform.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加平台收入统计
 */
Plaform.openAddPlaform = function () {
    var index = layer.open({
        type: 2,
        title: '添加平台收入统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/plaform/plaform_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看平台收入统计详情
 */
Plaform.openPlaformDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '平台收入统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/plaform/plaform_update/' + Plaform.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除平台收入统计
 */
Plaform.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/plaform/delete", function (data) {
            Feng.success("删除成功!");
            Plaform.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("plaformId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询平台收入统计列表
 */
Plaform.search = function () {
    var queryData = {};
    queryData['date'] = $("#condition").val();
    Plaform.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Plaform.initColumn();
    var table = new BSTable(Plaform.id, "/plaform/list", defaultColunms);
    table.setPaginationType("client");
    Plaform.table = table.init();
});
