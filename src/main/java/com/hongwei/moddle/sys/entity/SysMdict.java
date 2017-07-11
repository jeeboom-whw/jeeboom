package com.hongwei.moddle.sys.entity;

import java.io.Serializable;

/**
 * 数据字典实体类
 */
public class SysMdict implements Serializable {

	private static final long serialVersionUID = 1L;

	/**  ID编号 */
	private Long id;
	/**  标题 */
	private String title;
	/**  标题名称 */
	private String name;
	/**  值 */
	private String value;
	/**  值描述 */
	private String info;
	/**  排序 */
	private Integer orderNo;

	/**  无参构造函数 */
	public SysMdict(){
	}

	// -------------------- GET AND SET --------------------

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getValue(){
		return value;
	}
	
	public void setValue(String value){
		this.value = value;
	}

	public String getInfo(){
		return info;
	}
	
	public void setInfo(String info){
		this.info = info;
	}

	public Integer getOrderNo(){
		return orderNo;
	}
	
	public void setOrderNo(Integer orderNo){
		this.orderNo = orderNo;
	}
}
