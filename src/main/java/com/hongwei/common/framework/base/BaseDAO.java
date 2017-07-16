package com.hongwei.common.framework.base;

import com.hongwei.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公共DAO方法
 */
public interface BaseDAO<T> {

	/** 分页查询数据集合 */
	List<T> selectListByPage(@Param("entity") T t, @Param("pager") Pager<T> pager);

	/** 分页查询数据数量 */
	Long selectCount(@Param("entity") T t);

	/** 通过条件单条查询 */
	T selectOne(@Param("entity") T t);

	/** 通过ID查询 */
	T selectById(@Param("id") Long id);

	/** 新增 */
	void insert(T t);

	/** 批量新增 */
	void insertAll(@Param("entitys")List<T> entitys);

	/** 修改（通过ID只对不为空的内容进行修改） */
	void updateNotNull(T t);

	/** 修改（通过ID更新所有属性值 */
	void updateParams(T t);

	/** 通过ID删除数据 */
	void deleteById(@Param("id") Long id);

	/** 通过IDS批量删除数据 */
	void deleteByIds(@Param("ids") Long[] ids);
}
