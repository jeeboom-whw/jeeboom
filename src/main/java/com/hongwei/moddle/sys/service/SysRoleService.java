package com.hongwei.moddle.sys.service;

import com.hongwei.common.framework.base.BaseService;
import com.hongwei.moddle.sys.dao.SysRoleDAO;
import com.hongwei.moddle.sys.entity.SysRole;
import com.hongwei.moddle.sys.entity.SysRoleMenu;
import com.hongwei.moddle.sys.vo.resp.SysRoleResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统角色业务逻辑类
 */
@Service
public class SysRoleService extends BaseService<SysRole> {

	@Resource
	private SysRoleDAO sysRoleDAO;

	@Resource
	private SysRoleMenuService sysRoleMenuService;

	/** 保存 */
	@Transactional
	public void save(SysRole sysRole, Long[] menuIds,Long loginUserId){
		sysRole.setUpdateBy(loginUserId);
		//判断是新增还是修改
		Date date = new Date();
		if(sysRole.getId()==null){
			sysRole.setCreateBy(loginUserId);
			sysRole.setStatus(2);
			sysRole.setCreateTime(date);
			sysRole.setUpdateTime(date);
			this.insert(sysRole);
		}else{
			sysRole.setUpdateTime(date);
			this.updateNotNull(sysRole);
			sysRoleMenuService.deleteByRoleId(sysRole.getId());
		}
		//新增关联表信息
		for (Long menuId : menuIds){
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setSysMenuId(menuId);
			sysRoleMenu.setSysRoleId(sysRole.getId());
			sysRoleMenuService.insert(sysRoleMenu);
		}
	}

	/** 查看角色详情 */
	public SysRoleResp selectInfo(Long roleId){
		SysRole sysRole = this.selectById(roleId);
		SysRoleMenu sysRoleMenu = new SysRoleMenu();
		sysRoleMenu.setSysRoleId(roleId);
		List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.selectAll(sysRoleMenu);
		List<Long> menuIds = sysRoleMenus.stream().map(roleMenu -> roleMenu.getSysMenuId()).collect(Collectors.toList());
		SysRoleResp sysRoleResp = new SysRoleResp();
		BeanUtils.copyProperties(sysRole, sysRoleResp);
		sysRoleResp.setMenuIds(menuIds);
		return sysRoleResp;
	}

	/** 通过用户ID获取角色集合 */
	public List<Long> selectByUser(Long userId){
		List<Long> roleIds = sysRoleDAO.selectByLoginUser(userId);
		return roleIds;
	}

	/** 上下架 */
    public void accredit(Long id, Integer status) {
		sysRoleDAO.accredit(id,status);
	}
}