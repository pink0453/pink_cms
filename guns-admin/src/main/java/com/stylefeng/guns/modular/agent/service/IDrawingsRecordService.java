package com.stylefeng.guns.modular.agent.service;

import com.stylefeng.guns.modular.system.model.DrawingsRecord;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-28
 */
public interface IDrawingsRecordService extends IService<DrawingsRecord> {

	/**
	 * 根据用户ID获取提现记录
	 * @param userId
	 * @return
	 */
	List<DrawingsRecord> getDrawingsByUserId(int userId);
	
}
