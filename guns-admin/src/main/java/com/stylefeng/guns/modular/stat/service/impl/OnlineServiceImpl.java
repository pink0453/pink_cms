package com.stylefeng.guns.modular.stat.service.impl;

import com.stylefeng.guns.modular.mongoDao.StatOnlineDao;
import com.stylefeng.guns.modular.mongoModel.Mj_stat_online;
import com.stylefeng.guns.modular.stat.service.IOnlineService;

import java.util.Comparator;
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
 * @since 2018-09-06
 */
@Service
public class OnlineServiceImpl implements IOnlineService {

	@Autowired
	private StatOnlineDao onlineDao; 
	
	@Override
	public List<Mj_stat_online> findROnlineByCondition(long date) {
		// TODO Auto-generated method stub
		long to = date + 86400;
		
		List<Mj_stat_online> ons = onlineDao.findByTimeRange(date, to);
		if(ons != null) {
			
			Comparator<Mj_stat_online> comparator = (s1, s2) -> s1.getBeginTime().compareTo(s2.getBeginTime());
			ons.sort(comparator);
			//comparator.reversed()
		}
		
		return ons;
	}

	@Override
	public List<Mj_stat_online> findAll() {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "beginTime");
		return onlineDao.findAll(sort);
	}

}
