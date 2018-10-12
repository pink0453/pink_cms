/**
 * 管理初始化
 */
var Agent = {
    id: "AgentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Agent.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'USER_ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '账号', field: 'account', visible: true, align: 'center', valign: 'middle'},
            {title: '名字', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '生日', field: 'birthday', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'sexName', visible: true, align: 'center', valign: 'middle'},
            {title: '电子邮件', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '代理等级', field: 'roleName', visible: true, align: 'center', valign: 'middle'},
            {title: '反水百分比', field: 'rate', visible: true, align: 'center', valign: 'middle'},
            {title: '游戏ID', field: 'game_account_id', visible: true, align: 'center', valign: 'middle'},
            {title: '上级代理id', field: 'parent_id', visible: true, align: 'center', valign: 'middle'},
            {title: '绑定玩家数量', field: 'playersCount', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '最后登录时间', field: 'lastLogin', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'}
//            {title: '保留字段', field: 'version', visible: true, align: 'center', valign: 'middle'}
//            {title: 'wx', field: 'wx', visible: true, align: 'center', valign: 'middle'}
//            {title: '拥有的游戏权限', field: 'gameIds', visible: true, align: 'center', valign: 'middle'}
//            {title: '权限', field: 'rights', visible: true, align: 'center', valign: 'middle'}
//            {title: '关系', field: 'bz', visible: true, align: 'center', valign: 'middle'}
//            {title: '皮肤', field: 'skin', visible: true, align: 'center', valign: 'middle'}
//            {title: '头像', field: 'avatar', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Agent.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Agent.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
//area: ['800px', '420px'], //宽高
Agent.openAddAgent = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['330px', '420px'], //宽高
        fix: false, //不固定
        maxmin: false,
        moveOut:true,
        content: Feng.ctxPath + '/agent/agent_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Agent.openAgentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/agent/agent_update/' + Agent.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Agent.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/agent/delete", function (data) {
            Feng.success("删除成功!");
            Agent.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("agentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Agent.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Agent.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Agent.initColumn();
    var table = new BSTable(Agent.id, "/agent/list", defaultColunms);
    table.setPaginationType("client");
    Agent.table = table.init();
});
