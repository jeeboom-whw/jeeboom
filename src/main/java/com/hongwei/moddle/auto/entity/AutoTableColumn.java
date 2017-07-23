package com.hongwei.moddle.auto.entity;

import com.hongwei.common.auto.util.CamelCaseUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 属性列表实体类
 */
public class AutoTableColumn implements Serializable {

	private static final long serialVersionUID = 1L;

	/**  ID编号 */
	private Long id;
	/**  表id */
	private Long tableId;
	/**  数据库属性名称 */
	private String columnName;
	/**  属性别名 */
	private String name;
	/** 首字母小写 */
	private String nameUpper;
	/**  数据库类型 */
	private String columnType;
	/**  JAVA类型 */
	private String type;
	/**  长度 */
	private Integer length;
	/**  注释 */
	private String label;
	/**  是否为非空 */
	private Integer nullable;
	/**  是否展示在列表 */
	private Integer isList;
	/**  是否为查询条件 */
	private Integer isSelect;
	/**  查询种类 1 相等 2 like */
	private Integer isSelectType;
	/**  排序 */
	private Integer orderNo;
	/**  数据字典title */
	private String mdictTitle;

	/**  无参构造函数 */
	public AutoTableColumn(){
	}

	// -------------------- GET AND SET --------------------

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public Long getTableId(){
		return tableId;
	}
	
	public void setTableId(Long tableId){
		this.tableId = tableId;
	}

	public String getColumnName(){
		return columnName;
	}
	
	public void setColumnName(String columnName){
		this.columnName = columnName;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.nameUpper = CamelCaseUtils.toCamelCase(name);
		this.name = name;
	}

	public String getColumnType(){
		return columnType;
	}
	
	public void setColumnType(String columnType){
		this.columnType = columnType;
	}

	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}

	public Integer getLength(){
		return length;
	}
	
	public void setLength(Integer length){
		this.length = length;
	}

	public String getLabel(){
		return label;
	}
	
	public void setLabel(String label){
		this.label = label;
	}

	public Integer getNullable(){
		return nullable;
	}
	
	public void setNullable(Integer nullable){
		if(nullable==null){
			nullable = 0;
		}
		this.nullable = nullable;
	}

	public Integer getIsList(){
		return isList;
	}
	
	public void setIsList(Integer isList){
		if(isList==null){
			isList = 0;
		}
		this.isList = isList;
	}

	public Integer getIsSelect(){
		return isSelect;
	}
	
	public void setIsSelect(Integer isSelect){
		if(isSelect==null){
			isSelect = 0;
		}
		this.isSelect = isSelect;
	}

	public Integer getIsSelectType(){
		return isSelectType;
	}
	
	public void setIsSelectType(Integer isSelectType){
		this.isSelectType = isSelectType;
	}

	public Integer getOrderNo(){
		return orderNo;
	}
	
	public void setOrderNo(Integer orderNo){
		this.orderNo = orderNo;
	}

	public String getMdictTitle(){
		return mdictTitle;
	}
	
	public void setMdictTitle(String mdictTitle){
		this.mdictTitle = mdictTitle;
	}

	public String getNameUpper() {
		return nameUpper;
	}

	public void setNameUpper(String nameUpper) {
		this.nameUpper = nameUpper;
	}
}
