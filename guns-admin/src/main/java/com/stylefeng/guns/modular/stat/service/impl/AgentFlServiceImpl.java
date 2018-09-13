package com.stylefeng.guns.modular.stat.service.impl;

import com.stylefeng.guns.modular.mongoDao.StatAgentFlDao;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_agent_fl;
import com.stylefeng.guns.modular.stat.service.IAgentFlStatService;

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
public class AgentFlServiceImpl implements IAgentFlStatService {

	@Autowired
	private StatAgentFlDao agentFlDao;

	@Override
	public List<Mj_stat_agent_fl> findAgentFlStatByCondition(long date) {
		// TODO Auto-generated method stub
		long to = date + 86400;
		return agentFlDao.findByTimeRange(date, to);
	}

	@Override
	public List<Mj_stat_agent_fl> findAll() {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "time");
		return agentFlDao.findAll(sort);
	}

}
