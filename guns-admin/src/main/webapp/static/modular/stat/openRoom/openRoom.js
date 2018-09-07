/**
 * 开房统计管理初始化
 */
var OpenRoom = {
    id: "OpenRoomTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OpenRoom.initColumn = function () {
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
OpenRoom.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OpenRoom.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加开房统计
 */
OpenRoom.openAddOpenRoom = function () {
    var index = layer.open({
        type: 2,
        title: '添加开房统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/openRoom/openRoom_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看开房统计详情
 */
OpenRoom.openOpenRoomDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '开房统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/openRoom/openRoom_update/' + OpenRoom.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除开房统计
 */
OpenRoom.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/openRoom/delete", function (data) {
            Feng.success("删除成功!");
            OpenRoom.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("openRoomId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询开房统计列表
 */
OpenRoom.search = function () {
    var queryData = {};
    queryData['date'] = $("#condition").val();
    OpenRoom.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OpenRoom.initColumn();
    var table = new BSTable(OpenRoom.id, "/openRoom/list", defaultColunms);
    table.setPaginationType("client");
    OpenRoom.table = table.init();
});
