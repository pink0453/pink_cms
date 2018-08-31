package com.stylefeng.guns.modular.agent.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.DateUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.modular.agent.service.IPlayerService;
import com.stylefeng.guns.modular.agent.service.IUserFlService;
import com.stylefeng.guns.modular.system.model.AgentFl;
import com.stylefeng.guns.modular.system.model.Mj_agent_fl;
import com.stylefeng.guns.modular.system.model.Mj_players;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;

/**
 * 代理返利控制器
 *
 * @author fengshuonan
 * @Date 2018-08-30 16:28:39
 */
@Controller
@RequestMapping("/agentFl")
public class AgentFlController extends BaseController {

    private String PREFIX = "/agent/agentFl/";
    
    @Autowired
    private IUserFlService userFlService;
    @Autowired
    private IPlayerService playerService;
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserService agentFlService;

    /**
     * 跳转到代理返利首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "agentFl.html";
    }

    /**
     * 跳转到添加代理返利
     */
    @RequestMapping("/agentFl_add")
    public String agentFlAdd() {
        return PREFIX + "agentFl_add.html";
    }

    /**
     * 跳转到修改代理返利
     */
    @RequestMapping("/agentFl_update/{agentFlId}")
    public String agentFlUpdate(@PathVariable Integer agentFlId, Model model) {
//        AgentFl agentFl = agentFlService.selectById(agentFlId);
//        model.addAttribute("item",agentFl);
//        LogObjectHolder.me().set(agentFl);
        return PREFIX + "agentFl_edit.html";
    }

    /**
     * 获取代理返利列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	
    	List<Mj_agent_fl> userFls = new ArrayList<Mj_agent_fl>();
    	
    	if(ShiroKit.isAdmin()) {
    		
    		userFls = userFlService.getAll();
    		
    		
    	}else {
    		
    		ShiroUser suser = ShiroKit.getUser();
    		User currentUser = userService.selectById(suser.getId());
    		List<Mj_players> players = playerService.getPlayersByRef(new ArrayList<>(), currentUser.getGameAccountId());
    		
    		userFls = new ArrayList<Mj_agent_fl>();
    		for(Mj_players player : players) {
    			
    			userFls.addAll(userFlService.getFlByCurUser(player, 1));
    			
    		}
    		
    	}
    	
    	for(Mj_agent_fl afl : userFls) {
    		
    		String date = DateUtil.fomatLongDateHHmmss(afl.getTime());
    		afl.setTimeStr(date);
    		
    	}
    	
    	return userFls;
    	
    }

    /**
     * 新增代理返利
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AgentFl agentFl) {
//        agentFlService.insert(agentFl);
        return SUCCESS_TIP;
    }

    /**
     * 删除代理返利
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer agentFlId) {
        agentFlService.deleteById(agentFlId);
        return SUCCESS_TIP;
    }

    /**
     * 修改代理返利
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AgentFl agentFl) {
//        agentFlService.updateById(agentFl);
        return SUCCESS_TIP;
    }

    /**
     * 代理返利详情
     */
    @RequestMapping(value = "/detail/{agentFlId}")
    @ResponseBody
    public Object detail(@PathVariable("agentFlId") Integer agentFlId) {
        return agentFlService.selectById(agentFlId);
    }
}