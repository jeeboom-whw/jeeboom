package com.hongwei.moddle.sys.service;

import com.hongwei.common.constant.CacheConstant;
import com.hongwei.common.framework.base.BaseService;
import com.hongwei.common.util.EhcacheUtil;
import com.hongwei.moddle.sys.dao.SysMenuDAO;
import com.hongwei.moddle.sys.entity.SysMenu;
import com.hongwei.moddle.sys.vo.resp.SysMenuResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统菜单业务逻辑类
 */
@Service
public class SysMenuService extends BaseService<SysMenu> {

	@Resource
	private SysMenuDAO sysMenuDAO;

	@Resource
	private SysRoleService sysRoleService;

	/** 获取登录用户菜单集合 */
	public List<SysMenu> selectByUser(Long userId){
		//获取当前登录用户所关联的角色ID
		List<Long> roleIds = sysRoleService.selectByUser(userId);
		List<SysMenu> sysMenus = sysMenuDAO.selectByRoleIds(roleIds);
		return sysMenus;
	}

	/** 获取登录用户菜单树集合 */
	public List<SysMenuResp> getTreeByUserId(Long userId){
		if(userId != null){
			if(userId == 1){
				//系统管理员获取总所有菜单权限
				SysMenu sysMenu = new SysMenu();
				sysMenu.setStatus(2);
				return install(this.selectAll(sysMenu),2);
			}else{
				return install(this.selectByUser(userId),2);
			}
		}
		return null;
	}

	/** 获取所有的菜单信息 */
	public List<SysMenuResp> getAllTreeList(){
		List<SysMenu> sysMenus = this.selectAll(new SysMenu());
		return install(sysMenus,3);
	}

	//拼装
	public List<SysMenuResp> install(List<SysMenu> sysMenus,Integer show){
		List<SysMenu> firstArray = sysMenus.stream().filter(sysMenu -> (sysMenu.getLevel() == 1 && sysMenu.getIsShow() != show)).collect(Collectors.toList());
		List<SysMenuResp> menuResps = sortSysMenuResp(firstArray);
		menuResps.forEach(sysMenuResp -> {
			forEach(sysMenuResp,sysMenus,show);
		});
		return menuResps;
	}

	//迭代循环
	public void forEach(SysMenuResp sysMenuResp,List<SysMenu> sysMenus,Integer hide){
		List<SysMenu> menus = sysMenus.stream().filter(sysMenu -> sysMenuResp.getId().equals(sysMenu.getPid()) && sysMenu.getIsShow() != hide).collect(Collectors.toList());
		if(menus != null && menus.size() > 0){
			List<SysMenuResp> childs = this.sortSysMenuResp(menus);
			sysMenuResp.setSysMenuResps(childs);
			childs.forEach(menuResp -> {
				forEach(menuResp,sysMenus,hide);
			});
		}
	}

	//排序并且转换实体类
	private List<SysMenuResp> sortSysMenuResp(List<SysMenu> sysMenus){
		//排序
		sysMenus.sort(new Comparator<SysMenu>() {
			@Override
			public int compare(SysMenu o1, SysMenu o2) {
				return o1.getOrderNo().compareTo(o2.getOrderNo());
			}
		});
		return sysMenus.stream().map( sysMenu -> toSysMenuResp(sysMenu)).collect(Collectors.toList());
	}

	//转换实体类
	private SysMenuResp toSysMenuResp(SysMenu sysMenu){
		SysMenuResp sysMenuResp = new SysMenuResp();
		BeanUtils.copyProperties(sysMenu, sysMenuResp);
		return sysMenuResp;
	}

	//上下架
    public void accredit(Long id, Integer status) {
		sysMenuDAO.accredit(id,status);
    }

    //根绝角色ID获取菜单列表
    public List<SysMenu> selectByRoleId(Long roleId){
		List<Long> roleIds = new ArrayList<Long>();
		roleIds.add(roleId);
		List<SysMenu> sysMenus = sysMenuDAO.selectByRoleIds(roleIds);
		return sysMenus;
	}

	//根据用户id获取权限标识列表
	public List<String> selectPermissions(Long userId){
		List<Long> roleIds = sysRoleService.selectByUser(userId);
		return sysMenuDAO.selectPermissions(roleIds);
	}

	//通过用户ID获取缓存中用户菜单
	public List<String> getPermissionByCache(Long userId){
		List<String> permissions = null;
		try {
			permissions = (List<String>) EhcacheUtil.get(CacheConstant.MENU_CACHE.LOGIN_PERMISSION + userId);
		} finally {
			if(permissions == null){
				permissions = this.selectPermissions(userId);
			}
		}
		return permissions;
	}
}