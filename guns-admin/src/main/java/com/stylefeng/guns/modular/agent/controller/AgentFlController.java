package com.stylefeng.guns.modular.agent.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ConstantUtil;
import com.stylefeng.guns.core.util.DateUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.modular.agent.service.IPlayerService;
import com.stylefeng.guns.modular.agent.service.IUserFlService;
import com.stylefeng.guns.modular.mongoModel.Mj_agent_fl;
import com.stylefeng.guns.modular.mongoModel.Mj_players;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_online;
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
    	List<Integer> ids = new ArrayList<Integer>();
    	
    	if(ShiroKit.hasAnyRoles(ConstantUtil.ADMIN_ROLES)) {
    		
    		userFls = userFlService.getAll();
    		
    		
    	}else {
    		
    		ShiroUser suser = ShiroKit.getUser();
    		User currentUser = userService.selectById(suser.getId());
    		
    		//直接查询返给当前用户的返利
    		int curGameId = currentUser.getGameAccountId();
    		userFls = userFlService.getFlByCurGameId(curGameId, 1);
    		
    		//------------------------------------------递归查询---------------------------------------------------
//    		List<User> users = userService.getUsersByCurrentUser(new ArrayList<User>(), currentUser.getId());
//    		List<Mj_players> tmpPlayers = new ArrayList<Mj_players>();
//    		for(User user : users) {
//    			
//    			ids.add(user.getGameAccountId());
//    			
//    			List<Mj_players> players = playerService.getPlayersByRef(new ArrayList<>(), user.getGameAccountId());
//    			if(players != null) {
//    				
//    				tmpPlayers.addAll(players);
//    				
//    			}
//    			
//    		}
//    		ids.add(currentUser.getGameAccountId());
//    		
//    		List<Mj_players> players = playerService.getPlayersByRef(new ArrayList<>(), currentUser.getGameAccountId());
//    		tmpPlayers.addAll(players);
//    		
//    		List<Mj_agent_fl> newFl = new ArrayList<Mj_agent_fl>();
//    		
//    		for(Mj_players player : tmpPlayers) {
//    			
//    			newFl.addAll(userFlService.getFlByCurUser(player, 1));
//    			
//    		}
//    		
//    		for(Integer id : ids) {
//        		
//        		for(Mj_agent_fl afl : newFl) {
//            		
//        			if(afl.getAid() == id) {
//        				
//        				if(afl.getAid() == currentUser.getGameAccountId()) {
//        					
//        					userFls.add(afl);
//        					
//        				}
//        				
//        			}
//            		
//            	}
//        		
//        	}
//    		
    	}
    	
    	for(Mj_agent_fl afl : userFls) {
    		
    		String date = DateUtil.fomatLongDateHHmmss(afl.getTime());
    		afl.setTimeStr(date);
    		
    	}
    	
    	//倒序排
    	if(userFls != null) {
			
			Comparator<Mj_agent_fl> comparator = (s1, s2) -> s1.getTime().compareTo(s2.getTime());
			userFls.sort(comparator.reversed());
			//comparator.reversed()
		}
    	
    	
    	
    	return userFls;
    	
    }

    /**
     * 新增代理返利
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_agent_fl agentFl) {
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
    public Object update(Mj_agent_fl agentFl) {
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
    
    /**
     * list去重
     * @param list
     * @return
     */
    public List<Integer> removeDuplicate(List<Integer> list) {   
        HashSet<Integer> h = new HashSet<Integer>(list);   
        list.clear();   
        list.addAll(h);   
        return list;   
    }   
    
}
