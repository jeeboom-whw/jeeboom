package com.hongwei.common.framework.base;

import com.hongwei.common.bean.Pager;
import com.hongwei.common.util.SpringUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5 0005.
 */
public class BaseService<T> {

    private BaseDAO baseDAO;

    /** 分页查询 */
    public Pager<T> selectPage(T t, Pager<T> pager){
        getBaseDao();
        List<T> objArray = baseDAO.selectListByPage(t, pager);
        Long count = baseDAO.selectCount(t);
        pager.setCount(count);
        pager.setList(objArray);
        return pager;
    }

    /** 查询全部数据 */
    public List<T> selectAll(T t){
        getBaseDao();
        return baseDAO.selectListByPage(t, null);
    }

    /** 通过条件查询一条数据 */
    public T selectOne(T t){
        getBaseDao();
        return (T)baseDAO.selectOne(t);
    }

    /** 通过ID查询 */
    public T selectById(Long id) {
        getBaseDao();
        return (T)baseDAO.selectById(id);
    }

    /** 添加 */
    @Transactional
    public void insert(T t) {
        getBaseDao();
        baseDAO.insert(t);
    }

    /** 修改（通过ID只对不为空的内容进行修改）*/
    @Transactional
    public void updateNotNull(T t) {
        getBaseDao();
        baseDAO.updateNotNull(t);
    }

    /**  修改（通过ID更新所有属性值）*/
    @Transactional
    public void updateParams(T t) {
        getBaseDao();
        baseDAO.updateParams(t);
    }

    /** 通过ID删除 */
    public void delById(Long id){
        getBaseDao();
        baseDAO.deleteById(id);
    }

    private void getBaseDao(){
        String beanDaoStr = "auto" + this.getClass().getSimpleName().replace("Service","DAO");
        baseDAO = (BaseDAO)SpringUtil.getBean(beanDaoStr);
    }
}
