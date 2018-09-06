package com.stylefeng.guns.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stylefeng.guns.modular.system.model.Mj_players;
import com.stylefeng.guns.modular.system.model.Mj_stat_active;
import com.stylefeng.guns.modular.system.model.Mj_stat_online;
import com.stylefeng.guns.modular.system.model.Mj_stat_register;
import com.stylefeng.guns.modular.system.mongoDao.PlayersDao;
import com.stylefeng.guns.modular.system.mongoDao.StatActiveDao;
import com.stylefeng.guns.modular.system.mongoDao.StatOnlineDao;
import com.stylefeng.guns.modular.system.mongoDao.StatRegisterDao;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

	@Autowired
	private PlayersDao playersDao;
	@Autowired
	private StatRegisterDao registerDao;
	@Autowired
	private StatActiveDao activeDao;
	@Autowired
	private StatOnlineDao onlineDao;
	
	Map<Integer, Integer> onMap = new HashMap<Integer, Integer>();
	
	/**
	 * 每日0点查询今日注册用户
	 */
	@Scheduled(cron = "0 0 0 * * ?")
    public void statRegisterSch(){
		
		System.out.println("----------------------开始统计今日注册量----------------------");
		
		long nowTime = System.currentTimeMillis() / 1000;
		long beforeTime = nowTime - 86400;//前一天
		List<Mj_players> players = playersDao.findByTimeRange(beforeTime, nowTime);
		
		Integer regCount = players.size();
		
		//保存时间向前推1个小时,保证算出来的数据在当天
		long saveTime = nowTime - 3600;
		
		Mj_stat_register register = new Mj_stat_register();
		register.setCreateTime(saveTime);
		register.setRegCount(regCount);
		register.setRingGrowth(0);
		register.setRingGrowthRate(0);
		register.setYoyGrowth(0);
		register.setYoyGrowthRate(0);
		
		registerDao.insert(register);
		
		System.out.println("----------------------结束统计今日注册量----------------------");
		
    }
	
	/**
	 * 每日0点统计活跃人数
	 */
	//0/5 * * * * ? 
	@Scheduled(cron = "0 0 0 * * ?")
	public void statActiveSch() {
		
		System.out.println("----------------------开始统计今日活跃人数----------------------");
		
		long nowTime = System.currentTimeMillis() / 1000;
		long beforeTime = nowTime - 86400;//前一天
		List<Mj_players> players = playersDao.findActiveByTimeRange(beforeTime, nowTime);
		
		Integer actCount = players.size();
		
		//保存时间向前推1个小时,保证算出来的数据在当天
		long saveTime = nowTime - 3600;
		
		Mj_stat_active active = new Mj_stat_active();
		active.setActCount(actCount);
		active.setTime(saveTime);
		active.setRingGrowth(0);
		active.setRingGrowthRate(0);
		active.setYoyGrowth(0);
		active.setYoyGrowthRate(0);
		
		activeDao.insert(active);
		
		System.out.println("----------------------结束统计今日活跃人数----------------------");
		
	}
	
	/**
	 * 统计整点在线
	 * 50,00,10统计三次取最大
	 */
	@Scheduled(cron = "0 0,10,50 * * * ? ")
	public void statOnlineSch() {
		System.out.println("----------------------开始统计整点在线人数----------------------");
		
		Date date = new Date();
		int min = date.getMinutes();
		
		if(min == 50) {
			
			onMap.clear();
			List<Mj_players> players = playersDao.findIsOnline(false);
			onMap.put(min, players.size());

		}else if(min == 00) {
			
			List<Mj_players> players = playersDao.findIsOnline(false);
			onMap.put(min, players.size());
			
		}else if(min == 10) {
			
			List<Mj_players> players = playersDao.findIsOnline(false);
			onMap.put(min, players.size());
			
			List<Integer> counts = new ArrayList<Integer>();
			
			for (Entry<Integer, Integer> entry : onMap.entrySet()) {
				counts.add(entry.getValue());
			}

			Collections.reverse(counts);
			
			Mj_stat_online onLine = new Mj_stat_online();
			onLine.setOnCount(counts.get(0));
			onLine.setBeginTime(new Date(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), 00).getTime() / 1000);
			
			onlineDao.insert(onLine);
			
		}
		
		System.out.println("----------------------结束统计整点在线人数----------------------");
		
	}
	
}
