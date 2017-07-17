package com.hongwei.moddle.auto.service;

import com.hongwei.common.framework.base.BaseService;
import com.hongwei.moddle.auto.dao.AutoTableDAO;
import com.hongwei.moddle.auto.entity.AutoTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 数据库表业务逻辑类
 */
@Service
public class AutoTableService extends BaseService<AutoTable> {

	@Resource
	private AutoTableDAO autoTableDAO;

    /** 保存 */
    public void save(AutoTable autoTable) {
        if(autoTable.getId() == null){
            this.insert(autoTable);
        }else{
            this.updateParams(autoTable);
        }
    }
}