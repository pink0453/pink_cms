/**
 * 玩家充值管理初始化
 */
var OrderRecord = {
    id: "OrderRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OrderRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
//            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'price', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'productId', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'productAmount', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'productName', visible: true, align: 'center', valign: 'middle'},
            {title: '游戏ID', field: 'MY_CODE', visible: true, align: 'center', valign: 'middle'},
            {title: '玩家昵称', field: 'NICKNAME', visible: true, align: 'center', valign: 'middle'},
            {title: '充值金额', field: 'AMOUNT', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'gameType', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'agentId', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'agentGameId', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'from', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'status', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '充值时间', field: 'CREATE_TIME', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '更新时间', field: 'EDIT_TIME', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
OrderRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OrderRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加玩家充值
 */
OrderRecord.openAddOrderRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加玩家充值',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/orderRecord/orderRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看玩家充值详情
 */
OrderRecord.openOrderRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '玩家充值详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/orderRecord/orderRecord_update/' + OrderRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除玩家充值
 */
OrderRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/orderRecord/delete", function (data) {
            Feng.success("删除成功!");
            OrderRecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("orderRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询玩家充值列表
 */
OrderRecord.search = function () {
    var queryData = {};
    
    queryData['playerId'] = $("#playerId").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    
    OrderRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OrderRecord.initColumn();
    var table = new BSTable(OrderRecord.id, "/orderRecord/list", defaultColunms);
    table.setPaginationType("client");
    OrderRecord.table = table.init();
});
