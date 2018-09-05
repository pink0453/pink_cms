package com.stylefeng.guns.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stylefeng.guns.modular.system.model.Mj_players;
import com.stylefeng.guns.modular.system.model.Mj_stat_register;
import com.stylefeng.guns.modular.system.mongoDao.PlayersDao;
import com.stylefeng.guns.modular.system.mongoDao.StatRegisterDao;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

	@Autowired
	private PlayersDao playersDao;
	@Autowired
	private StatRegisterDao registerDao;
	
	/**
	 * 每日0点查询今日注册用户
	 */
	@Scheduled(cron = "0 0 0 * * ?")
    public void reportCurrentTime(){
		
		System.out.println("----------------------开始统计今日注册量----------------------");
		
		long nowTime = System.currentTimeMillis() / 1000;
		long beforeTime = nowTime - 86400;//前一天
		List<Mj_players> players = playersDao.findByTimeRange(beforeTime, nowTime);
		
		Integer regCount = players.size();
		
		Mj_stat_register register = new Mj_stat_register();
		register.setCreateTime(nowTime);
		register.setRegCount(regCount);
		register.setRingGrowth(0);
		register.setRingGrowthRate(0);
		register.setYoyGrowth(0);
		register.setYoyGrowthRate(0);
		
		registerDao.insert(register);
		
		System.out.println("----------------------结束统计今日注册量----------------------");
		
    }
	
}
