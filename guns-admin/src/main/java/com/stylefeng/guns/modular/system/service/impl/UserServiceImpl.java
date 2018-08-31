package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.modular.system.dao.UserMapper;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public int setStatus(Integer userId, int status) {
        return this.baseMapper.setStatus(userId, status);
    }

    @Override
    public int changePwd(Integer userId, String pwd) {
        return this.baseMapper.changePwd(userId, pwd);
    }

    @Override
    public List<Map<String, Object>> selectUsers(DataScope dataScope, String name, String beginTime, String endTime, Integer deptid) {
        return this.baseMapper.selectUsers(dataScope, name, beginTime, endTime, deptid);
    }

    @Override
    public int setRoles(Integer userId, String roleIds) {
        return this.baseMapper.setRoles(userId, roleIds);
    }

    @Override
    public User getByAccount(String account) {
        return this.baseMapper.getByAccount(account);
    }

	@Override
	public User getByGameAccountId(String gameAccountId) {
		// TODO Auto-generated method stub
		 return this.baseMapper.getByGameAccountId(gameAccountId);
	}

	@Override
	public List<User> getUsersByCurrentUser(List<User> allUsers, Integer curId) {
		// TODO Auto-generated method stub
		
		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("parent_id", curId);
		List<User> users = this.selectByMap(conMap);
		
		allUsers.addAll(users);
		for(User user : users) {
			
			getUsersByCurrentUser(allUsers, user.getId());
			
		}
		
		return allUsers;
	}

	@Override
	public List<Map<String, Object>> getUsersByids(List<Integer> ids) {
		// TODO Auto-generated method stub
		
		return this.baseMapper.selectUsersByIds(ids);
		
	}
}
