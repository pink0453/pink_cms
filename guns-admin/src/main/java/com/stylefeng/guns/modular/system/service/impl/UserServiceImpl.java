package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.modular.system.dao.UserMapper;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

	@Override
	public List<User> getUsersByParentId(Integer id) {
		// TODO Auto-generated method stub
		
		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("parent_id", id);
		List<User> users = this.selectByMap(conMap);
		
		return users;
		
	}
	
	/**
	 * 层级划分代理列表
	 * @param user
	 * @return
	 */
	public Map<Integer, List<User>> currentUsersLvList(User user){
		
		//key:0代表自己
		Map<Integer, List<User>> lvMap = new HashMap<>();
		
		List<User> currentUser = new ArrayList<>();
		currentUser.add(user);
		lvMap.put(0, currentUser);
		
		return getLvUsersByCurrentUser(lvMap, user.getId(), 1);
		
	}
	
	/**
	 * 查询代理层级列表
	 * @param lvUsers
	 * @param curId
	 * @param lv
	 * @return
	 */
	public Map<Integer, List<User>>  getLvUsersByCurrentUser(Map<Integer, List<User>> lvUsers, Integer curId, int lv) {
		// TODO Auto-generated method stub
		
		List<User> users = getUsersByParentId(curId);
		
		if(users != null && users.size() > 0) {
			
			lvUsers.put(lv, users);
			
			while(true) {
				
				List<User> tmpUsers = new ArrayList<>();
				
				for(int i = 0; i < users.size(); i++) {
					
					tmpUsers.addAll(getUsersByParentId(users.get(i).getId()));
					
				}
				
				if(tmpUsers.size() > 0) {
					
					lv = lv + 1;
					lvUsers.put(lv, tmpUsers);
					
					users = new ArrayList<>();
					users = tmpUsers;
					
				}else {
					
					break;
					
				}
				
			}
			
		}
		
		return lvUsers;
	}
	
}
