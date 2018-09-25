package com.stylefeng.guns.modular.recharge.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.ConstantUtil;

import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.OrderRecord;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.warpper.RechargeAgentWarpper;
import com.stylefeng.guns.modular.mongoDao.PlayersDao;
import com.stylefeng.guns.modular.mongoModel.Mj_players;
import com.stylefeng.guns.modular.recharge.service.IOrderRecordService;

/**
 * 代理充值明细控制器
 *
 * @author fengshuonan
 * @Date 2018-09-20 16:16:43
 */
@Controller
@RequestMapping("/orderAgentRecord")
public class OrderAgentRecordController extends BaseController {

    private String PREFIX = "/recharge/orderAgentRecord/";

    @Autowired
    private IOrderRecordService orderAgentRecordService;
    @Autowired
    private IUserService userService;
    @Autowired
    private PlayersDao playerDao;

    /**
     * 跳转到代理充值明细首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "orderAgentRecord.html";
    }

    /**
     * 跳转到添加代理充值明细
     */
    @RequestMapping("/orderAgentRecord_add")
    public String orderAgentRecordAdd() {
        return PREFIX + "orderAgentRecord_add.html";
    }

    /**
     * 跳转到修改代理充值明细
     */
    @RequestMapping("/orderAgentRecord_update/{orderAgentRecordId}")
    public String orderAgentRecordUpdate(@PathVariable Integer orderAgentRecordId, Model model) {
    	OrderRecord orderAgentRecord = orderAgentRecordService.selectById(orderAgentRecordId);
        model.addAttribute("item",orderAgentRecord);
        LogObjectHolder.me().set(orderAgentRecord);
        return PREFIX + "orderAgentRecord_edit.html";
    }

    /**
     * 获取代理充值明细列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer playerId) {
    	
    	int type = 1;
    	
    	List<Map<String, Object>> usersMap = new ArrayList<>();
    	Page<OrderRecord> page = new PageFactory<OrderRecord>().defaultPage();
    	
		if(!ShiroKit.hasAnyRoles(ConstantUtil.ADMIN_ROLES)) {
			
			ShiroUser suser = ShiroKit.getUser();
			User currentUser = userService.selectById(suser.getId());
			Map<Integer, List<User>>  lvMap = userService.currentUsersLvList(currentUser);
			
			//直属代理集合
			List<User> users = lvMap.get(1);
			
			for(User user : users) {
				
				
				List<Mj_players> players = playerDao.findPlayersByRef(user.getId());
	    		List<Integer> ids = new ArrayList<>();
	    		for(Mj_players player : players) {
	    			
	    			ids.add(player.get_id());
	    			
	    		}
	    		ids.add(user.getId());
	    		
	    		if(ids.size() > 0) {
	    			
	    			usersMap.addAll(orderAgentRecordService.getRechargeByIds(ids, beginTime, endTime, playerId, page.getOrderByField(), page.isAsc(), type));
	    			
	    		}
				
			}
			
		}else {
			
			usersMap = orderAgentRecordService.getRecharges(beginTime, endTime, playerId, page.getOrderByField(), page.isAsc(), type);
			
		}
		
    	return new RechargeAgentWarpper(usersMap).warp();
    	
    }

    /**
     * 新增代理充值明细
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(OrderRecord orderAgentRecord) {
        orderAgentRecordService.insert(orderAgentRecord);
        return SUCCESS_TIP;
    }

    /**
     * 删除代理充值明细
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer orderAgentRecordId) {
        orderAgentRecordService.deleteById(orderAgentRecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改代理充值明细
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(OrderRecord orderAgentRecord) {
        orderAgentRecordService.updateById(orderAgentRecord);
        return SUCCESS_TIP;
    }

    /**
     * 代理充值明细详情
     */
    @RequestMapping(value = "/detail/{orderAgentRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("orderAgentRecordId") Integer orderAgentRecordId) {
        return orderAgentRecordService.selectById(orderAgentRecordId);
    }
}
