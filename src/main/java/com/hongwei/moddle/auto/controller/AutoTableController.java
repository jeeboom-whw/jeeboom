package com.hongwei.moddle.auto.controller;

import com.alibaba.fastjson.JSON;
import com.hongwei.auto.bean.Column;
import com.hongwei.auto.bean.Table;
import com.hongwei.auto.util.MySqlTools;
import com.hongwei.common.bean.Pager;
import com.hongwei.common.bean.ResultData;
import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.Permission;
import com.hongwei.moddle.auto.entity.AutoTable;
import com.hongwei.moddle.auto.entity.AutoTableColumn;
import com.hongwei.moddle.auto.service.AutoTableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据库表视图层
 */
@Controller
@RequestMapping("autoTable")
public class AutoTableController extends BaseController {

	@Resource
	private AutoTableService autoTableService;

	@ModelAttribute
	public AutoTable get(@RequestParam(required=false) Long id) {
		if (id != null){
			return autoTableService.selectById(id);
		}else{
			return new AutoTable();
		}
	}

	// 代码生成页面 选择数据库表
	@Permission("auto:autoTable:view")
	@GetMapping("showTables")
	public String showTables(Model model){
		//获取数据库表
		List<Table> tables = MySqlTools.getTables();
		model.addAttribute("tables",tables);
		return "auto/autoTable/showTables";
	}

	// 代码生成页面 获取数据库属性列表
	@Permission("auto:autoTable:view")
	@GetMapping("showColumns")
	public String showColumns(String tableName,Model model){
		List<Column> columns = MySqlTools.getColumns(tableName);
		List<AutoTableColumn> autoTableColumns = new ArrayList<AutoTableColumn>();
		int i = 0;
		for(Column column: columns){
			AutoTableColumn autoTableColumn = new AutoTableColumn();
			autoTableColumn.setColumnName(column.getDbName());
			autoTableColumn.setColumnType(column.getDbType());
			autoTableColumn.setType(column.getType());
			autoTableColumn.setIsList(1);
			autoTableColumn.setIsSelect(0);
			autoTableColumn.setIsSelectType(1);
			autoTableColumn.setLabel(column.getLabel());
			autoTableColumn.setLength(column.getLength());
			autoTableColumn.setName(column.getName());
			autoTableColumn.setNullable(column.getNullable()?0:1);
			autoTableColumn.setOrderNo(i++ * 10);
			autoTableColumns.add(autoTableColumn);
		}
		model.addAttribute("list",autoTableColumns);
		model.addAttribute("tableName",tableName);
		return "auto/autoTableColumn/showColumns";
	}

	// 代码生成页面 选择数据库表
	@Permission("auto:autoTable:view")
	@GetMapping("pathFrom")
	public String pathFrom(Model model,AutoTable autoTable,List<AutoTableColumn> autoTableColumns){
		System.out.println(JSON.toJSONString(autoTable));
		System.out.println(JSON.toJSONString(autoTableColumns));

		return "auto/autoTable/showTables";
	}


	//分页列表
	@Permission("auto:autoTable:view")
	@GetMapping("page")
	public String page(AutoTable autoTable, Pager<AutoTable> pager, Model model){
		Pager<AutoTable> page = autoTableService.selectPage(autoTable, pager);
		model.addAttribute("pager",page);
		model.addAttribute("autoTable",autoTable);
        return "auto/autoTable/autoTablePage";
	}

	//跳转添加编辑页面
	@Permission("auto:autoTable:view")
	@GetMapping("saveFrom")
	public String saveFrom(AutoTable autoTable,Model model){
		model.addAttribute("autoTable",autoTable);
		return "auto/autoTable/autoTableSave";
    }

	//添加编辑操作
	@Permission("auto:autoTable:edit")
	@ResponseBody
	@PostMapping("save")
	public ResultData<Object> save(AutoTable autoTable){
		try {
			autoTableService.save(autoTable);
			return success();
		} catch (Exception e) {
			e.printStackTrace();
			return error();
		}
	}

    //删除
    @Permission("auto:autoTable:edit")
    @GetMapping("delById")
    public String delById(Long id){
		if(id != null){
			autoTableService.delById(id);
		}
   		return "redirect:/autoTable/page";
    }
}