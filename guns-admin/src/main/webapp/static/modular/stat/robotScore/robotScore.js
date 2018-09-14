/**
 * 机器人收入统计管理初始化
 */
var RobotScore = {
    id: "RobotScoreTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RobotScore.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '日期', field: 'timeStr', visible: true, align: 'center', valign: 'middle'},
            {title: '纸牌牛牛抽水金额', field: 'niuniuPokerWaterScore', visible: true, align: 'center', valign: 'middle'},
            {title: '麻将牛牛抽水金额', field: 'niuniuMjWaterScore', visible: true, align: 'center', valign: 'middle'},
            {title: '扎金花抽水金额', field: 'zjhPokerWaterScore', visible: true, align: 'center', valign: 'middle'},
            {title: '三公抽水金额', field: 'sangongPokerWaterScore', visible: true, align: 'center', valign: 'middle'},
            {title: '总抽水金额', field: 'totalWaterScore', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
RobotScore.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        RobotScore.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加机器人收入统计
 */
RobotScore.openAddRobotScore = function () {
    var index = layer.open({
        type: 2,
        title: '添加机器人收入统计',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/robotScore/robotScore_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看机器人收入统计详情
 */
RobotScore.openRobotScoreDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '机器人收入统计详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/robotScore/robotScore_update/' + RobotScore.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除机器人收入统计
 */
RobotScore.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/robotScore/delete", function (data) {
            Feng.success("删除成功!");
            RobotScore.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("robotScoreId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询机器人收入统计列表
 */
RobotScore.search = function () {
    var queryData = {};
    queryData['date'] = $("#condition").val();
    RobotScore.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = RobotScore.initColumn();
    var table = new BSTable(RobotScore.id, "/robotScore/list", defaultColunms);
    table.setPaginationType("client");
    RobotScore.table = table.init();
});
