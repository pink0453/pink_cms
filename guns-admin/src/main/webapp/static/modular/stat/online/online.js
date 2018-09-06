/**
 * 在线统计管理初始化
 */
var Online = {
    id: "OnlineTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Online.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '时间', field: 'beginTimeStr', visible: true, align: 'center', valign: 'middle'},
            {title: '在线人数', field: 'onCount', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Online.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Online.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加在线统计
 */
Online.openAddOnline = function () {
    var index = layer.open({
        type: 2,
        title: '添加在线统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/online/online_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看在线统计详情
 */
Online.openOnlineDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '在线统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/online/online_update/' + Online.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除在线统计
 */
Online.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/online/delete", function (data) {
            Feng.success("删除成功!");
            Online.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("onlineId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询在线统计列表
 */
Online.search = function () {
    var queryData = {};
    queryData['date'] = $("#condition").val();
    Online.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Online.initColumn();
    var table = new BSTable(Online.id, "/online/list", defaultColunms);
    table.setPaginationType("client");
    Online.table = table.init();
});
