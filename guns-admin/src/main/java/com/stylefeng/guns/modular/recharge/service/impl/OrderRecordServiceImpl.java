package com.stylefeng.guns.modular.recharge.service.impl;

import com.stylefeng.guns.modular.system.model.OrderRecord;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.dao.OrderRecordMapper;
import com.stylefeng.guns.modular.system.dao.UserMapper;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.modular.recharge.service.IOrderRecordService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-19
 */
@Service
public class OrderRecordServiceImpl extends ServiceImpl<OrderRecordMapper, OrderRecord> implements IOrderRecordService {

	@Autowired
	private OrderRecordMapper orderRecordMapper;
	
	/**
	 * 根据条件查询玩家充值列表
	 */
	@Override
	@DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
	@Transactional
    public List<Map<String, Object>> getRecharges(String beginTime, String endTime, Integer playerId, String orderByField, boolean asc, int type) {
		
		List<Map<String, Object>> recharges = orderRecordMapper.getRecharges(beginTime, endTime, playerId, orderByField, asc, type);
		
		return recharges;
		
    }

	@Override
	@DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
	@Transactional
	public List<Map<String, Object>> getRechargeByIds(List<Integer> ids, String beginTime, String endTime, Integer playerId, String orderByField, boolean asc, int type) {
		// TODO Auto-generated method stub
		
		return orderRecordMapper.getRechargeByIds(ids, beginTime, endTime, playerId, orderByField, asc, type);
		
	}
	
}
