package com.stylefeng.guns.modular.stat.service;

import java.util.List;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_system_water;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-12
 */
public interface ISystemWaterService{

	public List<Mj_stat_system_water> findSystemByCondition(long date);
	public List<Mj_stat_system_water> findAll();
	
}
