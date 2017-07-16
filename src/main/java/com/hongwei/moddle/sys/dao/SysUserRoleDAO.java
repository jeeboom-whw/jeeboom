package com.hongwei.moddle.sys.dao;


import com.hongwei.common.framework.base.BaseDAO;
import com.hongwei.moddle.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 系统用户角色关联持久层接口
 */
@Component
public interface SysUserRoleDAO {

    /** 通过角色ID删除关联信息 */
    public void deleteByUserId(@Param("userId")Long userId);
	
}
@Component
interface AutoSysUserRoleDAO extends BaseDAO<SysUserRole> {

}