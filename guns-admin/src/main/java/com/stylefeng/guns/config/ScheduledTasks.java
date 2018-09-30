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

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.modular.mongoDao.PlayerFlDao;
import com.stylefeng.guns.modular.mongoDao.PlayersDao;
import com.stylefeng.guns.modular.mongoDao.RoomCardDao;
import com.stylefeng.guns.modular.mongoDao.StatActiveDao;
import com.stylefeng.guns.modular.mongoDao.StatAgentFlDao;
import com.stylefeng.guns.modular.mongoDao.StatConversionDao;
import com.stylefeng.guns.modular.mongoDao.StatHourRoomDao;
import com.stylefeng.guns.modular.mongoDao.StatOnlineDao;
import com.stylefeng.guns.modular.mongoDao.StatOpenRoomDao;
import com.stylefeng.guns.modular.mongoDao.StatPlatformDao;
import com.stylefeng.guns.modular.mongoDao.StatRegisterDao;
import com.stylefeng.guns.modular.mongoDao.StatRobotScoreDao;
import com.stylefeng.guns.modular.mongoDao.StatSystemWaterDao;
import com.stylefeng.guns.modular.mongoDao.UserFlDao;
import com.stylefeng.guns.modular.mongoDao.WaterRecordDao;
import com.stylefeng.guns.modular.mongoModel.Mj_agent_fl;
import com.stylefeng.guns.modular.mongoModel.Mj_player_fl;
import com.stylefeng.guns.modular.mongoModel.Mj_players;
import com.stylefeng.guns.modular.mongoModel.Mj_rooms;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_active;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_agent_fl;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_conversion;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_hour_room;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_online;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_open_room;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_platform;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_register;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_robot_score;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_system_water;
import com.stylefeng.guns.modular.mongoModel.Mj_water_record;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;

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
	@Autowired
	private PlayerFlDao playerFlDao;
	@Autowired
	private StatSystemWaterDao systemWaterDao;
	@Autowired
	private UserFlDao userFlDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private StatAgentFlDao agentFlDao;
	@Autowired
	private WaterRecordDao waterRecordDao;
	@Autowired
	private StatRobotScoreDao robotScoreDao;
	@Autowired
	private StatConversionDao conversionDao;
	@Autowired
	private StatPlatformDao platformDao;
	
	Map<Integer, Integer> onMap = new HashMap<Integer, Integer>();
	Map<String, Float> platformMap = new HashMap<String, Float>();
	
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
	
	/**
	 * 系统抽水统计(0点5分)
	 */
