package com.stylefeng.guns.modular.stat.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.DateUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_agent_fl;
import com.stylefeng.guns.modular.stat.service.IAgentFlStatService;

/**
 * 代理返利统计控制器
 *
 * @author fengshuonan
 * @Date 2018-09-12 18:18:22
 */
@Controller
@RequestMapping("/agentFlStat")
public class AgentFlStatController extends BaseController {

    private String PREFIX = "/stat/agentFlStat/";

    @Autowired
    private IAgentFlStatService agentFlService;

    /**
     * 跳转到代理返利统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "agentFl.html";
    }

    /**
     * 跳转到添加代理返利统计
     */
    @RequestMapping("/agentFl_add")
    public String agentFlAdd() {
        return PREFIX + "agentFl_add.html";
    }

    /**
     * 跳转到修改代理返利统计
     */
    @RequestMapping("/agentFl_update/{agentFlId}")
    public String agentFlUpdate(@PathVariable Integer agentFlId, Model model) {
//        AgentFl agentFl = agentFlService.selectById(agentFlId);
//        model.addAttribute("item",agentFl);
//        LogObjectHolder.me().set(agentFl);
        return PREFIX + "agentFl_edit.html";
    }

    /**
     * 获取代理返利统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String date) throws ParseException {
    	
    	List<Mj_stat_agent_fl> afls = null;
    	
    	if(date != null && !date.equals("")) {
    		
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = sdf.parse(date);
    		
    		long time = dateTime.getTime() / 1000;
    		afls = agentFlService.findAgentFlStatByCondition(time);
    		
    	}else {
    		
    		afls = agentFlService.findAll();
    		
    	}
    	
    	for(Mj_stat_agent_fl fl : afls) {
    		
    		String time = DateUtil.fomatLongDate(fl.getTime());
    		fl.setTimeStr(time);
    		
    	}
    	
    	return afls;
    	
    }

    /**
     * 新增代理返利统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_stat_agent_fl agentFl) {
//        agentFlService.insert(agentFl);
        return SUCCESS_TIP;
    }

    /**
     * 删除代理返利统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer agentFlId) {
//        agentFlService.deleteById(agentFlId);
        return SUCCESS_TIP;
    }

    /**
     * 修改代理返利统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_stat_agent_fl agentFl) {
//        agentFlService.updateById(agentFl);
        return SUCCESS_TIP;
    }

    /**
     * 代理返利统计详情
     */
    @RequestMapping(value = "/detail/{agentFlId}")
    @ResponseBody
    public Object detail(@PathVariable("agentFlId") Integer agentFlId) {
//        return agentFlService.selectById(agentFlId);
    	return null;
    }
}
