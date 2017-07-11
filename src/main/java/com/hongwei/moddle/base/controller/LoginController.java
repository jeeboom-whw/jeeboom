package com.hongwei.moddle.base.controller;

import com.hongwei.common.constant.CacheConstant;
import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.NotLogin;
import com.hongwei.common.util.EhcacheUtil;
import com.hongwei.common.util.MD5Utils;
import com.hongwei.moddle.sys.entity.SysUser;
import com.hongwei.moddle.sys.service.SysMenuService;
import com.hongwei.moddle.sys.service.SysUserService;
import com.hongwei.moddle.sys.utils.UserUtil;
import com.hongwei.moddle.sys.vo.resp.SysMenuResp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登录Controller
 */
@NotLogin
@Controller
public class LoginController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysMenuService sysMenuService;

    //get请求跳转login页面
    @GetMapping("")
    public String index(){
        return "redirect:/login";
    }

    @GetMapping("login")
    public String login(){
        return UserUtil.isLogin() ? "redirect:/common/index" : "common/login";
    }

    @PostMapping("login")
    public String login(String userName, String password, Model model, HttpSession session){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userName);
        sysUser.setPassword(MD5Utils.getMD5String(password));
        sysUser = sysUserService.selectOne(sysUser);
        if(sysUser!=null){
            if(sysUser.getStatus() == 1){
                model.addAttribute("msg", "status");
                return "common/login";
            }
            sysUserService.userLogin(sysUser.getId());
            session.setAttribute("loginUser", sysUser);
            //缓存登录用户数据
            this.loginCache(sysUser);
            return "redirect:/common/index";
        }else{
            model.addAttribute("userName", userName);
            model.addAttribute("msg", "error");
            return "common/login";
        }
    }

    //注销
    @NotLogin(false)
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/login";
    }

    //用户登录后缓存菜单与权限数据
    private void loginCache(SysUser sysUser){
        List<SysMenuResp> sysMenuResps = sysMenuService.getTreeByUserId(sysUser.getId());
        List<String> permissions = sysMenuService.selectPermissions(sysUser.getId());
        EhcacheUtil.put(CacheConstant.MENU_CACHE.LOGIN_MENU+sysUser.getId(),sysMenuResps);
        EhcacheUtil.put(CacheConstant.MENU_CACHE.LOGIN_PERMISSION+sysUser.getId(),permissions);
    }
}
