package com.hongwei.moddle.sys.service;

import com.hongwei.common.framework.base.BaseService;
import com.hongwei.moddle.sys.dao.SysUserRoleDAO;
import com.hongwei.moddle.sys.entity.SysUserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 系统用户角色关联业务逻辑类
 */
@Service
public class SysUserRoleService extends BaseService<SysUserRole> {

	@Resource
	private SysUserRoleDAO sysUserRoleDAO;

	/** 通过用户ID删除关联信息 */
	@Transactional
	public void deleteByUserId(Long userId) {
		sysUserRoleDAO.deleteByUserId(userId);
	}
}