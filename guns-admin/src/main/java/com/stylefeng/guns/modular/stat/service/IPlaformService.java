package com.stylefeng.guns.modular.stat.service;

import java.util.List;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_platform;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-17
 */
public interface IPlaformService {

	public List<Mj_stat_platform> findPlatformByCondition(long date);
	public List<Mj_stat_platform> findAll();
	
}
