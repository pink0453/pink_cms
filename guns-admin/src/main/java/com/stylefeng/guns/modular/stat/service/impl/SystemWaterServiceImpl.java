package com.stylefeng.guns.modular.stat.service.impl;

import com.stylefeng.guns.modular.mongoDao.StatSystemWaterDao;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_system_water;
import com.stylefeng.guns.modular.stat.service.ISystemWaterService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-12
 */
@Service
public class SystemWaterServiceImpl implements ISystemWaterService {

	@Autowired
	private StatSystemWaterDao systemWaterDao;
	
	@Override
	public List<Mj_stat_system_water> findSystemByCondition(long date) {
		// TODO Auto-generated method stub
		long to = date + 86400;
		return systemWaterDao.findByTimeRange(date, to);
	}

	@Override
	public List<Mj_stat_system_water> findAll() {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "time");
		return systemWaterDao.findAll(sort);
	}

}
