/**
 * 初始化详情对话框
 */
var AgentInfoDlg = {
    agentInfoData : {},
    validateFields: {
        account: {
            validators: {
                notEmpty: {
                    message: '账户不能为空'
                }
            }
        },
        gameAccountId: {
            validators: {
                notEmpty: {
                    message: '游戏ID不能为空'
                }
            }
        },
        rate: {
            validators: {
                notEmpty: {
                    message: '返利率不能为空'
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '号码不能为空'
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'rePassword',
                    message: '两次密码不一致'
                },
            }
        },
        rePassword: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'password',
                    message: '两次密码不一致'
                },
            }
        }
    }
};

/**
 * 验证两个密码是否一致
 */
AgentInfoDlg.validatePwd = function () {
    var password = this.get("password");
    var rePassword = this.get("rePassword");
    if (password == rePassword) {
        return true;
    } else {
        return false;
    }
};

/**
 * 验证数据是否为空
 */
AgentInfoDlg.validate = function () {
    $('#agentInfoForm').data("bootstrapValidator").resetForm();
    $('#agentInfoForm').bootstrapValidator('validate');
    return $("#agentInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
AgentInfoDlg.clearData = function() {
    this.agentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AgentInfoDlg.set = function(key, val) {
    this.agentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AgentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AgentInfoDlg.close = function() {
    parent.layer.close(window.parent.Agent.layerIndex);
}

/**
 * 收集数据
 */
AgentInfoDlg.collectData = function() {
    this
    .set('id')
    .set('avatar')
    .set('account')
    .set('password')
    .set('salt')
    .set('name')
    .set('birthday')
    .set('sex')
    .set('email')
    .set('phone')
    .set('roleid')
    .set('deptid')
    .set('status')
    .set('createtime')
    .set('rights')
    .set('lastLogin')
    .set('bz')
    .set('skin')
    .set('gameIds')
    .set('wx')
    .set('rate')
    .set('gameAccountId')
    .set('parentId')
    .set('version');
}

/**
 * 提交添加
 */
AgentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    if (!this.validatePwd()) {
        Feng.error("两次密码输入不一致");
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/agent/add", function(data){
        Feng.success("添加成功!");
        window.parent.Agent.table.refresh();
        AgentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.agentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AgentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/agent/update", function(data){
        Feng.success("修改成功!");
        window.parent.Agent.table.refresh();
        AgentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.agentInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("agentInfoForm", AgentInfoDlg.validateFields);
    //初始化性别选项
    $("#sex").val($("#sexValue").val());
    $("#rate").val($("#rateValue").val());
    $("#roleid").val($("#roleValue").val());
});