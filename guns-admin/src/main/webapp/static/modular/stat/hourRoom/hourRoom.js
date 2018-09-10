/**
 * 房间统计管理初始化
 */
var HourRoom = {
    id: "HourRoomTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HourRoom.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
	        {title: '日期', field: 'timeStr', visible: true, align: 'center', valign: 'middle'},
	        {title: '纸牌牛牛', field: 'pokerNiuCount', visible: true, align: 'center', valign: 'middle'},
	        {title: '麻将牛牛', field: 'mjNiuCount', visible: true, align: 'center', valign: 'middle'},
	        {title: '扎金花', field: 'zjhCount', visible: true, align: 'center', valign: 'middle'},
	        {title: '三公', field: 'sgCount', visible: true, align: 'center', valign: 'middle'},
	        {title: '开房总数', field: 'roomCount', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HourRoom.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HourRoom.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加房间统计
 */
HourRoom.openAddHourRoom = function () {
    var index = layer.open({
        type: 2,
        title: '添加房间统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hourRoom/hourRoom_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看房间统计详情
 */
HourRoom.openHourRoomDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '房间统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hourRoom/hourRoom_update/' + HourRoom.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除房间统计
 */
HourRoom.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/hourRoom/delete", function (data) {
            Feng.success("删除成功!");
            HourRoom.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("hourRoomId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询房间统计列表
 */
HourRoom.search = function () {
    var queryData = {};
    queryData['date'] = $("#condition").val();
    HourRoom.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = HourRoom.initColumn();
    var table = new BSTable(HourRoom.id, "/hourRoom/list", defaultColunms);
    table.setPaginationType("client");
    HourRoom.table = table.init();
});
