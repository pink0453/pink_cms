package com.stylefeng.guns.modular.stat.service.impl;

import com.stylefeng.guns.modular.MongoDao.StatRegisterDao;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_register;
import com.stylefeng.guns.modular.stat.service.IRegisterService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-05
 */
@Service
public class RegisterServiceImpl implements IRegisterService {

	@Autowired
	private StatRegisterDao registerDao;
	
	/**
	 * 通过条件查询
	 */
	@Override
	public List<Mj_stat_register> findRegsByCondition(long date) {
		// TODO Auto-generated method stub
		long to = date + 86400;
		return registerDao.findByTimeRange(date, to);
	}

	@Override
	public List<Mj_stat_register> findAll() {
		// TODO Auto-generated method stub
		return registerDao.findAll();
	}
	
}
