package com.stylefeng.guns.modular.mongoDao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_platform;

public interface StatPlatformDao extends MongoRepository<Mj_stat_platform, Integer>{

	@Query("{'time' : {'$gt' : ?0, '$lt' : ?1}}") 
	public List<Mj_stat_platform> findByTimeRange(long from, long to);
	
}
