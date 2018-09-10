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

import com.stylefeng.guns.modular.mongoModel.Mj_stat_hour_room;
import com.stylefeng.guns.modular.stat.service.IHourRoomService;

/**
 * 房间统计控制器
 *
 * @author fengshuonan
 * @Date 2018-09-10 15:11:44
 */
@Controller
@RequestMapping("/hourRoom")
public class HourRoomController extends BaseController {

    private String PREFIX = "/stat/hourRoom/";

    @Autowired
    private IHourRoomService hourRoomService;

    /**
     * 跳转到房间统计首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hourRoom.html";
    }

    /**
     * 跳转到添加房间统计
     */
    @RequestMapping("/hourRoom_add")
    public String hourRoomAdd() {
        return PREFIX + "hourRoom_add.html";
    }

    /**
     * 跳转到修改房间统计
     */
    @RequestMapping("/hourRoom_update/{hourRoomId}")
    public String hourRoomUpdate(@PathVariable Integer hourRoomId, Model model) {
//        HourRoom hourRoom = hourRoomService.selectById(hourRoomId);
//        model.addAttribute("item",hourRoom);
//        LogObjectHolder.me().set(hourRoom);
        return PREFIX + "hourRoom_edit.html";
    }

    /**
     * 获取房间统计列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String date) throws ParseException {
    	List<Mj_stat_hour_room> rooms = null;
    	
    	if(date != null && !date.equals("")) {
    		
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = sdf.parse(date);
    		
    		long time = dateTime.getTime() / 1000;
    		rooms = hourRoomService.findRRoomByCondition(time);
    		
    	}else {
    		
    		rooms = hourRoomService.findAll();
    		
    	}
    	
    	for(Mj_stat_hour_room room : rooms) {
    		
    		String time = DateUtil.fomatLongDateHH(room.getTime());
    		room.setTimeStr(time);
    		
    	}
    	
    	return rooms;
    }

    /**
     * 新增房间统计
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Mj_stat_hour_room hourRoom) {
//        hourRoomService.insert(hourRoom);
        return SUCCESS_TIP;
    }

    /**
     * 删除房间统计
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer hourRoomId) {
//        hourRoomService.deleteById(hourRoomId);
        return SUCCESS_TIP;
    }

    /**
     * 修改房间统计
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Mj_stat_hour_room hourRoom) {
//        hourRoomService.updateById(hourRoom);
        return SUCCESS_TIP;
    }

    /**
     * 房间统计详情
     */
    @RequestMapping(value = "/detail/{hourRoomId}")
    @ResponseBody
    public Object detail(@PathVariable("hourRoomId") Integer hourRoomId) {
//        return hourRoomService.selectById(hourRoomId);
    	return null;
    }
}
