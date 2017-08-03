package com.hongwei.moddle.sys.controller;

import com.hongwei.common.bean.Pager;
import com.hongwei.common.bean.ResultData;
import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.Permission;
import com.hongwei.moddle.sys.entity.SysArea;
import com.hongwei.moddle.sys.service.SysAreaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地区视图层
 */
@Controller
@RequestMapping("sysArea")
public class SysAreaController extends BaseController {

    @Resource
    private SysAreaService sysAreaService;

    @ModelAttribute
    public SysArea get(@RequestParam(required=false) Long id) {
        if (id != null){
            return sysAreaService.selectById(id);
        }else{
            return new SysArea();
        }
    }

    //分页列表
    @Permission("sys:sysArea:view")
    @RequestMapping("page")
    public String page(SysArea sysArea, Pager<SysArea> pager, Model model){
        Pager<SysArea> page = sysAreaService.selectPage(sysArea, pager);


        SysArea area = new SysArea();
        area.setLevel(1);
        List<SysArea> sysAreas = sysAreaService.selectAll(area);
        model.addAttribute("province",sysAreas);

        model.addAttribute("pager",page);
        model.addAttribute("sysArea",sysArea);
        return "sys/sysArea/sysAreaPage";
    }

    @ResponseBody
    @GetMapping("getAreaByPid")
    public ResultData<Object> getAreaByPid(Long pid){
        try {
            SysArea area = new SysArea();
            area.setPid(pid);
            List<SysArea> sysAreas = sysAreaService.selectAll(area);
            return success(sysAreas);
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }

    //跳转添加编辑页面
    @Permission("sys:sysArea:view")
    @GetMapping("saveFrom")
    public String saveFrom(SysArea sysArea,Model model){
        model.addAttribute("sysArea",sysArea);
        return "sys/sysArea/sysAreaSave";
    }

    //添加编辑操作
    @Permission("sys:sysArea:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(SysArea sysArea){
        try {
            sysAreaService.save(sysArea);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }


}