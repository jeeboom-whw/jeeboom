package com.hongwei.moddle.sys.service;

import com.hongwei.common.framework.base.BaseService;
import com.hongwei.moddle.sys.dao.SysUserDAO;
import com.hongwei.moddle.sys.entity.SysUser;
import com.hongwei.moddle.sys.entity.SysUserRole;
import com.hongwei.moddle.sys.vo.resp.SysUserResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户业务逻辑类
 */
@Service
public class SysUserService extends BaseService<SysUser> {

	@Resource
	private SysUserDAO sysUserDAO;

	@Resource
	private SysUserRoleService sysUserRoleService;

	/** 用户登录成功 */
	@Transactional
	public void userLogin(Long userId){
		sysUserDAO.userLogin(userId);
	}

	/** 添加、修改用户信息 */
	@Transactional
	public void save(SysUser sysUser,Long[] roleIds,Long loginUserId){
		sysUser.setUpdateBy(loginUserId);
		//判断是新增或者是修改
		Date date = new Date();
		if(sysUser.getId()==null){
			sysUser.setCreateBy(loginUserId);
			sysUser.setStatus(2);
			sysUser.setUpdateTime(date);
			sysUser.setCreateTime(date);
			this.insert(sysUser);
		}else{
			sysUserRoleService.deleteByUserId(sysUser.getId());
			sysUser.setUpdateTime(date);
			this.updateNotNull(sysUser);
		}
		for(Long roleId : roleIds) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setSysRoleId(roleId);
			sysUserRole.setSysUserId(sysUser.getId());
			sysUserRoleService.insert(sysUserRole);
		}
	}

	/** 获取用户详情 */
	public SysUserResp info(Long userId){
		SysUser sysUser = this.selectById(userId);
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setSysUserId(userId);
		List<Long> roleIds = sysUserRoleService.selectAll(sysUserRole).stream().map(userRole -> userRole.getSysRoleId()).collect(Collectors.toList());
		SysUserResp sysUserResp = new SysUserResp();
		BeanUtils.copyProperties(sysUser,sysUserResp);
		sysUserResp.setRoleIds(roleIds);
		return sysUserResp;
	}

    public void accredit(Long id, Integer status) {
		sysUserDAO.accredit(id,status);
    }
}