//	@Scheduled(cron = "0/5 * * * * ? ")
	@Scheduled(cron = "0 5 0 * * ?")
	public void statSystemWaterSch() {
		System.out.println("----------------------开始统计今日抽水----------------------");
		
		long nowTime = System.currentTimeMillis() / 1000;
		long beforeTime = nowTime - 86400;//前一天
		
		List<Mj_player_fl> fls = playerFlDao.findFlByTimeRange(beforeTime, nowTime);
		
		float niuniuPokerSystem = 0;
		float niuniuPokerAgent = 0;
		float niuniuMjSystem = 0;
		float niuniuMjAgent = 0;
		float sangongPokerSystem = 0;
		float sangongPokerAgent = 0;
		float zjhPokerSystem = 0;
		float zjhPokerAgent = 0;
		
		float totalSystem = 0;
		float totalAgent = 0;
		
		for(Mj_player_fl fl : fls) {
			
			switch (fl.getRoomType()) {
			case 229://麻将牛牛 金币
				niuniuMjSystem = niuniuMjSystem + fl.getFront_money();
				niuniuMjAgent = niuniuMjAgent + fl.getBehind_money();
				break;
			case 221://纸牌牛牛 金币
				niuniuPokerSystem = niuniuPokerSystem + fl.getFront_money();
				niuniuPokerAgent = niuniuPokerAgent + fl.getBehind_money();
				break;
			case 207://扎金花 金币
				zjhPokerSystem = zjhPokerSystem + fl.getFront_money();
				zjhPokerAgent = zjhPokerAgent + fl.getBehind_money();
				break;
			case 231://三公 金币
				sangongPokerSystem = sangongPokerSystem + fl.getFront_money();
				sangongPokerAgent = sangongPokerAgent + fl.getBehind_money();
				break;
			default:
				break;
			}
			
		}
		
		niuniuPokerSystem = niuniuPokerSystem - niuniuPokerAgent;
		niuniuMjSystem = niuniuMjSystem - niuniuMjAgent;
		sangongPokerSystem = sangongPokerSystem - sangongPokerAgent;
		zjhPokerSystem = zjhPokerSystem - zjhPokerAgent;
		
		totalSystem = niuniuPokerSystem + niuniuMjSystem + sangongPokerSystem + zjhPokerSystem;
		totalAgent = niuniuPokerAgent + niuniuMjAgent + sangongPokerAgent + zjhPokerAgent;
		
		//保存时间向前推1个小时,保证算出来的数据在当天
		long saveTime = nowTime - 3600;
		
		Mj_stat_system_water systemWater = new Mj_stat_system_water();
		systemWater.setNiuniuMjAgent(niuniuMjAgent);
		systemWater.setNiuniuMjSystem(niuniuMjSystem);
		systemWater.setNiuniuPokerAgent(niuniuPokerAgent);
		systemWater.setNiuniuPokerSystem(niuniuPokerSystem);
		systemWater.setSangongPokerAgent(sangongPokerAgent);
		systemWater.setSangongPokerSystem(sangongPokerSystem);
		systemWater.setZjhPokerAgent(zjhPokerAgent);
		systemWater.setZjhPokerSystem(zjhPokerSystem);
		systemWater.setTotalAgent(totalAgent);
		systemWater.setTotalSystem(totalSystem);
		systemWater.setTime(saveTime);
		
		systemWaterDao.insert(systemWater);
		
		Float system = platformMap.get("system");
		if(system == null) {
			platformMap.put("system", totalSystem);
		}
		
		System.out.println("----------------------结束统计今日抽水----------------------");
	}
	
	/**
	 * 代理返利统计
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void statAgentRecordSch() {
		System.out.println("----------------------开始代理返利统计---------------------");
		
		long nowTime = System.currentTimeMillis() / 1000;
		long beforeTime = nowTime - 86400;//前一天
		List<Mj_agent_fl> fls = userFlDao.findByTimeRange(beforeTime, nowTime);
		
		//保存时间向前推1个小时,保证算出来的数据在当天
		long saveTime = nowTime - 3600;
		
		List<Mj_stat_agent_fl> statFls = toGroupBy(fls);
		
		if(statFls != null && statFls.size() > 0) {
			
			for(Mj_stat_agent_fl statFl : statFls) {
				
				statFl.setTime(saveTime);
				agentFlDao.insert(statFl);
				
			}
			
		}
		
//		for(Mj_agent_fl fl : fls) {
//			
//			Mj_stat_agent_fl agentFl = new Mj_stat_agent_fl();
//			User user = userService.getByGameAccountId(fl.getAid()+"");
//			
//			agentFl.setGameAccountId(user.getGameAccountId());
//			agentFl.setLv(Integer.parseInt(user.getRoleid()));
//			agentFl.setLvStr(fl.getLv());
//			agentFl.setMoney(fl.getMoney());
//			agentFl.setParentId(fl.getPid());
//			agentFl.setPhone(user.getPhone());
//			agentFl.setRealName(user.getName());
//			agentFl.setType(fl.getType());
//			agentFl.setTime(saveTime);
//			
//			agentFlDao.insert(agentFl);
//			
//		}
		
		System.out.println("----------------------结束代理返利统计---------------------");
	}
	
	/**
	 * 统计机器人收入(0点6分)
	 */
//	@Scheduled(cron = "0/10 * * * * ? ")
	@Scheduled(cron = "0 6 0 * * ?")
	public void statRobotScore() {
		System.out.println("----------------------开始统计机器人收入---------------------");
		
		long nowTime = System.currentTimeMillis() / 1000;
		long beforeTime = nowTime - 86400;//前一天
		
		List<Mj_water_record> records = waterRecordDao.findByTimeRange(beforeTime, nowTime);
		
		float niuniuPokerWater = 0;
		float niuniuMjWater = 0;
		float zjhPokerWater = 0;
		float sangongPokerWater = 0;
		float totalWater = 0;
		
		for(Mj_water_record record : records) {
			
			switch (record.getMapId()) {
			case 229://麻将牛牛 金币
				niuniuMjWater = niuniuMjWater + record.getTakeMoney();
				break;
			case 221://纸牌牛牛 金币
				niuniuPokerWater = niuniuPokerWater + record.getTakeMoney();
				break;
			case 207://扎金花 金币
				zjhPokerWater = zjhPokerWater + record.getTakeMoney();
				break;
			case 231://三公 金币
				sangongPokerWater = sangongPokerWater + record.getTakeMoney();
				break;
			default:
				break;
			}
			
		}
		
		totalWater = niuniuMjWater + niuniuPokerWater + zjhPokerWater + sangongPokerWater;
		
		//保存时间向前推1个小时,保证算出来的数据在当天
		long saveTime = nowTime - 3600;
		
		Mj_stat_robot_score robotScore = new Mj_stat_robot_score();
		robotScore.setNiuniuMjWaterScore(niuniuMjWater);
		robotScore.setNiuniuPokerWaterScore(niuniuPokerWater);
		robotScore.setSangongPokerWaterScore(sangongPokerWater);
		robotScore.setZjhPokerWaterScore(zjhPokerWater);
		robotScore.setTotalWaterScore(totalWater);
		robotScore.setTime(saveTime);
		
		robotScoreDao.insert(robotScore);
		
		Float robot = platformMap.get("robot");
		if(robot == null) {
			platformMap.put("robot", totalWater);
		}
		
		System.out.println("----------------------结束统计机器人收入---------------------");
	}
	
	/**
	 * 兑换收入统计(0点7分)
	 */
