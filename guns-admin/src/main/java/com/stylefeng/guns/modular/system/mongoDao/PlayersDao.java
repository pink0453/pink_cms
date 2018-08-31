package com.stylefeng.guns.modular.system.mongoDao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.system.model.Mj_agent_fl;
import com.stylefeng.guns.modular.system.model.Mj_players;

public interface PlayersDao extends MongoRepository<Mj_players, Integer>{
	
	@Query("{ 'referrer_uid' : ?0 }")
	public List<Mj_players> findPlayersByRef(Integer refId);
}
