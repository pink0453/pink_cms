/**
 * 代理充值明细管理初始化
 */
var OrderAgentRecord = {
    id: "OrderAgentRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OrderAgentRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
//            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'price', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'productId', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'productAmount', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'productName', visible: true, align: 'center', valign: 'middle'},
            {title: '玩家ID', field: 'USER_ID', visible: true, align: 'center', valign: 'middle'},
            {title: '充值金额', field: 'AMOUNT', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'myCode', visible: true, align: 'center', valign: 'middle'},
            {title: '玩家昵称', field: 'NICKNAME', visible: true, align: 'center', valign: 'middle'},
//            {title: '', field: 'gameType', visible: true, align: 'center', valign: 'middle'},
//            {title: '代理ID', field: 'AGENT_ID', visible: true, align: 'center', valign: 'middle'},
//            {title: '代理游戏ID', field: 'AGENT_NAME', visible: true, align: 'center', valign: 'middle'},
            {title: '所属代理ID', field: 'AGENT_ID', visible: true, align: 'center', valign: 'middle'},
            {title: '所属代理姓名', field: 'AGENT_NAME', visible: true, align: 'center', valign: 'middle'},
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
OrderAgentRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OrderAgentRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加代理充值明细
 */
OrderAgentRecord.openAddOrderAgentRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加代理充值明细',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/orderAgentRecord/orderAgentRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看代理充值明细详情
 */
OrderAgentRecord.openOrderAgentRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '代理充值明细详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/orderAgentRecord/orderAgentRecord_update/' + OrderAgentRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除代理充值明细
 */
OrderAgentRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/orderAgentRecord/delete", function (data) {
            Feng.success("删除成功!");
            OrderAgentRecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("orderAgentRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询代理充值明细列表
 */
OrderAgentRecord.search = function () {
    var queryData = {};
    
    queryData['playerId'] = $("#playerId").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    
    OrderAgentRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OrderAgentRecord.initColumn();
    var table = new BSTable(OrderAgentRecord.id, "/orderAgentRecord/list", defaultColunms);
    table.setPaginationType("client");
    OrderAgentRecord.table = table.init();
});
