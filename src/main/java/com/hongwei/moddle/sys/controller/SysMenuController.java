package com.hongwei.moddle.sys.controller;

import com.hongwei.common.bean.ResultData;
import com.hongwei.common.constant.CodeEnum;
import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.Permission;
import com.hongwei.moddle.sys.entity.SysMenu;
import com.hongwei.moddle.sys.entity.SysUser;
import com.hongwei.moddle.sys.service.SysMenuService;
import com.hongwei.moddle.sys.utils.UserUtil;
import com.hongwei.moddle.sys.vo.resp.SysMenuResp;
import com.hongwei.moddle.sys.vo.resp.TreeResp;
import com.hongwei.moddle.sys.vo.resp.ZTreeResp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统菜单Controller
 */
@Controller
@RequestMapping("sysMenu")
public class SysMenuController extends BaseController {

    @Resource
    private SysMenuService sysMenuService;

    @ModelAttribute
    public SysMenu get(@RequestParam(required=false) Long id) {
        if (id != null){
            return sysMenuService.selectById(id);
        }else{
            return new SysMenu();
        }
    }

    /** 菜单管理列表 */
    @Permission("sys:sysMenu:view")
    @RequestMapping("treeList")
    public String treeList(Model model){
        List<SysMenuResp> treeList = sysMenuService.getAllTreeList();
        model.addAttribute("treeList",treeList);
        return "sys/sysMenu/sysMenuPage";
    }

    /** 跳转菜单保存跳转页面 */
    @Permission("sys:sysMenu:view")
    @GetMapping("saveForm")
    public String saveFrom(SysMenu sysMenu,Model model){
        if(sysMenu.getPid() != null ){
            if(sysMenu.getPid().equals(0L)){
                model.addAttribute("pname","主菜单");
            }else{
                SysMenu pMenu = sysMenuService.selectById(sysMenu.getPid());
                model.addAttribute("pname",pMenu!=null?pMenu.getName():"");
            }
        }
        model.addAttribute("sysMenu",sysMenu);
        return "sys/sysMenu/sysMenuSave";
    }

    /** 保存菜单 */

    @ResponseBody
    @PostMapping("save")
    @Permission("sys:sysMenu:edit")
    public ResultData<Object> save(SysMenu sysMenu){
        try {
            //获取当前登录用户
            SysUser user = UserUtil.getUser();
            if(user == null){
                return error(CodeEnum.SESSION_TIME_OUT);
            }
            //添加修改人ID
            sysMenu.setUpdateBy(user.getId());
            Date date = new Date();
            if(sysMenu.getId() == null){
                //新增
                sysMenu.setCreateBy(user.getId());
                sysMenu.setStatus(2);
                sysMenu.setCreateTime(date);
                sysMenu.setUpdateTime(date);
                sysMenuService.insert(sysMenu);
            }else{
                //修改
                sysMenu.setUpdateTime(date);
                sysMenu.setUpdateBy(user.getId());
                sysMenuService.updateParams(sysMenu);
            }
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    /** 上下架 */
    @Permission("sys:sysMenu:edit")
    @GetMapping("accredit")
    public String accredit(Long id,Integer status){
        if(id != null && status != null){
            sysMenuService.accredit(id,status==1?2:1);
        }
        return "redirect:/sysMenu/treeList";
    }

    /** 获取上级菜单弹窗跳转页面 */
    @GetMapping("getTreeFrom")
    public String getTreeFrom(){
        return "sys/sysMenu/sysMenuTree";
    }

    /** 获取上级菜单弹窗接口 */
    @ResponseBody
    @PostMapping("getTree")
    public ResultData<Object> getTree(){
        try {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setStatus(2);
            List<SysMenu> sysMenus = sysMenuService.selectAll(sysMenu);
            List<SysMenuResp> sysMenuResps = sysMenuService.install(sysMenus.stream().filter(menu -> menu.getLevel() != 4).collect(Collectors.toList()), 3);
            List<TreeResp> treeResps = new ArrayList<TreeResp>();
            treeResps.add(TreeResp.getTreeResp(sysMenuResps));
            return success(treeResps);
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    /** 编辑权限时获取的ztree.checkbox列表 */
    @ResponseBody
    @PostMapping("getZTree")
    public ResultData<Object> getZTree(Long roleId){
        try {
            //获取所有的有效菜单
            SysMenu sysMenu = new SysMenu();
            sysMenu.setStatus(2);
            List<SysMenu> sysMenus = sysMenuService.selectAll(sysMenu);
            //排序
            sysMenus.sort(new Comparator<SysMenu>() {
                @Override
                public int compare(SysMenu o1, SysMenu o2) {
                    return o1.getOrderNo().compareTo(o2.getOrderNo());
                }
            });
            //根据角色ID获取该角色绑定的有效菜单
            List<Long> checkedIds = new ArrayList<Long>();
            if(roleId != null){
                List<SysMenu> checkedMenus = sysMenuService.selectByRoleId(roleId);
                checkedIds = checkedMenus.stream().map(menu -> new Long(menu.getId())).collect(Collectors.toList());
            }
            //转换ZTREE
            List<Long> finalCheckedIds = checkedIds;
            List<ZTreeResp> ztrees = sysMenus.stream().map(menu ->{
                return new ZTreeResp(menu.getId(),menu.getPid(),menu.getName(),finalCheckedIds.contains(menu.getId()));
            }).collect(Collectors.toList());
            return success(ztrees);
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }
}
