/**
 * 代理返利管理初始化
 */
var AgentFl = {
    id: "AgentFlTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AgentFl.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '代理游戏ID', field: 'aid', visible: true, align: 'center', valign: 'middle'},
        {title: '代理等级', field: 'lv', visible: true, align: 'center', valign: 'middle'},
        {title: '玩家ID', field: 'playerId', visible: true, align: 'center', valign: 'middle'},
        {title: '玩家昵称', field: 'playerName', visible: true, align: 'center', valign: 'middle'},
        {title: '返利金额', field: 'money', visible: true, align: 'center', valign: 'middle'},
        {title: '上级代理', field: 'pid', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'timeStr', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
AgentFl.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AgentFl.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加代理返利
 */
AgentFl.openAddAgentFl = function () {
    var index = layer.open({
        type: 2,
        title: '添加代理返利',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/agentFl/agentFl_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看代理返利详情
 */
AgentFl.openAgentFlDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '代理返利详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/agentFl/agentFl_update/' + AgentFl.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除代理返利
 */
AgentFl.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/agentFl/delete", function (data) {
            Feng.success("删除成功!");
            AgentFl.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("agentFlId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询代理返利列表
 */
AgentFl.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AgentFl.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AgentFl.initColumn();
    var table = new BSTable(AgentFl.id, "/agentFl/list", defaultColunms);
    table.setPaginationType("client");
    AgentFl.table = table.init();
});
