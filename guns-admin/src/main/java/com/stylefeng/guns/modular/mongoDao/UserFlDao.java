package com.stylefeng.guns.modular.mongoDao;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.mongoModel.Mj_agent_fl;

public interface UserFlDao extends MongoRepository<Mj_agent_fl, Integer>{
	
	@Query("{ 'playerId' : ?0 , 'type' : ?1}")
	public List<Mj_agent_fl> findByPlayerIdAndType(Integer playerId, Integer type);
	
	@Query("{'time' : {'$gt' : ?0, '$lt' : ?1}}") 
	public List<Mj_agent_fl> findByTimeRange(long from, long to);
	
	@Query("{ 'aid' : ?0 , 'type' : ?1}")
	public List<Mj_agent_fl> findByAidAndType(Integer aid, Integer type);
	
}
