package com.stylefeng.guns.modular.stat.service;

import java.util.List;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_online;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-06
 */
public interface IOnlineService {

	public List<Mj_stat_online> findROnlineByCondition(long date);
	public List<Mj_stat_online> findAll();
	
}
