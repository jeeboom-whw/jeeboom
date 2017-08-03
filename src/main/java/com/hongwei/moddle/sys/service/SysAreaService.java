package com.hongwei.moddle.sys.service;

import com.hongwei.common.framework.base.BaseService;
import com.hongwei.moddle.sys.dao.SysAreaDAO;
import com.hongwei.moddle.sys.entity.SysArea;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 地区业务逻辑类
 */
@Service
public class SysAreaService extends BaseService<SysArea> {

    @Resource
    private SysAreaDAO sysAreaDAO;

    /** 保存 */
    @Transactional
    public void save(SysArea sysArea) {
        if(sysArea.getId() == null){
            this.insert(sysArea);
        }else{
            this.updateParams(sysArea);
        }
    }
}