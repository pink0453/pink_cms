package com.stylefeng.guns.modular.mongoDao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.mongoModel.Mj_water_record;

public interface WaterRecordDao extends MongoRepository<Mj_water_record, Integer>{

	@Query("{'time' : {'$gt' : ?0, '$lt' : ?1}}") 
	public List<Mj_water_record> findByTimeRange(long from, long to);
	
}
