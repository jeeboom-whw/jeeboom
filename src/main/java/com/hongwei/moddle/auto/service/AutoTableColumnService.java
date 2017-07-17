package com.hongwei.moddle.auto.service;

import com.hongwei.common.framework.base.BaseService;
import com.hongwei.moddle.auto.dao.AutoTableColumnDAO;
import com.hongwei.moddle.auto.entity.AutoTableColumn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 属性列表业务逻辑类
 */
@Service
public class AutoTableColumnService extends BaseService<AutoTableColumn> {

	@Resource
	private AutoTableColumnDAO autoTableColumnDAO;

    /** 保存 */
    public void save(AutoTableColumn autoTableColumn) {
        if(autoTableColumn.getId() == null){
            this.insert(autoTableColumn);
        }else{
            this.updateParams(autoTableColumn);
        }
    }
}