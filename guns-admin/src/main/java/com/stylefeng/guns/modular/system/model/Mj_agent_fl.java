package com.stylefeng.guns.modular.system.model;

/**
 * <p>
 * 代理,玩家返利
 * </p>
 *
 * @author stylefeng
 * @since 2018-08-29
 */
public class Mj_agent_fl {

    private int id;
	private int aid;
	private String lv;
	private Integer lvType;
	private Integer pid;
	private float money;//返利数量
	private long time;
	
	private String name;
	private String tel;
	
	private String playerName;
	private Integer playerId;
	private Integer type;//0:玩家返利 1:代理返利
	
	private String timeStr;
	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getLv() {
		return lv;
	}
	public void setLv(String lv) {
		this.lv = lv;
	}
	public Integer getLvType() {
		return lvType;
	}
	public void setLvType(Integer lvType) {
		this.lvType = lvType;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
