package com.stylefeng.guns.modular.system.model;

public class Mj_stat_active {

	private String id;
	private long time;
	private int actCount;
	private double ringGrowth;//环比增长
	private double ringGrowthRate;//环比增长率
	private double yoyGrowth;//同比增长
	private double yoyGrowthRate;//同比增长率
	
	private String timeStr;
	
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getActCount() {
		return actCount;
	}
	public void setActCount(int actCount) {
		this.actCount = actCount;
	}
	public double getRingGrowth() {
		return ringGrowth;
	}
	public void setRingGrowth(double ringGrowth) {
		this.ringGrowth = ringGrowth;
	}
	public double getRingGrowthRate() {
		return ringGrowthRate;
	}
	public void setRingGrowthRate(double ringGrowthRate) {
		this.ringGrowthRate = ringGrowthRate;
	}
	public double getYoyGrowth() {
		return yoyGrowth;
	}
	public void setYoyGrowth(double yoyGrowth) {
		this.yoyGrowth = yoyGrowth;
	}
	public double getYoyGrowthRate() {
		return yoyGrowthRate;
	}
	public void setYoyGrowthRate(double yoyGrowthRate) {
		this.yoyGrowthRate = yoyGrowthRate;
	}
	
	
	
}
