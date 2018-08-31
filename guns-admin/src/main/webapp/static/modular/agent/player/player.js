/**
 * 玩家列表管理初始化
 */
var Player = {
    id: "PlayerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Player.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '玩家ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户昵称', field: 'nickname', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle'},
            {title: '国家', field: 'country', visible: true, align: 'center', valign: 'middle'},
            {title: '省', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '市', field: 'city', visible: true, align: 'center', valign: 'middle'},
            {title: '钻石', field: 'numof_cards_2', visible: true, align: 'center', valign: 'middle'},
            {title: '金币', field: 'jinbi', visible: true, align: 'center', valign: 'middle'},
            {title: '绑定代理', field: 'referrer_uid', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createDateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '最后登录时间', field: 'lastLoginTime', visible: true, align: 'center', valign: 'middle'},
            {title: 'ip', field: 'ip', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Player.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Player.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加玩家列表
 */
Player.openAddPlayer = function () {
    var index = layer.open({
        type: 2,
        title: '添加玩家列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/player/player_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看玩家列表详情
 */
Player.openPlayerDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '玩家列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/player/player_update/' + Player.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除玩家列表
 */
Player.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/player/delete", function (data) {
            Feng.success("删除成功!");
            Player.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("playerId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询玩家列表列表
 */
Player.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Player.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Player.initColumn();
    var table = new BSTable(Player.id, "/player/list", defaultColunms);
    table.setPaginationType("client");
    Player.table = table.init();
});
