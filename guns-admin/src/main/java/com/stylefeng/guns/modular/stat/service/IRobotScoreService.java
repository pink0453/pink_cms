package com.stylefeng.guns.modular.stat.service;

import java.util.List;

import com.stylefeng.guns.modular.mongoModel.Mj_stat_robot_score;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-13
 */
public interface IRobotScoreService {

	public List<Mj_stat_robot_score> findRobotScoreByCondition(long date);
	public List<Mj_stat_robot_score> findAll();
}
