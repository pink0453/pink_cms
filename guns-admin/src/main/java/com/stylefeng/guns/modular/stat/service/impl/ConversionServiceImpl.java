package com.stylefeng.guns.modular.stat.service.impl;

import com.stylefeng.guns.modular.mongoDao.StatConversionDao;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_conversion;
import com.stylefeng.guns.modular.stat.service.IConversionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinkman
 * @since 2018-09-17
 */
@Service
public class ConversionServiceImpl implements IConversionService {

	@Autowired
	private StatConversionDao conversionDao;
	
	@Override
	public List<Mj_stat_conversion> findConsByCondition(long date) {
		// TODO Auto-generated method stub
		long to = date + 86400;
		return conversionDao.findByTimeRange(date, to);
	}

	@Override
	public List<Mj_stat_conversion> findAll() {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "time");
		return conversionDao.findAll(sort);
	}

}
