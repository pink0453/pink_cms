package com.stylefeng.guns.modular.stat.controller;

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

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.DateUtil;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_active;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_register;
import com.stylefeng.guns.modular.stat.service.IActiveService;

/**
 * 活跃人数控制器
 *
 * @author fengshuonan
 * @Date 2018-09-05 19:03:18
 */
@Controller
@RequestMapping("/active")
public class ActiveController extends BaseController {

    private String PREFIX = "/stat/active/";

    @Autowired
    private IActiveService activeService;

    /**
     * 跳转到活跃人数首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "active.html";
    }

    /**
     * 跳转到添加活跃人数
     */
    @RequestMapping("/active_add")
    public String activeAdd() {
        return PREFIX + "active_add.html";
    }

    /**
     * 跳转到修改活跃人数
     */
    @RequestMapping("/active_update/{activeId}")
    public String activeUpdate(@PathVariable Integer activeId, Model model) {
//        Active active = activeService.selectById(activeId);
//        model.addAttribute("item",active);
//        LogObjectHolder.me().set(active);
        return PREFIX + "active_edit.html";
    }

    /**
     * 获取活跃人数列表
     * @throws ParseException 
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String date) throws ParseException {

    	List<Mj_stat_active> acts = null;
    	
    	if(date != null && !date.equals("")) {
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = sdf.parse(date);
    		
    		long time = dateTime.getTime() / 1000;
    		acts = activeService.findActsByCondition(time);
    		
    	}else {
    		
    		acts = activeService.findAll();
    		
    	}
    	
    	for(Mj_stat_active active : acts) {
    		
    		String time = DateUtil.fomatLongDate(active.getTime());
    		active.setTimeStr(time);
    		
    	}
    	
    	return acts;
    	
    }

    /**
     * 新增活跃人数
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_stat_register active) {
//        activeService.insert(active);
        return SUCCESS_TIP;
    }

    /**
     * 删除活跃人数
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer activeId) {
//        activeService.deleteById(activeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改活跃人数
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_stat_register active) {
//        activeService.updateById(active);
        return SUCCESS_TIP;
    }

    /**
     * 活跃人数详情
     */
    @RequestMapping(value = "/detail/{activeId}")
    @ResponseBody
    public Object detail(@PathVariable("activeId") Integer activeId) {
//        return activeService.selectById(activeId);
    	return null;
    }
}
