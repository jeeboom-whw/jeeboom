package com.hongwei.moddle.sys.controller;

import com.hongwei.common.constant.CodeEnum;
import com.hongwei.common.bean.Pager;
import com.hongwei.common.bean.ResultData;
import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.Permission;
import com.hongwei.common.util.StringUtil;
import com.hongwei.moddle.sys.entity.SysRole;
import com.hongwei.moddle.sys.entity.SysUser;
import com.hongwei.moddle.sys.service.SysRoleService;
import com.hongwei.moddle.sys.utils.UserUtil;
import com.hongwei.moddle.sys.vo.resp.SysRoleResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/10 0010.
 */
@Controller
@RequestMapping("sysRole")
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;

    //权限列表
    @Permission("sys:sysRole:view")
    @GetMapping("page")
    public String page(SysRole sysRole, Pager<SysRole> pager, Model model){
        pager.setOrderBy("update_time desc");
        Pager<SysRole> sysRolePager = sysRoleService.selectPage(sysRole, pager);
        model.addAttribute("pager",sysRolePager);
        return "sys/sysRole/sysRolePage";
    }

    //跳转编辑页面
    @Permission("sys:sysRole:view")
    @GetMapping("saveFrom")
    public String saveFrom(SysRole sysRole,Model model){
        if(sysRole.getId() != null){
            SysRoleResp sysRoleResp = sysRoleService.selectInfo(sysRole.getId());
            BeanUtils.copyProperties(sysRoleResp,sysRole);
            StringBuffer menuIds = new StringBuffer();
            for (Long menuId : sysRoleResp.getMenuIds()) {
                menuIds.append(menuId).append(",");
            }
            model.addAttribute("menuIds",menuIds);
        }
        model.addAttribute("sysRole",sysRole);
        return "sys/sysRole/sysRoleSave";
    }

    //保存
    @Permission("sys:sysRole:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(SysRole sysRole, String menuIds){
        try {
            SysUser sysUser = UserUtil.getUser();
            if(sysUser == null){
                return error(CodeEnum.SESSION_TIME_OUT);
            }
            sysRoleService.save(sysRole, StringUtil.toLongArray(menuIds),sysUser.getId());
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    /** 上下架 */
    @Permission("sys:sysRole:edit")
    @GetMapping("accredit")
    public String accredit(Long id,Integer status){
        if(id != null && status != null){
            sysRoleService.accredit(id,status==1?2:1);
        }
        return "redirect:/sysRole/page";
    }
}
