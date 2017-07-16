package com.hongwei.moddle.sys.dao;


import com.hongwei.common.framework.base.BaseDAO;
import com.hongwei.moddle.sys.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统角色持久层接口
 */
@Component
public interface SysRoleDAO {

    @Select("select sys_role.id from sys_role where sys_role.id in (select sys_user_role.sys_role_id from sys_user_role where sys_user_role.sys_user_id = #{userId}) and sys_role.status = 2")
    List<Long> selectByLoginUser(Long userId);

    @Update("update sys_role set sys_role.status = #{status} where id = #{id}")
    void accredit(@Param("id")Long id, @Param("status")Integer status);
}
@Component
interface AutoSysRoleDAO extends BaseDAO<SysRole> {

}
