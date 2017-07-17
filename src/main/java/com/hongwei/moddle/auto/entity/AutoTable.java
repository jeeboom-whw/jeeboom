package com.hongwei.moddle.auto.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 数据库表实体类
 */
public class AutoTable implements Serializable {

	private static final long serialVersionUID = 1L;

	/**   */
	private Long id;
	/**  数据库表名 */
	private String tableName;
	/**  表别名 */
	private String name;
	/**  表注释 */
	private String label;
	/**  表生成类型1单表 2树行列表结构 3包含关联关系表(扩展以后用) */
	private Integer type;
	/**  是否有删除功能(真删除) */
	private Integer isDel;
	/**  是否有展示隐藏功能(假删除) */
	private Integer isShow;
	/**  是否有上下架功能 */
	private Integer isStatus;
	/**  批量删除功能(真删除) */
	private Integer isAllDel;
	/**  批量展示隐藏功能(假删除) */
	private Integer isAllShow;
	/**  批量上下架功能 */
	private Integer isAllStatus;

	/**  无参构造函数 */
	public AutoTable(){
	}

	// -------------------- GET AND SET --------------------

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getTableName(){
		return tableName;
	}
	
	public void setTableName(String tableName){
		this.tableName = tableName;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getLabel(){
		return label;
	}
	
	public void setLabel(String label){
		this.label = label;
	}

	public Integer getType(){
		return type;
	}
	
	public void setType(Integer type){
		this.type = type;
	}

	public Integer getIsDel(){
		return isDel;
	}
	
	public void setIsDel(Integer isDel){
		this.isDel = isDel;
	}

	public Integer getIsShow(){
		return isShow;
	}
	
	public void setIsShow(Integer isShow){
		this.isShow = isShow;
	}

	public Integer getIsStatus(){
		return isStatus;
	}
	
	public void setIsStatus(Integer isStatus){
		this.isStatus = isStatus;
	}

	public Integer getIsAllDel(){
		return isAllDel;
	}
	
	public void setIsAllDel(Integer isAllDel){
		this.isAllDel = isAllDel;
	}

	public Integer getIsAllShow(){
		return isAllShow;
	}
	
	public void setIsAllShow(Integer isAllShow){
		this.isAllShow = isAllShow;
	}

	public Integer getIsAllStatus(){
		return isAllStatus;
	}
	
	public void setIsAllStatus(Integer isAllStatus){
		this.isAllStatus = isAllStatus;
	}
}
