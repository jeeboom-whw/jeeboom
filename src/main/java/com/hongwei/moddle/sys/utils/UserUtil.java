package com.hongwei.moddle.sys.utils;

import com.hongwei.common.util.HttpUtil;
import com.hongwei.moddle.sys.entity.SysUser;

import javax.servlet.http.HttpSession;

/** 用户工具类 */
public class UserUtil {

    /** 获取系统当前登录用户 */
    public static SysUser getUser(){
        HttpSession session = HttpUtil.getSession();
        SysUser user = null;
        if(session != null){
            user = (SysUser) session.getAttribute("loginUser");
        }
        return user;
    }

    /** 判断当前用户是否登陆 */
    public static boolean isLogin(){
        return getUser() != null ? true:false;
    }
}
