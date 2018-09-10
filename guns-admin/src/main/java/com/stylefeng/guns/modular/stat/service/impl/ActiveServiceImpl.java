package com.stylefeng.guns.modular.stat.service.impl;

import com.stylefeng.guns.modular.MongoDao.StatActiveDao;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_active;
import com.stylefeng.guns.modular.stat.service.IActiveService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-05
 */
@Service
public class ActiveServiceImpl implements IActiveService {

	@Autowired
	private StatActiveDao activeDao;
	
	@Override
	public List<Mj_stat_active> findActsByCondition(long date) {
		// TODO Auto-generated method stub
		
		long to = date + 86400;
		return activeDao.findByTimeRange(date, to);
		
	}

	@Override
	public List<Mj_stat_active> findAll() {
		// TODO Auto-generated method stub
		return activeDao.findAll();
	}

}
