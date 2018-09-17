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

import com.stylefeng.guns.modular.mongoModel.Mj_stat_conversion;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_platform;
import com.stylefeng.guns.modular.stat.service.IPlaformService;

/**
 * 平台收入统计控制器
 *
 * @author fengshuonan
 * @Date 2018-09-17 18:58:17
 */
@Controller
@RequestMapping("/plaform")
public class PlaformController extends BaseController {

    private String PREFIX = "/stat/plaform/";

    @Autowired
    private IPlaformService plaformService;

    /**
     * 跳转到平台收入统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "plaform.html";
    }

    /**
     * 跳转到添加平台收入统计
     */
    @RequestMapping("/plaform_add")
    public String plaformAdd() {
        return PREFIX + "plaform_add.html";
    }

    /**
     * 跳转到修改平台收入统计
     */
    @RequestMapping("/plaform_update/{plaformId}")
    public String plaformUpdate(@PathVariable Integer plaformId, Model model) {
//        Plaform plaform = plaformService.selectById(plaformId);
//        model.addAttribute("item",plaform);
//        LogObjectHolder.me().set(plaform);
        return PREFIX + "plaform_edit.html";
    }

    /**
     * 获取平台收入统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String date) throws ParseException {
    	
    	List<Mj_stat_platform> plats = null;
    	
    	if(date != null && !date.equals("")) {
    		
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = sdf.parse(date);
    		
    		long time = dateTime.getTime() / 1000;
    		plats = plaformService.findPlatformByCondition(time);
    		
    	}else {
    		
    		plats = plaformService.findAll();
    		
    	}
    	
    	for(Mj_stat_platform platform : plats) {
    		
    		String time = DateUtil.fomatLongDate(platform.getTime());
    		platform.setTimeStr(time);
    		
    	}
    	
    	return plats;
    	
    }

    /**
     * 新增平台收入统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_stat_platform plaform) {
//        plaformService.insert(plaform);
        return SUCCESS_TIP;
    }

    /**
     * 删除平台收入统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer plaformId) {
//        plaformService.deleteById(plaformId);
        return SUCCESS_TIP;
    }

    /**
     * 修改平台收入统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_stat_platform plaform) {
//        plaformService.updateById(plaform);
        return SUCCESS_TIP;
    }

    /**
     * 平台收入统计详情
     */
    @RequestMapping(value = "/detail/{plaformId}")
    @ResponseBody
    public Object detail(@PathVariable("plaformId") Integer plaformId) {
//        return plaformService.selectById(plaformId);
    	return null;
    }
}
