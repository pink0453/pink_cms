package com.stylefeng.guns.modular.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.modular.mongoDao.CardDao;
import com.stylefeng.guns.modular.mongoDao.GameBindDao;
import com.stylefeng.guns.modular.mongoDao.PlayersDao;
import com.stylefeng.guns.modular.mongoModel.Mj_game_bind;
import com.stylefeng.guns.modular.mongoModel.Mj_match_cards;
import com.stylefeng.guns.modular.mongoModel.Mj_players;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;

/**
 * 接口服务类
 * @author admin
 *
 */
@Controller
@RequestMapping("/web")
public class WebController extends BaseController {

	@Autowired
	private IUserService userService;
	@Autowired
	private CardDao cardDao;
	@Autowired
	private PlayersDao playersDao;
	@Autowired
	private GameBindDao gameBindDao;
	
	/**
	 * 返回代理详情
	 * @return
	
	 * @author linchenguang  
	 * @throws Exception 
	 * @date 2018年8月3日
	 */
	@RequestMapping(value="/getAgent")
	@ResponseBody
	public Object getAgent() throws Exception{
		
		String gameAccountId = getPara("GAME_ACCOUNT_ID");
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
			
		User user = userService.getByGameAccountId(gameAccountId);
		
		if(user != null){
			errInfo = "error";
			String roleName = ConstantFactory.me().getRoleName(user.getRoleid());
			map.put("roleName", roleName);
		}
		
		map.put("result", errInfo);				//返回结果
		map.put("data", user);
		
		return map;
	}
	
	/**
	 * 通过代理编号获取代理
	 * 
	
	 * @author linchenguang  
	 * @throws Exception 
	 * @date 2018年8月7日
	 */
	@RequestMapping(value="/getAgentByCode")
	@ResponseBody
	public Object getAgentByCode() throws Exception {
		
		String userId = getPara("USER_ID");
		
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
			
		User user = userService.selectById(userId);
		
		String roleName = ConstantFactory.me().getRoleName(user.getRoleid());
		
		if(user != null){
			errInfo = "error";
		}
		
		map.put("result", errInfo);				//返回结果
		map.put("data", user);
		map.put("roleName", roleName);
		
		return map;
		
	}
	
	/**
	 * 自动发牌接口
	 * @return
	 */
	@RequestMapping(value="/autoFapi")
	@ResponseBody
	public Object autoFapi(@RequestBody Mj_match_cards cards) {
		
		List<Mj_match_cards> allcards = cardDao.getCard(cards.getMap_id(), cards.getPlayer_id());
		
		for(Mj_match_cards card1 : allcards) {
			
			cardDao.delete(card1);
			
		}
		
		cardDao.insert(cards);
		
		return "success";
		
	}
	
	/**
	 * 删除牌型
	 * @param card
	 * @return
	 */
	@RequestMapping(value="/delCard")
	@ResponseBody
	public Object delCard(@RequestBody Mj_match_cards card) {
		
		List<Mj_match_cards> cards = cardDao.getCard(card.getMap_id(), card.getPlayer_id());
		
		for(Mj_match_cards card1 : cards) {
			
			cardDao.delete(card1);
			
		}
		
		return "success";
		
	}
	
	/**
	 * 通过获取玩家信息
	 * @return
	 */
	@RequestMapping(value="/getPlayerInfo")
	@ResponseBody
	public Object getPlayerInfo() {
		
		String playerId = getPara("uid");
		
		if(playerId != null && !playerId.equals("")) {
			
			Mj_players player = playersDao.findPlayerByUid(Integer.parseInt(playerId));
			return player;
			
		}else {
			
			return "error";
			
		}
		
		
	}
	
    /**
     * 记录用户扫码下载信息
     */
    @RequestMapping(value="/recBind")
    @ResponseBody
    public Object recBind() throws Exception{
    	
    	String gameAccountId = getPara("uid");
    	
    	if(gameAccountId != null && !gameAccountId.equals("")) {
    		
    		Integer uid = Integer.parseInt(gameAccountId);
        	String ip = getPara("ip");
        	
        	//判断是否为代理
        	User user = userService.getByGameAccountId(gameAccountId);
        	if(user != null) {
        		
        		Mj_game_bind gameBind = new Mj_game_bind();
//            	gameBind.setId(AppUtil.getUUID());
            	gameBind.setUid(uid);
            	gameBind.setIp(ip);
            	gameBind.setCreatTime(System.currentTimeMillis() / 1000);
            	
            	gameBindDao.insert(gameBind);
        		
        	}
    		
    	}
    	
    	return "success";
    	
    }
	
}
