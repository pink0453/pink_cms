package com.stylefeng.guns.modular.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.modular.mongoModel.Mj_players;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-08-29
 */
public interface IPlayerService{

	/**
	 * 通过绑定人查询所有玩家
	 * @param allplayers
	 * @param refId
	 * @return
	 */
	List<Mj_players> getPlayersByRef(List<Mj_players> allplayers, Integer refId);
	
	/**
	 * 查询所有玩家
	 * @return
	 */
	List<Mj_players> getAllPlayers();
	
}
