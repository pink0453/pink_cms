/**
 * 金币修改管理初始化
 */
var Jinbi = {
    id: "JinbiTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Jinbi.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'jinbi', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Jinbi.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Jinbi.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加金币修改
 */
Jinbi.openAddJinbi = function () {
    var index = layer.open({
        type: 2,
        title: '添加金币修改',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/jinbi/jinbi_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看金币修改详情
 */
Jinbi.openJinbiDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '金币修改详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/jinbi/jinbi_update/' + Jinbi.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除金币修改
 */
Jinbi.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/jinbi/delete", function (data) {
            Feng.success("删除成功!");
            Jinbi.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("jinbiId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询金币修改列表
 */
Jinbi.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Jinbi.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Jinbi.initColumn();
    var table = new BSTable(Jinbi.id, "/jinbi/list", defaultColunms);
    table.setPaginationType("client");
    Jinbi.table = table.init();
});
