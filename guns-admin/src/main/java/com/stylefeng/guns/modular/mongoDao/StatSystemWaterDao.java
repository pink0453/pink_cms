package com.stylefeng.guns.modular.mongoDao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_system_water;

public interface StatSystemWaterDao extends MongoRepository<Mj_stat_system_water, Integer>{

	@Query("{'time' : {'$gt' : ?0, '$lt' : ?1}}") 
	public List<Mj_stat_system_water> findByTimeRange(long from, long to);
	
}
