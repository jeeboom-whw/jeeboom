package com.hongwei.moddle.sys.entity;

import java.io.Serializable;

/**
 * 系统菜单实体类
 */
public class SysMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	/**  ID编号 */
	private Long id;
	/**  菜单名称 */
	private String name;
	/**  父级ID */
	private Long pid;
	/**  当前级别 */
	private Integer level;
	/**  排序号码 */
	private Integer orderNo;
	/**  访问路径 */
	private String path;
	/**  权限标识 */
	private String permision;
	/**  图标标识 */
	private String img;
	/**  是否显示 1是 2否 */
	private Integer isShow;
	/**  是否禁用 1是 2否 */
	private Integer status;
	/**  创建时间 */
	private java.util.Date createTime;
	/**  修改时间 */
	private java.util.Date updateTime;
	/**  创建人 */
	private Long createBy;
	/**  修改人 */
	private Long updateBy;

	/**  无参构造函数 */
	public SysMenu(){
	}

	// -------------------- GET AND SET --------------------

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public Long getPid(){
		return pid;
	}
	
	public void setPid(Long pid){
		this.pid = pid;
	}

	public Integer getLevel(){
		return level;
	}
	
	public void setLevel(Integer level){
		this.level = level;
	}

	public Integer getOrderNo(){
		return orderNo;
	}
	
	public void setOrderNo(Integer orderNo){
		this.orderNo = orderNo;
	}

	public String getPath(){
		return path;
	}
	
	public void setPath(String path){
		this.path = path;
	}

	public String getPermision(){
		return permision;
	}
	
	public void setPermision(String permision){
		this.permision = permision;
	}

	public String getImg(){
		return img;
	}
	
	public void setImg(String img){
		this.img = img;
	}

	public Integer getIsShow(){
		return isShow;
	}
	
	public void setIsShow(Integer isShow){
		this.isShow = isShow;
	}

	public Integer getStatus(){
		return status;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}

	public java.util.Date getCreateTime(){
		return createTime;
	}
	
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public Long getCreateBy(){
		return createBy;
	}
	
	public void setCreateBy(Long createBy){
		this.createBy = createBy;
	}

	public Long getUpdateBy(){
		return updateBy;
	}
	
	public void setUpdateBy(Long updateBy){
		this.updateBy = updateBy;
	}
}
