package com.stylefeng.guns.modular.agent.service;

import java.util.List;

import com.stylefeng.guns.modular.mongoModel.Mj_agent_fl;
import com.stylefeng.guns.modular.mongoModel.Mj_players;

/**
 * <p>
 * 代理返利 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-08-29
 */
public interface IUserFlService {

	/**
	 * 查询所有
	 * @return
	 */
	public List<Mj_agent_fl> getAll();
	public void insert(Mj_agent_fl mjAgentFl);
	
	public List<Mj_agent_fl> getFlByCurUser(Mj_players player, Integer type);
	
}
