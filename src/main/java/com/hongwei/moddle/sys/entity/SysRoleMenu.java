package com.hongwei.moddle.sys.entity;

import java.io.Serializable;

/**
 * 系统角色菜单关联实体类
 */
public class SysRoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	/**  ID编号 */
	private Long id;
	/**  角色ID */
	private Long sysRoleId;
	/**  菜单ID */
	private Long sysMenuId;

	/**  无参构造函数 */
	public SysRoleMenu(){
	}

	// -------------------- GET AND SET --------------------

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public Long getSysRoleId(){
		return sysRoleId;
	}
	
	public void setSysRoleId(Long sysRoleId){
		this.sysRoleId = sysRoleId;
	}

	public Long getSysMenuId(){
		return sysMenuId;
	}
	
	public void setSysMenuId(Long sysMenuId){
		this.sysMenuId = sysMenuId;
	}
}
