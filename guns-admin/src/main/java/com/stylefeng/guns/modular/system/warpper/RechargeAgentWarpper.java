package com.stylefeng.guns.modular.system.warpper;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

public class RechargeAgentWarpper extends BaseControllerWarpper{

	public RechargeAgentWarpper(Object obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		map.put("AGENT_ID", ConstantFactory.me().getAgentIdByPlayerId((Integer) map.get("USER_ID")));
		map.put("AGENT_NAME", ConstantFactory.me().getAgentNameByPlayerId((Integer) map.get("USER_ID")));
		
		Date createTime = (Date) map.get("CREATE_TIME");
		Date updateTime = (Date) map.get("EDIT_TIME");
		
		if(createTime != null) {
			Calendar calendar = Calendar.getInstance();    
			calendar.setTime(createTime);    
			calendar.add(Calendar.HOUR, -13);
			map.put("CREATETIME", calendar.getTime());
		}
		
		if(updateTime != null) {
			Calendar calendar2 = Calendar.getInstance();    
			calendar2.setTime(updateTime);    
			calendar2.add(Calendar.HOUR, -13);
			map.put("UPDATETIME", calendar2.getTime());
		}
		
	}

}
