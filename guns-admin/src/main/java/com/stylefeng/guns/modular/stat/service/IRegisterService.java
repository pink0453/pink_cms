package com.stylefeng.guns.modular.stat.service;

import java.util.List;

import com.stylefeng.guns.modular.system.model.Mj_stat_register;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-05
 */
public interface IRegisterService {

	public List<Mj_stat_register> findRegsByCondition(long date);
	public List<Mj_stat_register> findAll();
}
