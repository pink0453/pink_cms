package com.stylefeng.guns.modular.MongoDao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.mongoModel.Mj_rooms;

public interface RoomCardDao extends MongoRepository<Mj_rooms, Integer> {

	/**
	 * 根据时间段查询开房数
	 * @param from
	 * @param to
	 * @return
	 */
	@Query("{'create_time' : {'$gt' : ?0, '$lt' : ?1}}") 
	public List<Mj_rooms> findORoomByTimeRange(long from, long to);
	
}
