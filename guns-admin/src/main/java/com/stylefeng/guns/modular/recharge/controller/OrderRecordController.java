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
import com.stylefeng.guns.modular.system.warpper.RechargePlayerWarpper;
import com.stylefeng.guns.modular.mongoDao.PlayersDao;
import com.stylefeng.guns.modular.mongoModel.Mj_players;
import com.stylefeng.guns.modular.recharge.service.IOrderRecordService;

/**
 * 玩家充值控制器
 *
 * @author fengshuonan
 * @Date 2018-09-19 20:56:14
 */
@Controller
@RequestMapping("/orderRecord")
public class OrderRecordController extends BaseController {

    private String PREFIX = "/recharge/orderRecord/";

    @Autowired
    private IOrderRecordService orderRecordService;
    @Autowired
    private IUserService userService;
    @Autowired
    private PlayersDao playerDao;

    /**
     * 跳转到玩家充值首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "orderRecord.html";
    }

    /**
     * 跳转到添加玩家充值
     */
    @RequestMapping("/orderRecord_add")
    public String orderRecordAdd() {
        return PREFIX + "orderRecord_add.html";
    }

    /**
     * 跳转到修改玩家充值
     */
    @RequestMapping("/orderRecord_update/{orderRecordId}")
    public String orderRecordUpdate(@PathVariable Integer orderRecordId, Model model) {
        OrderRecord orderRecord = orderRecordService.selectById(orderRecordId);
        model.addAttribute("item",orderRecord);
        LogObjectHolder.me().set(orderRecord);
        return PREFIX + "orderRecord_edit.html";
    }

    /**
     * 获取玩家充值列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer playerId) {
        
    	int type = 0;
    	
    	Page<OrderRecord> page = new PageFactory<OrderRecord>().defaultPage();
        List<Map<String, Object>> result = orderRecordService.getRecharges(beginTime, endTime, playerId, page.getOrderByField(), page.isAsc(), type);
    	
    	if(!ShiroKit.hasAnyRoles(ConstantUtil.ADMIN_ROLES)) {
    		
    		ShiroUser suser = ShiroKit.getUser();
    		User currentUser = userService.selectById(suser.getId());
    		int curGameId = currentUser.getGameAccountId();
    		
    		//查询当前登录用户旗下所有玩家集合
    		List<Mj_players> players = playerDao.findPlayersByRef(curGameId);
    		List<Integer> ids = new ArrayList<>();
    		for(Mj_players player : players) {
    			
    			ids.add(player.get_id());
    			
    		}
    		ids.add(curGameId);
    		
    		if(ids.size() > 0) {
    			
    			result = orderRecordService.getRechargeByIds(ids, beginTime, endTime, playerId, page.getOrderByField(), page.isAsc(), type);
    			
    		}else {
    			
    			return null;
    			
    		}
    		
    	}
    	
    	return new RechargePlayerWarpper(result).warp();
    	
    }

    /**
     * 新增玩家充值
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(OrderRecord orderRecord) {
        orderRecordService.insert(orderRecord);
        return SUCCESS_TIP;
    }

    /**
     * 删除玩家充值
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer orderRecordId) {
        orderRecordService.deleteById(orderRecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改玩家充值
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(OrderRecord orderRecord) {
        orderRecordService.updateById(orderRecord);
        return SUCCESS_TIP;
    }

    /**
     * 玩家充值详情
     */
    @RequestMapping(value = "/detail/{orderRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("orderRecordId") Integer orderRecordId) {
        return orderRecordService.selectById(orderRecordId);
    }
}
