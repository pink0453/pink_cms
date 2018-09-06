/**
 * 活跃人数管理初始化
 */
var Active = {
    id: "ActiveTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Active.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '时间', field: 'timeStr', visible: true, align: 'center', valign: 'middle'},
            {title: '当日活跃人数', field: 'actCount', visible: true, align: 'center', valign: 'middle'},
            {title: '环比增长', field: 'ringGrowth', visible: true, align: 'center', valign: 'middle'},
            {title: '环比增长率', field: 'ringGrowthRate', visible: true, align: 'center', valign: 'middle'},
            {title: '同比增长', field: 'yoyGrowth', visible: true, align: 'center', valign: 'middle'},
            {title: '同比增长率', field: 'yoyGrowthRate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Active.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Active.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加活跃人数
 */
Active.openAddActive = function () {
    var index = layer.open({
        type: 2,
        title: '添加活跃人数',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/active/active_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看活跃人数详情
 */
Active.openActiveDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '活跃人数详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/active/active_update/' + Active.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除活跃人数
 */
Active.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/active/delete", function (data) {
            Feng.success("删除成功!");
            Active.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("activeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询活跃人数列表
 */
Active.search = function () {
    var queryData = {};
    queryData['date'] = $("#condition").val();
    Active.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Active.initColumn();
    var table = new BSTable(Active.id, "/active/list", defaultColunms);
    table.setPaginationType("client");
    Active.table = table.init();
});
