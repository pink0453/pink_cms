package com.stylefeng.guns.modular.system.mongoDao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.system.model.Mj_agent_fl;
import com.stylefeng.guns.modular.system.model.Mj_players;

public interface PlayersDao extends MongoRepository<Mj_players, Integer>{
	
	/**
	 * 根据推荐人查询玩家列表
	 * @param refId
	 * @return
	 */
	@Query("{ 'referrer_uid' : ?0 }")
	public List<Mj_players> findPlayersByRef(Integer refId);
	
	/**
	 * 根据uid查询某个用户
	 * @param uid
	 * @return
	 */
	@Query("{ '_id' : ?0 }")
	public Mj_players findPlayerByUid(Integer uid);
	
	/**
	 * 根据时间段查询注册用户
	 * @param from
	 * @param to
	 * @return
	 */
	@Query("{'create_time' : {'$gt' : ?0, '$lt' : ?1}}") 
	public List<Mj_players> findByTimeRange(long from, long to);
	
}
