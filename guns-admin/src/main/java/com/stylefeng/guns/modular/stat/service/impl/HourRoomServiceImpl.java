package com.stylefeng.guns.modular.stat.service.impl;

import com.stylefeng.guns.modular.MongoDao.StatHourRoomDao;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_hour_room;
import com.stylefeng.guns.modular.stat.service.IHourRoomService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-10
 */
@Service
public class HourRoomServiceImpl implements IHourRoomService {
	
	@Autowired
	private StatHourRoomDao hourRoomDao;
	
	@Override
	public List<Mj_stat_hour_room> findRRoomByCondition(long date) {
		// TODO Auto-generated method stub
		long to = date + 86400;
		return hourRoomDao.findByTimeRange(date, to);
	}

	@Override
	public List<Mj_stat_hour_room> findAll() {
		// TODO Auto-generated method stub
		return hourRoomDao.findAll();
	}

}
