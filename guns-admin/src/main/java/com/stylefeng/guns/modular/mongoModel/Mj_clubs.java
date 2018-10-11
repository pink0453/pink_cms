package com.stylefeng.guns.modular.mongoModel;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.stylefeng.guns.core.annotation.AutoIncrement;

public class Mj_clubs {

	@Id
	@AutoIncrement
	@Field("_id")
	private int _id;
	private String club_name;
	private int agent_uid;
	private long create_time;
	private int owner_uid;
	
	private ArrayList<Map<Object, Object>> players;
	private Map<Object, Object> owner;
	private ArrayList<Map<Object, Object>> gamestr;
	private ArrayList<Map<Object, Object>> adplayers;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getClub_name() {
		return club_name;
	}
	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}
	public int getAgent_uid() {
		return agent_uid;
	}
	public void setAgent_uid(int agent_uid) {
		this.agent_uid = agent_uid;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public int getOwner_uid() {
		return owner_uid;
	}
	public void setOwner_uid(int owner_uid) {
		this.owner_uid = owner_uid;
	}
	public ArrayList<Map<Object, Object>> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Map<Object, Object>> players) {
		this.players = players;
	}
	public Map<Object, Object> getOwner() {
		return owner;
	}
	public void setOwner(Map<Object, Object> owner) {
		this.owner = owner;
	}
	public ArrayList<Map<Object, Object>> getGamestr() {
		return gamestr;
	}
	public void setGamestr(ArrayList<Map<Object, Object>> gamestr) {
		this.gamestr = gamestr;
	}
	public ArrayList<Map<Object, Object>> getAdplayers() {
		return adplayers;
	}
	public void setAdplayers(ArrayList<Map<Object, Object>> adplayers) {
		this.adplayers = adplayers;
	}
	
	
	
}
