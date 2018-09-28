package com.stylefeng.guns.modular.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.modular.system.model.User;

import io.swagger.models.auth.In;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
public interface IUserService extends IService<User> {

    /**
     * 修改用户状态
     */
    int setStatus(@Param("userId") Integer userId, @Param("status") int status);

    /**
     * 修改密码
     */
    int changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);

    /**
     * 根据条件查询用户列表
     */
    List<Map<String, Object>> selectUsers(@Param("dataScope") DataScope dataScope, @Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("deptid") Integer deptid);

    /**
     * 设置用户的角色
     */
    int setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);

    /**
     * 通过账号获取用户
     */
    User getByAccount(@Param("account") String account);
    
    /**
     * 通过游戏id获取账号
     * @param gameAccountId
     * @return
     */
    User getByGameAccountId(@Param("game_account_id") String gameAccountId);
    
    /**
     * 递归根据当前登录用户查询旗下代理
     * @param users
     * @param curId
     * @return
     */
    List<User> getUsersByCurrentUser(List<User> allUsers, Integer curId);
    
    /**
     * 通过父ID获取所有用户
     * @param id
     * @return
     */
    List<User> getUsersByParentId(Integer id);
    
    List<Map<String, Object>> getUsersByids(List<Integer> ids);
    
    /**
     * 获取当前用户下所有层级列表
     * @param user
     * @return
     */
    public Map<Integer, List<User>> currentUsersLvList(User user);
    
    /**
     * 更新返利
     * @param userId
     * @param fanli
     * @return
     */
    public int updateFanli(Integer userId, float fanli);
    
}
