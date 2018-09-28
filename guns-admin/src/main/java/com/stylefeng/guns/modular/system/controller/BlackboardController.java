package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.mongoDao.PlayersDao;
import com.stylefeng.guns.modular.mongoModel.Mj_players;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_agent_fl;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.INoticeService;
import com.stylefeng.guns.modular.system.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 总览信息
 *
 * @author fengshuonan
 * @Date 2017年3月4日23:05:54
 */
@Controller
@RequestMapping("/blackboard")
public class BlackboardController extends BaseController {

    @Autowired
    private INoticeService noticeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private PlayersDao playersDao;

    /**
     * 跳转到黑板
     */
    @RequestMapping("")
    public String blackboard(Model model) {
        List<Map<String, Object>> notices = noticeService.list(null);
        model.addAttribute("noticeList", notices);
        
        Integer userId = ShiroKit.getUser().getId();
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.selectById(userId);
        
        //下级代理数
        Map<Integer, List<User>> usersMap = userService.currentUsersLvList(user);
        int agentCount  = 0;
        
        //直系玩家数
        int playerCount = 0;
        
        for (Entry<Integer, List<User>> entry : usersMap.entrySet()) {
			//Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
			 //entry.getKey() ;entry.getValue(); entry.setValue();
			 //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
//			 System.out.println("key= " + entry.getKey() + " and value= "
//			             + entry.getValue());
			 
			 if(entry.getKey() > 0) {
				 
				 agentCount = agentCount + entry.getValue().size();
				 
			 }
			 
			 if(entry.getKey() == 0) {
				 
				 List<User> agentList = entry.getValue();
				 for(User agent : agentList) {
					 
					 List<Mj_players> players = playersDao.findPlayersByRef(agent.getGameAccountId());
					 playerCount = playerCount + players.size();
					 
				 }
				 
			 }
			 
		}
        
        //状态
        model.addAttribute("statusName", ConstantFactory.me().getStatusName((Integer) user.getStatus()));
        model.addAttribute("roleName", ConstantFactory.me().getRoleName((String) user.getRoleid()));
        
        model.addAttribute("user", user);
        model.addAttribute("agentCount", agentCount);
        model.addAttribute("playerCount", playerCount);
        
        return "/blackboard.html";
    }
}
