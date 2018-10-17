package com.stylefeng.guns.modular.agent.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.modular.mongoDao.PlayersDao;
import com.stylefeng.guns.modular.mongoModel.Mj_players;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;

import java.util.List;
import java.util.Map;

import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 金币修改控制器
 *
 * @author fengshuonan
 * @Date 2018-10-10 20:15:10
 */
@Controller
@RequestMapping("/jinbi")
public class JinbiController extends BaseController {

    private String PREFIX = "/agent/jinbi/";
    
    @Autowired
    private IUserService userService;
    @Autowired
    private PlayersDao playersDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    
    /**
     * 跳转到金币修改首页
     */
    @RequestMapping("")
    public String index(Model model) {
    	
    	ShiroUser suser = ShiroKit.getUser();
		User currentUser = userService.selectById(suser.getId());
		Integer ownerJinbi = currentUser.getJinbi();
    	
    	model.addAttribute("amount", ownerJinbi);
    	
        return PREFIX + "jinbi.html";
    }

    /**
     * 跳转到添加金币修改
     */
    @RequestMapping("/jinbi_add")
    public String jinbiAdd() {
        return PREFIX + "jinbi_add.html";
    }

    /**
     * 跳转到修改金币修改
     */
    @RequestMapping("/jinbi_update/{jinbiId}")
    public String jinbiUpdate(@PathVariable Integer jinbiId, Model model) {
//        Jinbi jinbi = jinbiService.selectById(jinbiId);
//        model.addAttribute("item",jinbi);
//        LogObjectHolder.me().set(jinbi);
        return PREFIX + "jinbi_edit.html";
    }

    /**
     * 获取金币修改列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
//        return jinbiService.selectList(null);
        return null;
    }

    /**
     * 新增金币修改
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@RequestParam Integer id, @RequestParam Integer amount, @RequestParam Integer type) {

    	ShiroUser suser = ShiroKit.getUser();
		User currentUser = userService.selectById(suser.getId());
		Integer ownerJinbi = currentUser.getJinbi();
		
		int status = 0;
		
		//检查余额
		if(ownerJinbi - amount > 0) {
			
			if(type == 0) {//代理修改金币
	    		
	    		Map<Integer, List<User>> usersMap = userService.currentUsersLvList(currentUser);
	    		List<User> users = usersMap.get(1);
	    		if(users != null && users.size() > 0) {
	    			
	    			boolean isHave = false;
	    			
	    			for(User user : users) {
	    				
	    				if(user.getId() == id) {
	    					
	    					isHave = true;
	    					
	    				}
	    				
	    			}
	    			
	    			if(isHave) {
	    				
						//修改代理金币
	    				userService.updateJinbi(id, amount);
	    				//修改自己金币
	    				userService.updateJinbi(currentUser.getId(), -amount);
	    				
	    				status = 1;
						
					}
	    				
	    			
	    		}
	    		
	    	}else if(type == 1){//玩家修改金币
	    		
	    		Integer currentUserGameId = currentUser.getGameAccountId();
	    		List<Mj_players> players = playersDao.findPlayersByRef(currentUserGameId);
	    		players.add(playersDao.findPlayerByUid(currentUserGameId));
	    		
	    		boolean isHave = false;
	    		
	    		for(Mj_players player : players) {
	    			
	    			if(player.get_id() == id) {
	    				
	    				isHave = true;
	    				
	    			}
	    			
	    		}
	    		
	    		if(isHave) {
    				
    				//修改玩家金币
    				Query query = Query.query(Criteria.where("_id").is(id));
    				Update update = new Update().inc("jinbi", amount);
    				mongoTemplate.updateFirst(query, update, "mj_players");
    				
    				//修改自己金币
    				userService.updateJinbi(currentUser.getId(), -amount);
    				
    				status = 1;
    				
    			}
	    		
	    	}
			
		}else {
			
			status = 2;
			
		}
		
    	return status;
    	
    }

    /**
     * 删除金币修改
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer jinbiId) {
//        jinbiService.deleteById(jinbiId);
        return SUCCESS_TIP;
    }

    /**
     * 修改金币修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
//        jinbiService.updateById(jinbi);
        return SUCCESS_TIP;
    }

    /**
     * 金币修改详情
     */
    @RequestMapping(value = "/detail/{jinbiId}")
    @ResponseBody
    public Object detail(@PathVariable("jinbiId") Integer jinbiId) {
//        return jinbiService.selectById(jinbiId);
    	return null;
    }
}
