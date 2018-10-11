package com.stylefeng.guns.modular.club.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.modular.club.service.IClubService;
import com.stylefeng.guns.modular.mongoDao.ClubDao;
import com.stylefeng.guns.modular.mongoDao.PlayersDao;
import com.stylefeng.guns.modular.mongoModel.Club;
import com.stylefeng.guns.modular.mongoModel.Mj_clubs;
import com.stylefeng.guns.modular.mongoModel.Mj_players;

@Service
public class ClubService implements IClubService{

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private ClubDao clubDao;
	@Autowired
	private PlayersDao playersDao;
	
	@Override
	public void createClub(Club club, int ownerId) {
		// TODO Auto-generated method stub
		
		Mj_players player = playersDao.findPlayerByUid(ownerId);
		
		Mj_clubs clubObj = new Mj_clubs();
		clubObj.setClub_name(club.getClubName());
		clubObj.setAgent_uid(club.getAgentId());
		clubObj.setCreate_time(System.currentTimeMillis() / 1000);
		clubObj.setOwner_uid(ownerId);
		
		Mj_clubs newClub = clubDao.insert(clubObj);
		
		club.setClubId((int)newClub.get_id());
		addClubOrPlayer(player, club);
		
	}
	
	/**
	 * 修改俱乐部和玩家信息  添加俱乐部内容
	 * @param player
	 * @param club
	 */
	public void addClubOrPlayer(Mj_players player,Club club) {
		
		//修改俱乐部
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(club.getClubId()));
		query.addCriteria(Criteria.where("players").nin(makeIntArrayList(player.get_id())));
		
		Map<Object, Object> map = new HashMap<>();
		map.put("player_id", player.get_id());
		map.put("nickname", player.getNickname());
		map.put("header", player.getHeadimgurl());
		Update update = new Update().push("players", map);
		
		mongoTemplate.updateFirst(query, update, "mj_clubs");
		
		//修改玩家
		Query query1 = new Query();
		query1.addCriteria(Criteria.where("_id").is(player.get_id()));
		query1.addCriteria(Criteria.where("clubs").nin(makeIntArrayList(club.getClubId())));
		
		Map<Object, Object> map1 = new HashMap<>();
		map1.put("club_id", club.getClubId());
		map1.put("club_name", club.getClubName());
		map1.put("club_logo", club.getClubLogo());
		Update update1 = new Update().push("clubs", map1);
		
		mongoTemplate.updateFirst(query1, update1, "mj_players");
		
	}
	
	public static ArrayList<Integer> makeIntArrayList(int... arr) {
        ArrayList<Integer> _arr = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++)
            _arr.add(arr[i]);
        return _arr;
    }

}
