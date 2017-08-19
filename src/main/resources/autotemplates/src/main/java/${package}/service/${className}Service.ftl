package ${package}.service;

import com.hongwei.common.framework.base.BaseService;
import ${package}.dao.${className}DAO;
import ${package}.entity.${className};
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * ${info}${autoInfo.serviceHeader}
 */
@Service
public class ${className}Service extends BaseService<${className}> {

    @Resource
    private ${className}DAO ${classNameLower}DAO;

    /** ${autoInfo.serviceSave} */
    @Transactional
    public void save(${className} ${classNameLower}) {
        if(${classNameLower}.getId() == null){
            this.insert(${classNameLower});
        }else{
            this.updateParams(${classNameLower});
        }
    }
    <#if autoTable.isStatus==1 || autoTable.isAllStatus==1>

    //${autoInfo.controllerIsStatus}
    @Transactional
    public void accredit(String ids,Integer status){
        ${classNameLower}DAO.accredit(ids,status);
    }
    </#if>
}