/**
 * 注册量统计管理初始化
 */
var Register = {
    id: "RegisterTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Register.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '时间', field: 'timeStr', visible: true, align: 'center', valign: 'middle'},
            {title: '注册人数', field: 'regCount', visible: true, align: 'center', valign: 'middle'},
            {title: '环比增长', field: 'ringGrowth', visible: true, align: 'center', valign: 'middle'},
            {title: '环比增长率', field: 'ringGrowthRate', visible: true, align: 'center', valign: 'middle'},
            {title: '同比增长', field: 'yoyGrowth', visible: true, align: 'center', valign: 'middle'},
            {title: '同比增长率', field: 'yoyGrowthRate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Register.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Register.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加注册量统计
 */
Register.openAddRegister = function () {
    var index = layer.open({
        type: 2,
        title: '添加注册量统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/register/register_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看注册量统计详情
 */
Register.openRegisterDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '注册量统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/register/register_update/' + Register.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除注册量统计
 */
Register.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/register/delete", function (data) {
            Feng.success("删除成功!");
            Register.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("registerId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询注册量统计列表
 */
Register.search = function () {
    var queryData = {};
    queryData['date'] = $("#condition").val();
    Register.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Register.initColumn();
    var table = new BSTable(Register.id, "/register/list", defaultColunms);
    table.setPaginationType("client");
    Register.table = table.init();
});
