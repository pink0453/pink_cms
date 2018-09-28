package com.stylefeng.guns.modular.agent.service.impl;

import com.stylefeng.guns.modular.system.model.DrawingsRecord;
import com.stylefeng.guns.modular.system.dao.DrawingsRecordMapper;
import com.stylefeng.guns.modular.agent.service.IDrawingsRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-28
 */
@Service
public class DrawingsRecordServiceImpl extends ServiceImpl<DrawingsRecordMapper, DrawingsRecord> implements IDrawingsRecordService {

	@Override
	public List<DrawingsRecord> getDrawingsByUserId(int userId) {
		// TODO Auto-generated method stub
		
		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("userId", userId);
		List<DrawingsRecord> users = this.selectByMap(conMap);
		
		return users;
	}

}
