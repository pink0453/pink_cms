package com.stylefeng.guns.modular.system.mongoDao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.system.model.Mj_stat_open_room;

public interface StatOpenRoomDao extends MongoRepository<Mj_stat_open_room, Integer>{

	@Query("{'time' : {'$gt' : ?0, '$lt' : ?1}}") 
	public List<Mj_stat_open_room> findByTimeRange(long from, long to);
	
}
