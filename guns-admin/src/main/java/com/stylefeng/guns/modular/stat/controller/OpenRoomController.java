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

import com.stylefeng.guns.modular.mongoModel.Mj_stat_open_room;
import com.stylefeng.guns.modular.stat.service.IOpenRoomService;

/**
 * 开房统计控制器
 *
 * @author fengshuonan
 * @Date 2018-09-07 18:49:12
 */
@Controller
@RequestMapping("/openRoom")
public class OpenRoomController extends BaseController {

    private String PREFIX = "/stat/openRoom/";

    @Autowired
    private IOpenRoomService openRoomService;

    /**
     * 跳转到开房统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "openRoom.html";
    }

    /**
     * 跳转到添加开房统计
     */
    @RequestMapping("/openRoom_add")
    public String openRoomAdd() {
        return PREFIX + "openRoom_add.html";
    }

    /**
     * 跳转到修改开房统计
     */
    @RequestMapping("/openRoom_update/{openRoomId}")
    public String openRoomUpdate(@PathVariable Integer openRoomId, Model model) {
//        OpenRoom openRoom = openRoomService.selectById(openRoomId);
//        model.addAttribute("item",openRoom);
//        LogObjectHolder.me().set(openRoom);
        return PREFIX + "openRoom_edit.html";
    }

    /**
     * 获取开房统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String date) throws ParseException{
//        return openRoomService.selectList(null);
    	List<Mj_stat_open_room> rooms = null;
    	
    	if(date != null && !date.equals("")) {
    		
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = sdf.parse(date);
    		
    		long time = dateTime.getTime() / 1000;
    		rooms = openRoomService.findORoomByCondition(time);
    		
    	}else {
    		
    		rooms = openRoomService.findAll();
    		
    	}
    	
    	for(Mj_stat_open_room room : rooms) {
    		
    		String time = DateUtil.fomatLongDate(room.getTime());
    		room.setTimeStr(time);
    		
    	}
    	
    	return rooms;
    }

    /**
     * 新增开房统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_stat_open_room openRoom) {
//        openRoomService.insert(openRoom);
        return SUCCESS_TIP;
    }

    /**
     * 删除开房统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer openRoomId) {
//        openRoomService.deleteById(openRoomId);
        return SUCCESS_TIP;
    }

    /**
     * 修改开房统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_stat_open_room openRoom) {
//        openRoomService.updateById(openRoom);
        return SUCCESS_TIP;
    }

    /**
     * 开房统计详情
     */
    @RequestMapping(value = "/detail/{openRoomId}")
    @ResponseBody
    public Object detail(@PathVariable("openRoomId") Integer openRoomId) {
//        return openRoomService.selectById(openRoomId);
    	return null;
    }
}
