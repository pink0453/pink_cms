package com.stylefeng.guns.modular.agent.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;

import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.DrawingsRecord;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.agent.service.IDrawingsRecordService;

/**
 * 代理提现记录控制器
 *
 * @author fengshuonan
 * @Date 2018-09-28 15:56:40
 */
@Controller
@RequestMapping("/drawingsRecord")
public class DrawingsRecordController extends BaseController {

    private String PREFIX = "/agent/drawingsRecord/";

    @Autowired
    private IDrawingsRecordService drawingsRecordService;
    @Autowired
    private IUserService userService;

    /**
     * 跳转到代理提现记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "drawingsRecord.html";
    }

    /**
     * 跳转到添加代理提现记录
     */
    @RequestMapping("/drawingsRecord_add")
    public String drawingsRecordAdd() {
        return PREFIX + "drawingsRecord_add.html";
    }

    /**
     * 跳转到修改代理提现记录
     */
    @RequestMapping("/drawingsRecord_update/{drawingsRecordId}")
    public String drawingsRecordUpdate(@PathVariable Integer drawingsRecordId, Model model) {
        DrawingsRecord drawingsRecord = drawingsRecordService.selectById(drawingsRecordId);
        model.addAttribute("item",drawingsRecord);
        LogObjectHolder.me().set(drawingsRecord);
        return PREFIX + "drawingsRecord_edit.html";
    }

    /**
     * 获取代理提现记录列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return drawingsRecordService.selectList(null);
    }

    /**
     * 新增代理提现记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DrawingsRecord drawingsRecord) {
    	
    	int rate = 10;
    	drawingsRecord.setRate(rate);
    	
    	Integer userId = ShiroKit.getUser().getId();
    	drawingsRecord.setUserId(userId);
    	
    	int status = 1;
    	drawingsRecord.setStatus(status);
    	
    	drawingsRecord.setCreateTime(new Date());
    	
    	ShiroUser suser = ShiroKit.getUser();
		User currentUser = userService.selectById(suser.getId());
		float fanli = currentUser.getFanli();
		
		if(fanli > drawingsRecord.getAmount()) {
			
			drawingsRecordService.insert(drawingsRecord);
			userService.updateFanli(currentUser.getGameAccountId(), -drawingsRecord.getAmount());
			
			return "充值成功";
			
		}else {
			
			return "余额不足";
			
		}
    	
    }

    /**
     * 删除代理提现记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer drawingsRecordId) {
        drawingsRecordService.deleteById(drawingsRecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改代理提现记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DrawingsRecord drawingsRecord) {
        drawingsRecordService.updateById(drawingsRecord);
        return SUCCESS_TIP;
    }

    /**
     * 代理提现记录详情
     */
    @RequestMapping(value = "/detail/{drawingsRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("drawingsRecordId") Integer drawingsRecordId) {
        return drawingsRecordService.selectById(drawingsRecordId);
    }
}
