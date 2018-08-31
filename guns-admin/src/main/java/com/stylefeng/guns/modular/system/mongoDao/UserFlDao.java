package com.stylefeng.guns.modular.system.mongoDao;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.system.model.Mj_agent_fl;
import com.stylefeng.guns.modular.system.model.Mj_players;


public interface UserFlDao extends MongoRepository<Mj_agent_fl, Integer>{
	
	
	@Query("{ 'playerId' : ?0 , 'type' : ?1}")
	public List<Mj_agent_fl> findByPlayerIdAndType(Integer playerId, Integer type);
}
