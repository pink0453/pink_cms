package com.stylefeng.guns.modular.stat.service.impl;

import com.stylefeng.guns.modular.mongoDao.StatRobotScoreDao;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_robot_score;
import com.stylefeng.guns.modular.stat.service.IRobotScoreService;

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
 * @since 2018-09-13
 */
@Service
public class RobotScoreServiceImpl implements IRobotScoreService {

	@Autowired
	private StatRobotScoreDao robotDao;
	
	@Override
	public List<Mj_stat_robot_score> findRobotScoreByCondition(long date) {
		// TODO Auto-generated method stub
		long to = date + 86400;
		return robotDao.findByTimeRange(date, to);
	}

	@Override
	public List<Mj_stat_robot_score> findAll() {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "time");
		return robotDao.findAll(sort);
	}

}
