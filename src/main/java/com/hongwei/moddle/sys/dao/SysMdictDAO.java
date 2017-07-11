package com.hongwei.moddle.sys.dao;


import com.hongwei.moddle.sys.entity.SysMdict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据字典持久层接口
 */
@Component
public interface SysMdictDAO {

    @Select("select sys_mdict.value,sys_mdict.info from sys_mdict where sys_mdict.title = #{title} order by order_no")
    public List<SysMdict> selectByTitle(@Param("title")String title);
}
