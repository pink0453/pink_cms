/**
 * 代理提现记录管理初始化
 */
var DrawingsRecord = {
    id: "DrawingsRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DrawingsRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '提现金额', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '手续费', field: 'commision', visible: true, align: 'center', valign: 'middle'},
            {title: '费率', field: 'rate', visible: true, align: 'center', valign: 'middle'},
            {title: '提现用户', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '提现时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '银行卡绑定姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '银行卡账号', field: 'bankCard', visible: true, align: 'center', valign: 'middle'},
            {title: '银行卡类型', field: 'bankType', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DrawingsRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DrawingsRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加代理提现记录
 */
DrawingsRecord.openAddDrawingsRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加代理提现记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/drawingsRecord/drawingsRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看代理提现记录详情
 */
DrawingsRecord.openDrawingsRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '代理提现记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/drawingsRecord/drawingsRecord_update/' + DrawingsRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除代理提现记录
 */
DrawingsRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/drawingsRecord/delete", function (data) {
            Feng.success("删除成功!");
            DrawingsRecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("drawingsRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询代理提现记录列表
 */
DrawingsRecord.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DrawingsRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = DrawingsRecord.initColumn();
    var table = new BSTable(DrawingsRecord.id, "/drawingsRecord/list", defaultColunms);
    table.setPaginationType("client");
    DrawingsRecord.table = table.init();
});
