package com.stylefeng.guns.modular.mongoDao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.mongoModel.Mj_player_fl;

public interface PlayerFlDao extends MongoRepository<Mj_player_fl, Integer>{

	/**
	 * 根据时间段查询
	 * @param from
	 * @param to
	 * @return
	 */
	@Query("{'time' : {'$gt' : ?0, '$lt' : ?1}}") 
	public List<Mj_player_fl> findFlByTimeRange(long from, long to);
	
}
