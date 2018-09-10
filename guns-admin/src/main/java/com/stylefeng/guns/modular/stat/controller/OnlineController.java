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

import com.stylefeng.guns.modular.mongoModel.Mj_stat_online;
import com.stylefeng.guns.modular.stat.service.IOnlineService;

/**
 * 在线统计控制器
 *
 * @author fengshuonan
 * @Date 2018-09-06 19:05:46
 */
@Controller
@RequestMapping("/online")
public class OnlineController extends BaseController {

    private String PREFIX = "/stat/online/";

    @Autowired
    private IOnlineService onlineService;

    /**
     * 跳转到在线统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "online.html";
    }

    /**
     * 跳转到添加在线统计
     */
    @RequestMapping("/online_add")
    public String onlineAdd() {
        return PREFIX + "online_add.html";
    }

    /**
     * 跳转到修改在线统计
     */
    @RequestMapping("/online_update/{onlineId}")
    public String onlineUpdate(@PathVariable Integer onlineId, Model model) {
//        Online online = onlineService.selectById(onlineId);
//        model.addAttribute("item",online);
//        LogObjectHolder.me().set(online);
        return PREFIX + "online_edit.html";
    }

    /**
     * 获取在线统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String date) throws ParseException {
//        return onlineService.selectList(null);
    	
    	List<Mj_stat_online> ons = null;
    	
    	if(date != null && !date.equals("")) {
    		
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = sdf.parse(date);
    		
    		long time = dateTime.getTime() / 1000;
    		ons = onlineService.findROnlineByCondition(time);
    		
    	}else {
    		
    		ons = onlineService.findAll();
    		
    	}
    	
    	for(Mj_stat_online online : ons) {
    		
    		String time = DateUtil.fomatLongDateHH(online.getBeginTime());
    		online.setBeginTimeStr(time);
    		
    	}
    	
    	return ons;
    	
    }

    /**
     * 新增在线统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_stat_online online) {
//        onlineService.insert(online);
        return SUCCESS_TIP;
    }

    /**
     * 删除在线统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer onlineId) {
//        onlineService.deleteById(onlineId);
        return SUCCESS_TIP;
    }

    /**
     * 修改在线统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_stat_online online) {
//        onlineService.updateById(online);
        return SUCCESS_TIP;
    }

    /**
     * 在线统计详情
     */
    @RequestMapping(value = "/detail/{onlineId}")
    @ResponseBody
    public Object detail(@PathVariable("onlineId") Integer onlineId) {
//        return onlineService.selectById(onlineId);
    	return null;
    }
}
