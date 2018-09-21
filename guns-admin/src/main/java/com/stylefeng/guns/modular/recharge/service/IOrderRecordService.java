package com.stylefeng.guns.modular.recharge.service;

import com.stylefeng.guns.modular.system.model.OrderRecord;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-19
 */
public interface IOrderRecordService extends IService<OrderRecord> {

	/**
	 * 获取玩家充值列表
	 * @param page
	 * @param beginTime
	 * @param endTime
	 * @param playerId
	 * @param orderByField
	 * @param asc
	 * @param type 0玩家充值 1代理充值
	 * @return
	 */
	List<Map<String, Object>> getRecharges(String beginTime, String endTime, Integer playerId, String orderByField, boolean asc, int type);
	
	/**
	 * 通过玩家集合获取充值记录
	 * @param ids
	 * @return
	 */
	List<Map<String, Object>> getRechargeByIds(List<Integer> ids, String beginTime, String endTime, Integer playerId, String orderByField, boolean asc, int type);

}
