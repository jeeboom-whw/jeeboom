package ${package}.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * ${entity.info}实体类
 */
public class ${className} implements Serializable {

	private static final long serialVersionUID = 1L;

<#list entity.table.columns as column>
	/**  ${column.label} */
	<#if column.type == 'java.util.Date'>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	</#if>
	private ${column.type} ${column.name};
</#list>

	/**  无参构造函数 */
	public ${className}(){
	}

	// -------------------- GET AND SET --------------------
<#list entity.table.columns as column>

	public ${column.type} get${column.nameUpper}(){
		return ${column.name};
	}
	
	public void set${column.nameUpper}(${column.type} ${column.name}){
		this.${column.name} = ${column.name};
	}
</#list>
}
