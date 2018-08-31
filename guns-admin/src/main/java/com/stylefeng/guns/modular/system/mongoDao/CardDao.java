package com.stylefeng.guns.modular.system.mongoDao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.stylefeng.guns.modular.system.model.Mj_match_cards;

public interface CardDao extends MongoRepository<Mj_match_cards, Integer>{

	@Query("{ 'map_id' : ?0 , 'player_id' : ?1}")
	public List<Mj_match_cards> getCard(Integer mapId, Integer playerId);
	
}
