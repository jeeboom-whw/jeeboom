package com.hongwei.moddle.auto.dao;

import com.hongwei.common.framework.base.BaseDAO;
import com.hongwei.moddle.auto.entity.AutoTable;
import org.springframework.stereotype.Component;

/**
 * 数据库表持久层接口
 */
@Component
public interface AutoTableDAO {
	
}
@Component
interface AutoAutoTableDAO extends BaseDAO<AutoTable>{

}
