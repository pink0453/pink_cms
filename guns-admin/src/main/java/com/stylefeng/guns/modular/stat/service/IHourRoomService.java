package com.stylefeng.guns.modular.stat.service;

import java.util.List;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_hour_room;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-10
 */
public interface IHourRoomService{
	public List<Mj_stat_hour_room> findRRoomByCondition(long date);
	public List<Mj_stat_hour_room> findAll();
}
