package com.stylefeng.guns.modular.agent.service.impl;

import com.stylefeng.guns.modular.MongoDao.UserFlDao;
import com.stylefeng.guns.modular.agent.service.IUserFlService;
import com.stylefeng.guns.modular.mongoModel.Mj_agent_fl;
import com.stylefeng.guns.modular.mongoModel.Mj_players;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 代理返利 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-08-29
 */
@Service
public class UserFlServiceImpl implements IUserFlService {

	@Autowired
	private UserFlDao userFlDao;
	
	@Override
	public List<Mj_agent_fl> getAll() {
		// TODO Auto-generated method stub
		
		List<Mj_agent_fl> userFls = userFlDao.findAll();
		
		return userFls;
	}

	@Override
	public void insert(Mj_agent_fl mjAgentFl) {
		// TODO Auto-generated method stub
		userFlDao.insert(mjAgentFl);
	}

	@Override
	public List<Mj_agent_fl> getFlByCurUser(Mj_players player, Integer type) {
		// TODO Auto-generated method stub
		
		List<Mj_agent_fl> afs = userFlDao.findByPlayerIdAndType(player.get_id(), type);
		return afs;
	}

}
