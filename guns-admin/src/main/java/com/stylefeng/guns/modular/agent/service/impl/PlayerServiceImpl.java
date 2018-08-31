package com.stylefeng.guns.modular.agent.service.impl;

import com.stylefeng.guns.modular.agent.service.IPlayerService;
import com.stylefeng.guns.modular.system.model.Mj_players;
import com.stylefeng.guns.modular.system.mongoDao.PlayersDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinkman
 * @since 2018-08-29
 */
@Service
public class PlayerServiceImpl implements IPlayerService {

	@Autowired
	private PlayersDao playersDao;
	
	@Override
	public List<Mj_players> getPlayersByRef(List<Mj_players> allplayers, Integer refId) {
		// TODO Auto-generated method stub
		
		List<Mj_players> players = playersDao.findPlayersByRef(refId);
		allplayers.addAll(players);
		
		for(Mj_players player : players) {
			
			getPlayersByRef(allplayers, player.get_id());
			
		}
		
		return allplayers;
	}

	@Override
	public List<Mj_players> getAllPlayers() {
		// TODO Auto-generated method stub
		return playersDao.findAll();
	}

}
