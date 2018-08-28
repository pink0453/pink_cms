/**
 * 代理返利明细管理初始化
 */
var UserFl = {
    id: "UserFlTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserFl.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserFl.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserFl.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加代理返利明细
 */
UserFl.openAddUserFl = function () {
    var index = layer.open({
        type: 2,
        title: '添加代理返利明细',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userFl/userFl_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看代理返利明细详情
 */
UserFl.openUserFlDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '代理返利明细详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userFl/userFl_update/' + UserFl.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除代理返利明细
 */
UserFl.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/userFl/delete", function (data) {
            Feng.success("删除成功!");
            UserFl.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userFlId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询代理返利明细列表
 */
UserFl.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    UserFl.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = UserFl.initColumn();
    var table = new BSTable(UserFl.id, "/userFl/list", defaultColunms);
    table.setPaginationType("client");
    UserFl.table = table.init();
});
