package com.stylefeng.guns.modular.agent.service;

import java.util.List;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_agent_fl;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-12
 */
public interface IAgentFlService {

	public List<Mj_stat_agent_fl> findAgentFlStatByCondition(long date);
	public List<Mj_stat_agent_fl> findAll();
	
}
