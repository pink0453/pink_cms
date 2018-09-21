package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.OrderRecord;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pinkman
 * @since 2018-09-19
 */
public interface OrderRecordMapper extends BaseMapper<OrderRecord> {
	/**
     * 获取玩家充值记录
     */
    List<Map<String, Object>> getRecharges(@Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("userId") Integer userId, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc, @Param("type") int type);

    /**
     * 通过玩家集合获取充值记录
     * @param ids
     * @return
     */
    public List<Map<String, Object>> getRechargeByIds(@Param("ids") List<Integer> ids, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("userId") Integer userId, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc, @Param("type") int type);
    
}
