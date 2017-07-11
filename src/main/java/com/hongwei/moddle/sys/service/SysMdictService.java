package com.hongwei.moddle.sys.service;

import com.hongwei.common.constant.CacheConstant;
import com.hongwei.common.framework.base.BaseService;
import com.hongwei.common.util.EhcacheUtil;
import com.hongwei.moddle.sys.dao.SysMdictDAO;
import com.hongwei.moddle.sys.entity.SysMdict;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典业务逻辑类
 */
@Service
public class SysMdictService extends BaseService<SysMdict> {

	@Resource
	private SysMdictDAO sysMdictDAO;

	/** 保存 */
	@Transactional
	public void save(SysMdict sysMdict){
		//判断是新增还是修改
		if(sysMdict.getId()==null){
			this.insert(sysMdict);
		}else{
			this.updateParams(sysMdict);
		}
		EhcacheUtil.remove(CacheConstant.MENU_CACHE.MDICT_TITLE + sysMdict.getTitle());
		EhcacheUtil.remove(sysMdict.getTitle() + CacheConstant.MENU_CACHE.MDICT_TITLE_VALUE + sysMdict.getValue());
	}

	//根据title获取字典列表
	public List<SysMdict> selectByTitleCache(String title){
		//从缓存中获取该字典集合
		List<SysMdict> list = (List) EhcacheUtil.get(CacheConstant.MENU_CACHE.MDICT_TITLE + title);
		//判断缓存中是否存在
		if(list == null){
			//查询数据库获得字典集合
			list = sysMdictDAO.selectByTitle(title);
			//将字典集合存储在缓存中
			EhcacheUtil.put(CacheConstant.MENU_CACHE.MDICT_TITLE + title,list);
		}
		//返回字典集合对象
		return list;
	}

	//根据title与value获取字典对象INFO值
	public String selectByTitleAndValueCache(String title,String value){
		//从缓存中获取该字典INFO值
		String info = (String) EhcacheUtil.get(title + CacheConstant.MENU_CACHE.MDICT_TITLE_VALUE + value);
		//判断缓存中是否存在
		if(StringUtils.isBlank(info)){
			//查询数据库获得字典对象
			SysMdict sysMdict = new SysMdict();
			sysMdict.setTitle(title);
			sysMdict.setValue(value);
			sysMdict = this.selectOne(sysMdict);
			if(sysMdict != null){
				//将字典info值存入缓存
				info = sysMdict.getInfo();
				EhcacheUtil.put(title + CacheConstant.MENU_CACHE.MDICT_TITLE_VALUE + value,info);
			}else{
				info = "没有该字典信息";
			}
		}
		return info;
	}
}