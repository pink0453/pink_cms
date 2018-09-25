package com.stylefeng.guns.modular.system.warpper;

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
		
	}

}
