package com.stylefeng.guns.modular.agent.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.DateUtil;

import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.agent.service.IPlayerService;
import com.stylefeng.guns.modular.system.model.Mj_players;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;

/**
 * 玩家列表控制器
 *
 * @author fengshuonan
 * @Date 2018-08-29 23:04:01
 */
@Controller
@RequestMapping("/player")
public class PlayerController extends BaseController {

    private String PREFIX = "/agent/player/";

    @Autowired
    private IPlayerService playerService;
    @Autowired
    private IUserService userService;

    /**
     * 跳转到玩家列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "player.html";
    }

    /**
     * 跳转到添加玩家列表
     */
    @RequestMapping("/player_add")
    public String playerAdd() {
        return PREFIX + "player_add.html";
    }

    /**
     * 跳转到修改玩家列表
     */
    @RequestMapping("/player_update/{playerId}")
    public String playerUpdate(@PathVariable Integer playerId, Model model) {
//        Player player = playerService.selectById(playerId);
//        model.addAttribute("item",player);
//        LogObjectHolder.me().set(player);
        return PREFIX + "player_edit.html";
    }

    /**
     * 获取玩家列表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	
    	List<Mj_players> players = new ArrayList<Mj_players>();
    	
    	if(ShiroKit.isAdmin()) {
    		
    		players = playerService.getAllPlayers();
    		
    		
    	}else {
    		
    		ShiroUser suser = ShiroKit.getUser();
    		User currentUser = userService.selectById(suser.getId());
    		players = playerService.getPlayersByRef(new ArrayList<>(), currentUser.getGameAccountId());
    		
    	}
    	
    	for(Mj_players player : players) {
    		
    		String lastDate = DateUtil.fomatLongDateHHmmss(player.getLast_login_time());
    		String createDate = DateUtil.fomatLongDateHHmmss(player.getCreate_time());
    		
    		player.setLastLoginTime(lastDate);
    		player.setCreateDateTime(createDate);
    		
    	}
    	
    	return players;
    	
    }

    /**
     * 新增玩家列表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_players player) {
//        playerService.insert(player);
        return SUCCESS_TIP;
    }

    /**
     * 删除玩家列表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer playerId) {
//        playerService.deleteById(playerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改玩家列表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_players player) {
//        playerService.updateById(player);
        return SUCCESS_TIP;
    }

    /**
     * 玩家列表详情
     */
    @RequestMapping(value = "/detail/{playerId}")
    @ResponseBody
    public Object detail(@PathVariable("playerId") Integer playerId) {
//        return playerService.selectById(playerId);
    	return null;
    }
}
