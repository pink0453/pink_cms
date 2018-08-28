package com.stylefeng.guns.modular.agent.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.core.exception.GunsException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.modular.system.factory.UserFactory;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.transfer.UserDto;
import com.stylefeng.guns.modular.system.warpper.UserWarpper;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-08-28 11:46:54
 */
@Controller
@RequestMapping("/agent")
public class AgentController extends BaseController {

    private String PREFIX = "/agent/agent/";

    @Autowired
    private IUserService agentService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "agent.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/agent_add")
    public String agentAdd() {
        return PREFIX + "agent_add.html";
    }

    /**
     * 跳转到修改
     */
    @Permission
    @RequestMapping("/agent_update/{agentId}")
    public String agentUpdate(@PathVariable Integer agentId, Model model) {
        User agent = agentService.selectById(agentId);
        model.addAttribute("item",agent);
        LogObjectHolder.me().set(agent);
        return PREFIX + "agent_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    @Permission
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid) {
    	
//    	List<Map<String, Object>> users = agentService.selectUsers(null, name, beginTime, endTime, deptid);
//        return new UserWarpper(users).warp();
        
        if (ShiroKit.isAdmin()) {
            List<Map<String, Object>> users = agentService.selectUsers(null, name, beginTime, endTime, deptid);
            return new UserWarpper(users).warp();
        } else {
            DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope());
            List<Map<String, Object>> users = agentService.selectUsers(dataScope, name, beginTime, endTime, deptid);
            return new UserWarpper(users).warp();
        }
    	
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @Permission
    public Object add(@Valid UserDto agent, BindingResult result) {
        
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断账号是否重复
        User theUser = agentService.getByAccount(agent.getAccount());
        if (theUser != null) {
            throw new GunsException(BizExceptionEnum.USER_ALREADY_REG);
        }

        // 完善账号信息
        agent.setSalt(ShiroKit.getRandomSalt(5));
        agent.setPassword(ShiroKit.md5(agent.getPassword(), agent.getSalt()));
        agent.setStatus(ManagerStatus.OK.getCode());
        agent.setCreatetime(new Date());
        agent.setDeptid(28);//默认新增代理部门为代理部
        
        this.agentService.insert(UserFactory.createUser(agent));
//        agentService.insert(agent);
        
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @Permission
    public Object delete(@RequestParam Integer agentId) {
        agentService.deleteById(agentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    @Permission
    public Object update(User agent) {
        agentService.updateById(agent);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{agentId}")
    @ResponseBody
    @Permission
    public Object detail(@PathVariable("agentId") Integer agentId) {
        return agentService.selectById(agentId);
    }
}
