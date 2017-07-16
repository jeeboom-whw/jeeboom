package ${package}.service;

import com.hongwei.common.framework.base.BaseService;
import ${package}.dao.${className}DAO;
import ${package}.entity.${className};
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * ${entity.info}业务逻辑类
 */
@Service
public class ${className}Service extends BaseService<${className}> {

	@Resource
	private ${className}DAO ${classNameLower}DAO;

    /** 保存 */
    public void save(${className} ${classNameLower}) {
        if(${classNameLower}.getId() == null){
            this.insert(${classNameLower});
        }else{
            this.updateParams(${classNameLower});
        }
    }
}