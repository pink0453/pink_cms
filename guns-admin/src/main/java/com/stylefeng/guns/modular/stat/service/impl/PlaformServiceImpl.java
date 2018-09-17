package com.stylefeng.guns.modular.stat.service.impl;

import com.stylefeng.guns.modular.mongoDao.StatPlatformDao;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_platform;
import com.stylefeng.guns.modular.stat.service.IPlaformService;

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
 * @since 2018-09-17
 */
@Service
public class PlaformServiceImpl implements IPlaformService {

	@Autowired
	private StatPlatformDao platformDao;
	
	@Override
	public List<Mj_stat_platform> findPlatformByCondition(long date) {
		// TODO Auto-generated method stub
		long to = date + 86400;
		return platformDao.findByTimeRange(date, to);
	}

	@Override
	public List<Mj_stat_platform> findAll() {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "time");
		return platformDao.findAll(sort);
	}

}
