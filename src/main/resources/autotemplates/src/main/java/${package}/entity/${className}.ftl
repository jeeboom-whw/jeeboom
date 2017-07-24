package ${package}.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * ${info}${autoInfo.beanHeader}
 */
public class ${className} implements Serializable {

    private static final long serialVersionUID = 1L;

    <#list tableColumns as column>
    /**  ${(column.label)!} */
    <#if column.type == 'java.util.Date'>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${column.type} ${column.name};
    </#list>

    public ${className}(){
    }

    // -------------------- GET AND SET --------------------
    <#list tableColumns as column>

    public ${column.type} get${column.nameUpper}(){
        return ${column.name};
    }

    public void set${column.nameUpper}(${column.type} ${column.name}){
        this.${column.name} = ${column.name};
    }
    </#list>
}
