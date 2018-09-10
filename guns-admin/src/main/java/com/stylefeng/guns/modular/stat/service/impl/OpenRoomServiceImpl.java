package com.stylefeng.guns.modular.stat.service.impl;

import com.stylefeng.guns.modular.MongoDao.StatOpenRoomDao;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_open_room;
import com.stylefeng.guns.modular.stat.service.IOpenRoomService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-07
 */
@Service
public class OpenRoomServiceImpl implements IOpenRoomService {

	@Autowired
	private StatOpenRoomDao openRoomDao;
	
	@Override
	public List<Mj_stat_open_room> findORoomByCondition(long date) {
		// TODO Auto-generated method stub
		long to = date + 86400;
		return openRoomDao.findByTimeRange(date, to);
	}

	@Override
	public List<Mj_stat_open_room> findAll() {
		// TODO Auto-generated method stub
		return openRoomDao.findAll();
	}

}
