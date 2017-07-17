package com.hongwei.moddle.auto.controller;

import com.hongwei.common.bean.Pager;
import com.hongwei.common.bean.ResultData;
import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.Permission;
import com.hongwei.moddle.auto.entity.AutoTableColumn;
import com.hongwei.moddle.auto.service.AutoTableColumnService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 属性列表视图层
 */
@Controller
@RequestMapping("autoTableColumn")
public class AutoTableColumnController extends BaseController {

	@Resource
	private AutoTableColumnService autoTableColumnService;

	@ModelAttribute
	public AutoTableColumn get(@RequestParam(required=false) Long id) {
		if (id != null){
			return autoTableColumnService.selectById(id);
		}else{
			return new AutoTableColumn();
		}
	}

	//分页列表
	@Permission("auto:autoTableColumn:view")
	@GetMapping("page")
	public String page(AutoTableColumn autoTableColumn, Pager<AutoTableColumn> pager, Model model){
		Pager<AutoTableColumn> page = autoTableColumnService.selectPage(autoTableColumn, pager);
		model.addAttribute("pager",page);
		model.addAttribute("autoTableColumn",autoTableColumn);
        return "auto/autoTableColumn/autoTableColumnPage";
	}

	//跳转添加编辑页面
	@Permission("auto:autoTableColumn:view")
	@GetMapping("saveFrom")
	public String saveFrom(AutoTableColumn autoTableColumn,Model model){
		model.addAttribute("autoTableColumn",autoTableColumn);
		return "auto/autoTableColumn/autoTableColumnSave";
    }

	//添加编辑操作
	@Permission("auto:autoTableColumn:edit")
	@ResponseBody
	@PostMapping("save")
	public ResultData<Object> save(AutoTableColumn autoTableColumn){
		try {
			autoTableColumnService.save(autoTableColumn);
			return success();
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
	}

    //删除
    @Permission("auto:autoTableColumn:edit")
    @GetMapping("delById")
    public String delById(Long id){
		if(id != null){
			autoTableColumnService.delById(id);
		}
   		return "redirect:/autoTableColumn/page";
    }
}