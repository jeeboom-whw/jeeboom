package com.hongwei.moddle.sys.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import com.hongwei.common.constant.CodeEnum;
import com.hongwei.common.bean.Pager;
import com.hongwei.common.bean.ResultData;
import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.NotLogin;
import com.hongwei.common.interfaces.Permission;
import com.hongwei.common.util.ImportExcelUtil;
import com.hongwei.common.util.MD5Utils;
import com.hongwei.common.util.OutputExcelUtil;
import com.hongwei.common.util.StringUtil;
import com.hongwei.moddle.base.vo.resp.Demo;
import com.hongwei.moddle.sys.entity.SysRole;
import com.hongwei.moddle.sys.entity.SysUser;
import com.hongwei.moddle.sys.service.SysRoleService;
import com.hongwei.moddle.sys.service.SysUserService;
import com.hongwei.moddle.sys.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/4 0004.
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    //分页列表
    @Permission("sys:sysUser:view")
    @GetMapping("page")
    public String page(SysUser sysUser, Pager<SysUser> pager, Model model){
        pager.setOrderBy("update_time desc");
        Pager<SysUser> page = sysUserService.selectPage(sysUser, pager);
        model.addAttribute("pager",page);
        return "sys/sysUser/sysUserPage";
    }

    //跳转用户添加编辑页面
    @Permission("sys:sysUser:view")
    @GetMapping("saveFrom")
    public String saveFrom(SysUser sysUser,Model model){
        if(sysUser.getId() != null){
            sysUser = sysUserService.selectById(sysUser.getId());
        }
        List<SysRole> roles = sysRoleService.selectAll(new SysRole());
        model.addAttribute("sysUser",sysUser);
        model.addAttribute("roles",roles);
        return "sys/sysUser/sysUserSave";
    }

    //添加编辑操作
    @Permission("sys:sysUser:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(SysUser sysUser, String roleIds, HttpServletRequest request){
        try {
            SysUser user = UserUtil.getUser();
            if(user == null){
                return error(CodeEnum.SESSION_TIME_OUT);
            }
            if(StringUtils.isNotBlank(sysUser.getPassword())){
                sysUser.setPassword(MD5Utils.MD5(sysUser.getPassword()));
            }
            sysUserService.save(sysUser, StringUtil.toLongArray(roleIds),user.getId());
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    /** 上下架 */
    @Permission("sys:sysUser:edit")
    @GetMapping("accredit")
    public String accredit(Long id,Integer status){
        if(id != null && status != null){
            sysUserService.accredit(id,status==1?2:1);
        }
        return "redirect:/sysUser/page";
    }

    //根据用户id获取角色ID集合
    @ResponseBody
    @PostMapping("getRids")
    public List<Long> getRids(Long userId){
        return sysRoleService.selectByUser(userId);
    }

    //验证用户名
    @ResponseBody
    @GetMapping("validationUname")
    public boolean validationUname(String username,Long userId){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        SysUser user = sysUserService.selectOne(sysUser);
        if(user == null || ( userId != null && userId.equals(user.getId()))){
            return true;
        }
        return false;
    }

    //验证密码
    @ResponseBody
    @GetMapping("validationPwd")
    public ResultData<Object> validationPwd(String pwd){
        SysUser sysUser = UserUtil.getUser();
        sysUser = sysUserService.selectById(sysUser.getId());
        if(sysUser != null && sysUser.getPassword().equals(MD5Utils.getMD5String(pwd))){
            return success(true);
        }
        return success(false);
    }

    //修改密码
    @ResponseBody
    @PostMapping("updatePwd")
    public ResultData<Object> updatePwd(String newPassword){
        SysUser user = new SysUser();
        user.setId(UserUtil.getUser().getId());
        user.setPassword(MD5Utils.getMD5String(newPassword));
        sysUserService.updateNotNull(user);
        return success();
    }

    //excel导出
    @ResponseBody
    @GetMapping("outputExl")
    public void outputExl(){
        List<SysUser> users = sysUserService.selectAll(new SysUser());
        new  OutputExcelUtil("系统用户.xlsx","系统用户",users,SysUser.class);
    }

    //excel导出
    @ResponseBody
    @PostMapping("importExl")
    public ResultData<Object> importExl(MultipartFile file){
        List<SysUser> list = new ImportExcelUtil(file, 2, SysUser.class).getList();
        System.out.println(JSON.toJSONString(list));
        return success(list);
    }
}
