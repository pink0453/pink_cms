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

import com.stylefeng.guns.modular.mongoModel.Mj_stat_register;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_system_water;
import com.stylefeng.guns.modular.stat.service.ISystemWaterService;

/**
 * 系统抽水统计控制器
 *
 * @author fengshuonan
 * @Date 2018-09-12 16:17:27
 */
@Controller
@RequestMapping("/systemWater")
public class SystemWaterController extends BaseController {

    private String PREFIX = "/stat/systemWater/";

    @Autowired
    private ISystemWaterService systemWaterService;

    /**
     * 跳转到系统抽水统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "systemWater.html";
    }

    /**
     * 跳转到添加系统抽水统计
     */
    @RequestMapping("/systemWater_add")
    public String systemWaterAdd() {
        return PREFIX + "systemWater_add.html";
    }

    /**
     * 跳转到修改系统抽水统计
     */
    @RequestMapping("/systemWater_update/{systemWaterId}")
    public String systemWaterUpdate(@PathVariable Integer systemWaterId, Model model) {
//        SystemWater systemWater = systemWaterService.selectById(systemWaterId);
//        model.addAttribute("item",systemWater);
//        LogObjectHolder.me().set(systemWater);
        return PREFIX + "systemWater_edit.html";
    }

    /**
     * 获取系统抽水统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String date) throws ParseException {
    	
    	List<Mj_stat_system_water> sys = null;
    	
    	if(date != null && !date.equals("")) {
    		
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = sdf.parse(date);
    		
    		long time = dateTime.getTime() / 1000;
    		sys = systemWaterService.findSystemByCondition(time);
    		
    	}else {
    		
    		sys = systemWaterService.findAll();
    		
    	}
    	
    	for(Mj_stat_system_water system : sys) {
    		
    		String time = DateUtil.fomatLongDate(system.getTime());
    		system.setTimeStr(time);
    		
    	}
    	
    	return sys;
    	
    }

    /**
     * 新增系统抽水统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_stat_system_water systemWater) {
//        systemWaterService.insert(systemWater);
        return SUCCESS_TIP;
    }

    /**
     * 删除系统抽水统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer systemWaterId) {
//        systemWaterService.deleteById(systemWaterId);
        return SUCCESS_TIP;
    }

    /**
     * 修改系统抽水统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_stat_system_water systemWater) {
//        systemWaterService.updateById(systemWater);
        return SUCCESS_TIP;
    }

    /**
     * 系统抽水统计详情
     */
    @RequestMapping(value = "/detail/{systemWaterId}")
    @ResponseBody
    public Object detail(@PathVariable("systemWaterId") Integer systemWaterId) {
//        return systemWaterService.selectById(systemWaterId);
    	return null;
    }
}
