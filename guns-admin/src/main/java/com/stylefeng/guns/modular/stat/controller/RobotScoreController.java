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
import com.stylefeng.guns.modular.mongoModel.Mj_stat_robot_score;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_system_water;
import com.stylefeng.guns.modular.stat.service.IRobotScoreService;

/**
 * 机器人收入统计控制器
 *
 * @author fengshuonan
 * @Date 2018-09-13 11:59:10
 */
@Controller
@RequestMapping("/robotScore")
public class RobotScoreController extends BaseController {

    private String PREFIX = "/stat/robotScore/";

    @Autowired
    private IRobotScoreService robotScoreService;

    /**
     * 跳转到机器人收入统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "robotScore.html";
    }

    /**
     * 跳转到添加机器人收入统计
     */
    @RequestMapping("/robotScore_add")
    public String robotScoreAdd() {
        return PREFIX + "robotScore_add.html";
    }

    /**
     * 跳转到修改机器人收入统计
     */
    @RequestMapping("/robotScore_update/{robotScoreId}")
    public String robotScoreUpdate(@PathVariable Integer robotScoreId, Model model) {
//        RobotScore robotScore = robotScoreService.selectById(robotScoreId);
//        model.addAttribute("item",robotScore);
//        LogObjectHolder.me().set(robotScore);
        return PREFIX + "robotScore_edit.html";
    }

    /**
     * 获取机器人收入统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String date) throws ParseException {
    	
    	List<Mj_stat_robot_score> robots = null;
    	
    	if(date != null && !date.equals("")) {
    		
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = sdf.parse(date);
    		
    		long time = dateTime.getTime() / 1000;
    		robots = robotScoreService.findRobotScoreByCondition(time);
    		
    	}else {
    		
    		robots = robotScoreService.findAll();
    		
    	}
    	
    	for(Mj_stat_robot_score system : robots) {
    		
    		String time = DateUtil.fomatLongDate(system.getTime());
    		system.setTimeStr(time);
    		
    	}
    	
    	return robots;
    }

    /**
     * 新增机器人收入统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_stat_robot_score robotScore) {
//        robotScoreService.insert(robotScore);
        return SUCCESS_TIP;
    }

    /**
     * 删除机器人收入统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer robotScoreId) {
//        robotScoreService.deleteById(robotScoreId);
        return SUCCESS_TIP;
    }

    /**
     * 修改机器人收入统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_stat_robot_score robotScore) {
//        robotScoreService.updateById(robotScore);
        return SUCCESS_TIP;
    }

    /**
     * 机器人收入统计详情
     */
    @RequestMapping(value = "/detail/{robotScoreId}")
    @ResponseBody
    public Object detail(@PathVariable("robotScoreId") Integer robotScoreId) {
//        return robotScoreService.selectById(robotScoreId);
    	return null;
    }
}
