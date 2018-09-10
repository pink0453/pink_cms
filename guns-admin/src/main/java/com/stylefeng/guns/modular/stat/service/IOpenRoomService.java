package com.stylefeng.guns.modular.stat.service;

import java.util.List;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_open_room;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-07
 */
public interface IOpenRoomService  {
	public List<Mj_stat_open_room> findORoomByCondition(long date);
	public List<Mj_stat_open_room> findAll();
}
