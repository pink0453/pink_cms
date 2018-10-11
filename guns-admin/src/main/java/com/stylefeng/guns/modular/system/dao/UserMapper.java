package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.core.datascope.DataScope;
import com.stylefeng.guns.modular.system.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public interface UserMapper extends BaseMapper<User> {

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
     * 通过游戏id获取用户
     * @param account
     * @return
     */
    User getByGameAccountId(@Param("game_account_id") String account);
    
    List<Map<String, Object>> selectUsersByIds(List<Integer> ids);
    
    /**
     * 更新返利
     * @param userId
     * @param fanli
     * @return
     */
    int updateFanli(@Param("userId") Integer userId, @Param("fanli") float fanli);
    
    /**
     * 更新金币
     * @param userId
     * @param jinbi
     * @return
     */
    int updateJinbi(@Param("userId") Integer userId, @Param("jinbi") Integer jinbi);
}