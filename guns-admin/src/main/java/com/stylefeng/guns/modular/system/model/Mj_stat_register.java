package com.stylefeng.guns.modular.system.model;

/**
 * 注册量统计
 * @author admin
 *
 */
public class Mj_stat_register {

	private String id;
	private int regCount;
	private double ringGrowth;//环比增长
	private double ringGrowthRate;//环比增长率
	private double yoyGrowth;//同比增长
	private double yoyGrowthRate;//同比增长率
	private long createTime;//创建时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRegCount() {
		return regCount;
	}
	public void setRegCount(int regCount) {
		this.regCount = regCount;
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
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	
	
}
