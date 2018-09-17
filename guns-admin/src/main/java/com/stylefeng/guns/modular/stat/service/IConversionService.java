package com.stylefeng.guns.modular.stat.service;

import java.util.List;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_conversion;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-17
 */
public interface IConversionService {

	public List<Mj_stat_conversion> findConsByCondition(long date);
	public List<Mj_stat_conversion> findAll();
	
}
