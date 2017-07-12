package com.hongwei.moddle.sys.controller;

import com.hongwei.common.bean.Pager;
import com.hongwei.common.bean.ResultData;
import com.hongwei.common.constant.CacheConstant;
import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.Permission;
import com.hongwei.common.util.EhcacheUtil;
import com.hongwei.moddle.sys.entity.SysMdict;
import com.hongwei.moddle.sys.service.SysMdictService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 数据字典视图层
 */
@Controller
@RequestMapping("sysMdict")
public class SysMdictController extends BaseController {

	@Resource
	private SysMdictService sysMdictService;

	@ModelAttribute
	public SysMdict get(@RequestParam(required=false) Long id) {
		if (id != null){
			return sysMdictService.selectById(id);
		}else{
			return new SysMdict();
		}
	}

	//分页列表
	@Permission("sys:sysMdict:view")
	@GetMapping("page")
	public String page(SysMdict sysMdict, Pager<SysMdict> pager, Model model){
		pager.setOrderBy("title ASC, id DESC");
		Pager<SysMdict> page = sysMdictService.selectPage(sysMdict, pager);
		model.addAttribute("pager",page);
		model.addAttribute("sysMdict",sysMdict);
        return "sys/sysMdict/sysMdictPage";
	}

	//跳转添加编辑页面
	@Permission("sys:sysMdict:view")
	@GetMapping("saveFrom")
	public String saveFrom(SysMdict sysMdict,Model model){
		model.addAttribute("sysMdict",sysMdict);
		return "sys/sysMdict/sysMdictSave";
	}

	//跳转添加编辑页面
	@Permission("sys:sysMdict:view")
	@GetMapping("saveFromTwo")
	public String saveFromTwo(SysMdict sysMdict,Model model){
		SysMdict mdict = new SysMdict();
		mdict.setTitle(sysMdict.getTitle());
		mdict.setName(sysMdict.getName());
		mdict.setOrderNo(sysMdict.getOrderNo()+10);
		model.addAttribute("sysMdict",mdict);
		return "sys/sysMdict/sysMdictSave";
	}

	//添加编辑操作
	@Permission("sys:sysMdict:edit")
	@ResponseBody
	@PostMapping("save")
	public ResultData<Object> save(SysMdict sysMdict){
		try {
			sysMdictService.save(sysMdict);
			return success();
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
	}

    //删除
    @Permission("sys:sysMdict:edit")
    @GetMapping("delById")
    public String delById(SysMdict sysMdict){
		if(sysMdict.getId() != null){
			sysMdictService.delById(sysMdict.getId());
		}
		EhcacheUtil.remove(CacheConstant.MENU_CACHE.MDICT_TITLE + sysMdict.getTitle());
		EhcacheUtil.remove(sysMdict.getTitle() + CacheConstant.MENU_CACHE.MDICT_TITLE_VALUE + sysMdict.getValue());
   		return "redirect:/sysMdict/page";
    }
}