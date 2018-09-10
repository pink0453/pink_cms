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

import com.stylefeng.guns.modular.mongoDao.PlayersDao;
import com.stylefeng.guns.modular.mongoDao.RoomCardDao;
import com.stylefeng.guns.modular.mongoDao.StatActiveDao;
import com.stylefeng.guns.modular.mongoDao.StatHourRoomDao;
import com.stylefeng.guns.modular.mongoDao.StatOnlineDao;
import com.stylefeng.guns.modular.mongoDao.StatOpenRoomDao;
import com.stylefeng.guns.modular.mongoDao.StatRegisterDao;
import com.stylefeng.guns.modular.mongoModel.Mj_players;
import com.stylefeng.guns.modular.mongoModel.Mj_rooms;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_active;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_hour_room;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_online;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_open_room;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_register;

/**
 * 定时任务 统计游戏数据
 * @author admin
 *
 */
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
	@Autowired
	private RoomCardDao roomCardDao;
	@Autowired
	private StatOpenRoomDao openRoomDao;
	@Autowired
	private StatHourRoomDao hourRoomDao;
	
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
	@Scheduled(cron = "0 0,10,50 * * * ?")
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
	
	/**
	 * 0点统计今日开房数据
	 */
//	@Scheduled(cron = "0/10 * * * * ? ")
	@Scheduled(cron = "0 0 0 * * ?")
	public void statOpenRoomSch() {
		System.out.println("----------------------开始分别统计今日开房总数---------------------");
		
		long nowTime = System.currentTimeMillis() / 1000;
		long beforeTime = nowTime - 86400;//前一天
		
		List<Mj_rooms> rooms = roomCardDao.findORoomByTimeRange(beforeTime, nowTime);

		int roomCount = rooms.size();
		//保存时间向前推1个小时,保证算出来的数据在当天
		long saveTime = nowTime - 3600;
		
		int pokerNiuCount = 0;
		int mjNiuCount = 0;
		int zjhCount = 0;
		int sgCount = 0;
		
		for(Mj_rooms room : rooms) {
			
			switch (room.getMap_id()) {
			case 220://麻将牛牛
				mjNiuCount++;
				break;
			case 226://纸牌牛牛
				pokerNiuCount++;
				break;
			case 207://扎金花
				zjhCount++;
				break;
			case 236://三公
				sgCount++;
				break;
			default:
				break;
			}
			
		}
		
		Mj_stat_open_room room = new Mj_stat_open_room();
		room.setTime(saveTime);
		room.setRoomCount(roomCount);
		
		room.setMjNiuCount(mjNiuCount);
		room.setPokerNiuCount(pokerNiuCount);
		room.setSgCount(sgCount);
		room.setZjhCount(zjhCount);
		
		openRoomDao.insert(room);
		
		System.out.println("----------------------结束分别统计今日开房总数---------------------");
	}
	
	/**
	 * 每小时开房次数统计
	 */
//	@Scheduled(cron = "0/10 * * * * ? ")
	@Scheduled(cron = "0 0 * * *  ?")
	public void statHourRoomSch() {
		System.out.println("----------------------开始统计每小时开房----------------------");
		
		long nowTime = System.currentTimeMillis() / 1000;
		long beforeTime = nowTime - 3600;//前一天
		
		List<Mj_rooms> rooms = roomCardDao.findORoomByTimeRange(beforeTime, nowTime);

		int roomCount = rooms.size();
		//保存时间向前推1个小时,保证算出来的数据在当天
		long saveTime = nowTime - 3600;
		
		int pokerNiuCount = 0;
		int mjNiuCount = 0;
		int zjhCount = 0;
		int sgCount = 0;
		
		for(Mj_rooms room : rooms) {
			
			switch (room.getMap_id()) {
			case 220://麻将牛牛
				mjNiuCount++;
				break;
			case 226://纸牌牛牛
				pokerNiuCount++;
				break;
			case 207://扎金花
				zjhCount++;
				break;
			case 236://三公
				sgCount++;
				break;
			default:
				break;
			}
			
		}
		
		Mj_stat_hour_room room = new Mj_stat_hour_room();
		room.setTime(saveTime);
		room.setRoomCount(roomCount);
		
		room.setMjNiuCount(mjNiuCount);
		room.setPokerNiuCount(pokerNiuCount);
		room.setSgCount(sgCount);
		room.setZjhCount(zjhCount);
		
		hourRoomDao.insert(room);
		
		System.out.println("----------------------结束统计每小时开房----------------------");
	}
	
}
