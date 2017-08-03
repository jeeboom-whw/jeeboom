package com.hongwei.moddle.sys.dao;

import com.hongwei.common.framework.base.BaseDAO;
import com.hongwei.moddle.sys.entity.SysArea;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * 地区持久层
 */
@Component
public interface SysAreaDAO {

}
@Component
interface AutoSysAreaDAO extends BaseDAO<SysArea>{

}