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
import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_register;
import com.stylefeng.guns.modular.stat.service.IRegisterService;

/**
 * 注册量统计控制器
 *
 * @author fengshuonan
 * @Date 2018-09-05 16:51:49
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {

    private String PREFIX = "/stat/register/";

    @Autowired
    private IRegisterService registerService;

    /**
     * 跳转到注册量统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "register.html";
    }

    /**
     * 跳转到添加注册量统计
     */
    @RequestMapping("/register_add")
    public String registerAdd() {
        return PREFIX + "register_add.html";
    }

    /**
     * 跳转到修改注册量统计
     */
    @RequestMapping("/register_update/{registerId}")
    public String registerUpdate(@PathVariable Integer registerId, Model model) {
//        Register register = registerService.selectById(registerId);
//        model.addAttribute("item",register);
//        LogObjectHolder.me().set(register);
        return PREFIX + "register_edit.html";
    }

    /**
     * 获取注册量统计列表
     * @throws ParseException 
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String date) throws ParseException {
    	
    	List<Mj_stat_register> regs = null;
    	
    	if(date != null && !date.equals("")) {
    		
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = sdf.parse(date);
    		
    		long time = dateTime.getTime() / 1000;
        	regs = registerService.findRegsByCondition(time);
    		
    	}else {
    		
    		regs = registerService.findAll();
    		
    	}
    	
    	for(Mj_stat_register register : regs) {
    		
    		String time = DateUtil.fomatLongDate(register.getCreateTime());
    		register.setTimeStr(time);
    		
    	}
    	
    	return regs;
    }

    /**
     * 新增注册量统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_stat_register register) {
//        registerService.insert(register);
        return SUCCESS_TIP;
    }

    /**
     * 删除注册量统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer registerId) {
//        registerService.deleteById(registerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改注册量统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_stat_register register) {
//        registerService.updateById(register);
        return SUCCESS_TIP;
    }

    /**
     * 注册量统计详情
     */
    @RequestMapping(value = "/detail/{registerId}")
    @ResponseBody
    public Object detail(@PathVariable("registerId") Integer registerId) {
//        return registerService.selectById(registerId);
        return null;
    }
}
