package com.hongwei.moddle.sys.entity;

import java.io.Serializable;

/**
 * 系统用户角色关联实体类
 */
public class SysUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	/**  ID编号 */
	private Long id;
	/**  系统用户ID */
	private Long sysUserId;
	/**  系统角色ID */
	private Long sysRoleId;

	/**  无参构造函数 */
	public SysUserRole(){
	}

	// -------------------- GET AND SET --------------------

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public Long getSysUserId(){
		return sysUserId;
	}
	
	public void setSysUserId(Long sysUserId){
		this.sysUserId = sysUserId;
	}

	public Long getSysRoleId(){
		return sysRoleId;
	}
	
	public void setSysRoleId(Long sysRoleId){
		this.sysRoleId = sysRoleId;
	}
}
