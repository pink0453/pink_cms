package com.stylefeng.guns.modular.mongoDao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stylefeng.guns.modular.mongoModel.Mj_game_bind;

public interface GameBindDao extends MongoRepository<Mj_game_bind, Integer>{

}
