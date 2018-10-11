package com.stylefeng.guns.modular.mongoDao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stylefeng.guns.modular.mongoModel.Mj_clubs;

public interface ClubDao extends MongoRepository<Mj_clubs, Integer>{

	
	
}
