package com.stylefeng.guns.modular.MongoDao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_hour_room;

public interface StatHourRoomDao extends MongoRepository<Mj_stat_hour_room, Integer>{

	@Query("{'time' : {'$gt' : ?0, '$lt' : ?1}}") 
	public List<Mj_stat_hour_room> findByTimeRange(long from, long to);
	
}
