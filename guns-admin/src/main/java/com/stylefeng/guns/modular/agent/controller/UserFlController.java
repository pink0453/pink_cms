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
import com.stylefeng.guns.modular.mongoModel.Mj_agent_fl;
import com.stylefeng.guns.modular.mongoModel.Mj_players;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;

/**
 * 代理返利明细控制器
 *
 * @author fengshuonan
 * @Date 2018-08-29 01:03:21
 */
@Controller
@RequestMapping("/userFl")
public class UserFlController extends BaseController {

    private String PREFIX = "/agent/userFl/";

    @Autowired
    private IUserFlService userFlService;
    @Autowired
    private IPlayerService playerService;
    @Autowired
    private IUserService userService;

    /**
     * 跳转到代理返利明细首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userFl.html";
    }

    /**
     * 跳转到添加代理返利明细
     */
    @RequestMapping("/userFl_add")
    public String userFlAdd() {
        return PREFIX + "userFl_add.html";
    }

    /**
     * 跳转到修改代理返利明细
     */
    @RequestMapping("/userFl_update/{userFlId}")
    public String userFlUpdate(@PathVariable Integer userFlId, Model model) {
//        UserFl userFl = userFlService.selectById(userFlId);
//        model.addAttribute("item",userFl);
//        LogObjectHolder.me().set(userFl);
        return PREFIX + "userFl_edit.html";
    }

    /**
     * 获取玩家返利明细列表
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
    			
    			userFls.addAll(userFlService.getFlByCurUser(player, 0));
    			
    		}
    		
    	}
    	
    	for(Mj_agent_fl afl : userFls) {
    		
    		String date = DateUtil.fomatLongDateHHmmss(afl.getTime());
    		afl.setTimeStr(date);
    		
    	}
    
    	return userFls;
    	
    }

    /**
     * 新增代理返利明细
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_agent_fl userFl) {
//        userFlService.insert(userFl);
        return SUCCESS_TIP;
    }

    /**
     * 删除代理返利明细
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer userFlId) {
//        userFlService.deleteById(userFlId);
        return SUCCESS_TIP;
    }

    /**
     * 修改代理返利明细
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_agent_fl userFl) {
//        userFlService.updateById(userFl);
        return SUCCESS_TIP;
    }

    /**
     * 代理返利明细详情
     */
    @RequestMapping(value = "/detail/{userFlId}")
    @ResponseBody
    public Object detail(@PathVariable("userFlId") Integer userFlId) {
//        return userFlService.selectById(userFlId);
    	return null;
    }
}
