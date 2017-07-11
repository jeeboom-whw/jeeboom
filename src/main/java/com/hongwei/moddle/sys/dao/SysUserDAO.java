package com.hongwei.moddle.sys.dao;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * 系统用户持久层接口
 */
@Component
public interface SysUserDAO {

    /** 用户登录成功 */
    @Update("update sys_user set login_time = NOW(),error_time = 0,error_date = null where id = #{id}")
    public int userLogin(Long id);

    /** 上下架 */
    @Update("update sys_user set sys_user.status = #{status} where id = #{id}")
    void accredit(@Param("id")Long id, @Param("status")Integer status);
}
