package com.hongwei.moddle.sys.service;

import com.hongwei.common.framework.base.BaseService;
import com.hongwei.moddle.sys.dao.SysRoleMenuDAO;
import com.hongwei.moddle.sys.entity.SysRoleMenu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 系统角色菜单关联业务逻辑类
 */
@Service
public class SysRoleMenuService extends BaseService<SysRoleMenu> {

	@Resource
	private SysRoleMenuDAO sysRoleMenuDAO;

	/** 通过角色ID删除关联信息 */
	@Transactional
	public void deleteByRoleId(Long roleId) {
		sysRoleMenuDAO.deleteByRoleId(roleId);
	}
}