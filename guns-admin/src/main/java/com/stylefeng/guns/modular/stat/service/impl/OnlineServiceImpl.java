package com.stylefeng.guns.modular.stat.service.impl;

import com.stylefeng.guns.modular.stat.service.IOnlineService;
import com.stylefeng.guns.modular.system.model.Mj_stat_online;
import com.stylefeng.guns.modular.system.mongoDao.StatOnlineDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-06
 */
@Service
public class OnlineServiceImpl implements IOnlineService {

	@Autowired
	private StatOnlineDao onlineDao; 
	
	@Override
	public List<Mj_stat_online> findROnlineByCondition(long date) {
		// TODO Auto-generated method stub
		long to = date + 86400;
		
		return onlineDao.findByTimeRange(date, to);
	}

	@Override
	public List<Mj_stat_online> findAll() {
		// TODO Auto-generated method stub
		return onlineDao.findAll();
	}

}
