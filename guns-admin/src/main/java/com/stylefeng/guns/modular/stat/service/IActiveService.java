package com.stylefeng.guns.modular.stat.service;

import java.util.List;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_active;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-05
 */
public interface IActiveService {

	public List<Mj_stat_active> findActsByCondition(long date);
	public List<Mj_stat_active> findAll();
	
}