//	@Scheduled(cron = "0/15 * * * * ? ")
	@Scheduled(cron = "0 7 0 * * ?")
	public void statConversionSch() {
		System.out.println("----------------------开始统计兑换收入---------------------");
	
		long nowTime = System.currentTimeMillis() / 1000;
		long beforeTime = nowTime - 86400;//前一天
		
		List<Mj_player_fl> fls = playerFlDao.findFlByTimeRange(beforeTime, nowTime);
		
		float agentMoney = 0;
		float sytemMoney = 0;
		float totalMoney = 0;
		
		for(Mj_player_fl fl : fls) {
			
			if(fl.getRoomType() == 0) {
				
				agentMoney = agentMoney + fl.getBehind_money();
				sytemMoney = sytemMoney + fl.getFront_money() - fl.getBehind_money();
				
			}
			
		}
		
		//保存时间向前推1个小时,保证算出来的数据在当天
		long saveTime = nowTime - 3600;
		
		totalMoney = agentMoney + sytemMoney;
		
		Mj_stat_conversion conversion = new Mj_stat_conversion();
		conversion.setAgentMoney(agentMoney);
		conversion.setSystemMoney(sytemMoney);
		conversion.setTotalMoney(totalMoney);
		conversion.setTime(saveTime);
		
		conversionDao.insert(conversion);
		
		Float conversion_sys = platformMap.get("conversion");
		if(conversion_sys == null) {
			platformMap.put("conversion", sytemMoney);
		}
		
		System.out.println("----------------------结束统计兑换收入---------------------");
	}
	
	/**
	 * 平台收入总统计(0点8分)
	 */
//	@Scheduled(cron = "0/20 * * * * ? ")
	@Scheduled(cron = "0 8 0 * * ?")
	public void statPlatformSch() {
		System.out.println("----------------------开始统计平台收入---------------------");
		
		long nowTime = System.currentTimeMillis() / 1000;
		
		float conversionMoney = 0;
		float conversionRate = 0;
		float waterMoney = 0;
		float waterRate = 0;
		float robotMoney = 0;
		float robotRate = 0;
		float totalMoney = 0;
		
		Float system = platformMap.get("system");
		Float robot = platformMap.get("robot");
		Float conversion = platformMap.get("conversion");
		
		if(system != null && robot != null && conversion != null) {
			
			conversionMoney = conversion;
			waterMoney = robot;
			robotMoney = system;
			
			totalMoney = conversionMoney + waterMoney + robotMoney;
			
			conversionRate = conversionMoney / totalMoney;
			waterRate = waterMoney / totalMoney;
			robotRate = robotMoney / totalMoney;
			
			Mj_stat_platform platform = new Mj_stat_platform();
			
			platform.setConversionMoney(conversionMoney);
			platform.setRobotMoney(robotMoney);
			platform.setWaterMoney(waterMoney);
			
			platform.setConversionRate(conversionRate);
			platform.setRobotRate(robotRate);
			platform.setWaterRate(waterRate);
			
			platform.setTotalMoney(totalMoney);
			
			//保存时间向前推1个小时,保证算出来的数据在当天
			long saveTime = nowTime - 3600;
			platform.setTime(saveTime);
			
			platformDao.insert(platform);

		}
		
		platformMap.clear();
		
		System.out.println("----------------------结束统计平台收入---------------------");
	}
	
	/**
	 * 分组相加
	 * @param list
	 * @return
	 */
	public List<Mj_stat_agent_fl> toGroupBy(List<Mj_agent_fl> list){
		
		Map<Integer, Mj_stat_agent_fl> map = new HashMap<Integer, Mj_stat_agent_fl>();
		List<Mj_stat_agent_fl> statFls = new ArrayList<Mj_stat_agent_fl>();
		
		for(Mj_agent_fl fl : list) {
			
			if(fl.getType() == 1) {
				
				Mj_stat_agent_fl statFl = map.get(fl.getPlayerId());
				if(statFl == null) {
					
					User user = userService.getByGameAccountId(fl.getPlayerId()+"");
					String roleName = ConstantFactory.me().getRoleName(user.getRoleid());
					
					statFl = new Mj_stat_agent_fl();
					statFl.setGameAccountId(fl.getPlayerId());
					statFl.setLv(Integer.parseInt(user.getRoleid()));
					statFl.setLvStr(roleName);
					statFl.setMoney(fl.getMoney());
					statFl.setParentId(user.getParentId());
					statFl.setPhone(user.getPhone());
					statFl.setRealName(user.getName());
					statFl.setType(fl.getType());
					statFl.setAid(fl.getAid());
					map.put(fl.getPlayerId(), statFl);
					
				}else {
					
					float money = statFl.getMoney() + fl.getMoney();
					statFl.setMoney(money);
					map.put(fl.getPlayerId(), statFl);
					
				}
				
			}
			
		}
		
		if(map != null && map.size() > 0) {
			
			for (Entry<Integer, Mj_stat_agent_fl> entry : map.entrySet()) {
				//Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
				 //entry.getKey() ;entry.getValue(); entry.setValue();
				 //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
//				 System.out.println("key= " + entry.getKey() + " and value= "
//				             + entry.getValue());
				 
				 statFls.add(entry.getValue());
				 
			}
			
		}
		
		return statFls;
		
	}
	
}
