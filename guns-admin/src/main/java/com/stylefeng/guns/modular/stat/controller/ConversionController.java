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
import com.stylefeng.guns.modular.mongoModel.Mj_stat_register;
import com.stylefeng.guns.modular.stat.service.IConversionService;

/**
 * 兑换收入统计控制器
 *
 * @author fengshuonan
 * @Date 2018-09-17 15:33:54
 */
@Controller
@RequestMapping("/conversion")
public class ConversionController extends BaseController {

    private String PREFIX = "/stat/conversion/";

    @Autowired
    private IConversionService conversionService;

    /**
     * 跳转到兑换收入统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "conversion.html";
    }

    /**
     * 跳转到添加兑换收入统计
     */
    @RequestMapping("/conversion_add")
    public String conversionAdd() {
        return PREFIX + "conversion_add.html";
    }

    /**
     * 跳转到修改兑换收入统计
     */
    @RequestMapping("/conversion_update/{conversionId}")
    public String conversionUpdate(@PathVariable Integer conversionId, Model model) {
//        Conversion conversion = conversionService.selectById(conversionId);
//        model.addAttribute("item",conversion);
//        LogObjectHolder.me().set(conversion);
        return PREFIX + "conversion_edit.html";
    }

    /**
     * 获取兑换收入统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String date) throws ParseException {

    	List<Mj_stat_conversion> cons = null;
    	
    	if(date != null && !date.equals("")) {
    		
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = sdf.parse(date);
    		
    		long time = dateTime.getTime() / 1000;
    		cons = conversionService.findConsByCondition(time);
    		
    	}else {
    		
    		cons = conversionService.findAll();
    		
    	}
    	
    	for(Mj_stat_conversion conversion : cons) {
    		
    		String time = DateUtil.fomatLongDate(conversion.getTime());
    		conversion.setTimeStr(time);
    		
    	}
    	
    	return cons;
    	
    }

    /**
     * 新增兑换收入统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_stat_conversion conversion) {
//        conversionService.insert(conversion);
        return SUCCESS_TIP;
    }

    /**
     * 删除兑换收入统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer conversionId) {
//        conversionService.deleteById(conversionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改兑换收入统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_stat_conversion conversion) {
//        conversionService.updateById(conversion);
        return SUCCESS_TIP;
    }

    /**
     * 兑换收入统计详情
     */
    @RequestMapping(value = "/detail/{conversionId}")
    @ResponseBody
    public Object detail(@PathVariable("conversionId") Integer conversionId) {
//        return conversionService.selectById(conversionId);
    	return null;
    }
}
