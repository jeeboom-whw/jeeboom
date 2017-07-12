package com.hongwei.moddle.base.controller;

import com.hongwei.common.constant.CacheConstant;
import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.NotLogin;
import com.hongwei.common.util.EhcacheUtil;
import com.hongwei.moddle.sys.entity.SysMenu;
import com.hongwei.moddle.sys.entity.SysUser;
import com.hongwei.moddle.sys.service.SysMenuService;
import com.hongwei.moddle.sys.utils.UserUtil;
import com.hongwei.moddle.sys.vo.resp.SysMenuResp;
import com.hongwei.moddle.sys.vo.resp.SysUserResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
@Controller
@RequestMapping("common")
public class CommonController extends BaseController {

    @Resource
    private SysMenuService sysMenuService;

    @GetMapping("index")
    public String index(Model model,Long m){
        SysUser sysUser = UserUtil.getUser();
        if(sysUser!=null){
            //获取用户菜单
            List<SysMenuResp> sysMenuResps = this.getUserMenusByCache(sysUser.getId());
            //创建左侧菜单实体类
            SysMenuResp sysMenuResp = null;
            //获取左侧菜单
            if(m == null && sysMenuResps.size() > 0){
                sysMenuResp = sysMenuResps.get(0);
            }else{
                List<SysMenuResp> resps = sysMenuResps.stream().filter(menu -> menu.getId() == m).collect(Collectors.toList());
                if(resps != null && resps.size() > 0){
                    sysMenuResp = resps.get(0);
                }
            }
            //获取主菜单
            List<SysMenu> menus = sysMenuResps.stream().map(menu -> {
                SysMenu sysMenu = new SysMenu();
                BeanUtils.copyProperties(menu,sysMenu);
                return sysMenu;
            }).collect(Collectors.toList());
            model.addAttribute("menus",menus);
            model.addAttribute("leftTree",sysMenuResp);
            //获取首次访问地址
            if(sysMenuResp != null && sysMenuResp.getSysMenuResps() != null && sysMenuResp.getSysMenuResps().size() > 0){
                SysMenuResp menuResp = sysMenuResp.getSysMenuResps().get(0);
                if(menuResp.getSysMenuResps() != null && menuResp.getSysMenuResps().size() > 0){
                    model.addAttribute("mainUrl",menuResp.getSysMenuResps().get(0).getPath());
                }
            }
        }
        return "common/index";
    }

    @GetMapping("iconFrom")
    public String iconFrom(){
        return "common/iconImg";
    }

    @GetMapping("mySelfFrom")
    public String mySelfFrom(){
        return "common/mySelf";
    }

    @GetMapping("newPwdFrom")
    public String newPwdFrom(){
        return "common/newPwd";
    }

    //通过用户ID获取缓存中用户菜单
    private List<SysMenuResp> getUserMenusByCache(Long userId){
        List<SysMenuResp> sysMenuResps = null;
        try {
            sysMenuResps = (List<SysMenuResp>)EhcacheUtil.get(CacheConstant.MENU_CACHE.LOGIN_MENU + userId);
        } finally {
            if(sysMenuResps == null){
                sysMenuResps = sysMenuService.getTreeByUserId(userId);
            }
        }
        return sysMenuResps;
    }
}
