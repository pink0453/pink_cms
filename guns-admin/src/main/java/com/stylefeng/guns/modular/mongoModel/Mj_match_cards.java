package com.stylefeng.guns.modular.mongoModel;

public class Mj_match_cards {

	private String id;
	private int map_id;
	private int player_id;
	private String card_ids;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMap_id() {
		return map_id;
	}
	public void setMap_id(int map_id) {
		this.map_id = map_id;
	}
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public String getCard_ids() {
		return card_ids;
	}
	public void setCard_ids(String card_ids) {
		this.card_ids = card_ids;
	}
	
}
