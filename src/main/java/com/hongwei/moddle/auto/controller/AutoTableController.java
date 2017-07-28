package com.hongwei.moddle.auto.controller;

import com.alibaba.fastjson.JSON;
import com.hongwei.common.auto.bean.Column;
import com.hongwei.common.auto.bean.CommonEntity;
import com.hongwei.common.auto.bean.Table;
import com.hongwei.common.auto.constant.AutoInfoConstant;
import com.hongwei.common.auto.util.CamelCaseUtils;
import com.hongwei.common.auto.util.FileHelper;
import com.hongwei.common.auto.util.MySqlTools;
import com.hongwei.common.bean.Pager;
import com.hongwei.common.bean.ResultData;
import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.Permission;
import com.hongwei.common.util.GlobalValue;
import com.hongwei.moddle.auto.entity.AutoTable;
import com.hongwei.moddle.auto.entity.AutoTableColumn;
import com.hongwei.moddle.auto.service.AutoTableService;
import com.hongwei.moddle.auto.vo.req.AutoTableReq;
import com.hongwei.moddle.sys.entity.SysMenu;
import com.hongwei.moddle.sys.service.SysMenuService;
import com.hongwei.moddle.sys.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.security.AuthProvider;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 数据库表视图层
 */
@Controller
@RequestMapping("autoTable")
public class AutoTableController extends BaseController {

	@Resource
	private AutoTableService autoTableService;

	@Resource
	private SysMenuService sysMenuService;

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
			autoTableColumn.setNameUpper(column.getNameUpper());
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
	public String pathFrom(Model model,AutoTableReq autoTableReq){
		System.out.println(JSON.toJSONString(autoTableReq));
		model.addAttribute("outRoot", GlobalValue.outRoot);
		model.addAttribute("package",GlobalValue.basepackage);
		return "auto/autoTable/autoTableSave";
	}


	@Permission("auto:autoTable:view")
	@PostMapping("createAutoFile")
	public String createAutoFile(Model model,AutoTableReq autoTableReq) throws Exception {
		System.out.println(JSON.toJSONString(autoTableReq));
		//获取当前日期
		SimpleDateFormat sm_date = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat sm_year = new SimpleDateFormat("yyyy年");
		Map<String,Object> map = new HashMap<String, Object>();
		autoTableReq.getAutoTableColumns().forEach(autoTableColumn -> {
			autoTableColumn.setNullable(autoTableColumn.getNullable()==null?0:1);
			autoTableColumn.setIsSelect(autoTableColumn.getIsSelect()==null?0:1);
			autoTableColumn.setIsList(autoTableColumn.getIsList()==null?0:1);
		});
		AutoTable autoTable = autoTableReq.getAutoTable();
		autoTable.setIsAllDel(autoTable.getIsAllDel()==null?0:1);
		autoTable.setIsAllShow(autoTable.getIsAllShow()==null?0:1);
		autoTable.setIsAllStatus(autoTable.getIsAllStatus()==null?0:1);
		autoTable.setIsDel(autoTable.getIsDel()==null?0:1);
		autoTable.setIsShow(autoTable.getIsShow()==null?0:1);
		autoTable.setIsStatus(autoTable.getIsStatus()==null?0:1);
		map.put("autoInfo",new AutoInfoConstant());
		map.put("autoTable",autoTable);	//表信息配置
		map.put("tableColumns",autoTableReq.getAutoTableColumns());	  //列表信息配置
		map.put("model",autoTableReq.getModel());	//模块名称
		map.put("package",autoTableReq.getBasePackage() + "." + autoTableReq.getModel());	//包路径
		map.put("outRoot",autoTableReq.getOutRoot());	//生成代码绝对路径
		map.put("info",autoTableReq.getInfo());	//代码描述
		map.put("createType",autoTableReq.getCreateType());	//代码生成类型
		String name = CamelCaseUtils.toCamelCase(autoTableReq.getAutoTable().getTableName());
		map.put("classNameLower",name);	//生成类首字母小写命名
		map.put("className",name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase()));	//驼峰命名
		map.put("date", sm_date.format(new Date()));
		map.put("year", sm_year.format(new Date()));
		FileHelper.createFileByTemp(autoTableReq.getModel(),map);

		//携带页面
		if(autoTableReq.getCreateType() == 1){
			SysMenu sysMenu = new SysMenu();
			sysMenu.setCreateBy(UserUtil.getUser().getId());
			sysMenu.setCreateTime(new Date());
			sysMenu.setImg("&#xe600;");
			sysMenu.setIsShow(1);
			sysMenu.setLevel(3);
			sysMenu.setName(autoTableReq.getInfo()+"管理");
			sysMenu.setOrderNo(30);
			sysMenu.setPath("/" + name + "/page");
			sysMenu.setPid(59L);
			sysMenu.setStatus(2);
			sysMenu.setUpdateBy(sysMenu.getCreateBy());
			sysMenu.setCreateTime(sysMenu.getCreateTime());
			sysMenuService.insert(sysMenu);
			SysMenu sysMenuEdis = new SysMenu();
			sysMenuEdis.setCreateBy(UserUtil.getUser().getId());
			sysMenuEdis.setCreateTime(new Date());
			sysMenuEdis.setIsShow(2);
			sysMenuEdis.setLevel(4);
			sysMenuEdis.setName("编辑");
			sysMenuEdis.setOrderNo(20);
			sysMenuEdis.setPid(sysMenu.getId());
			sysMenuEdis.setStatus(2);
			sysMenuEdis.setUpdateBy(sysMenuEdis.getCreateBy());
			sysMenuEdis.setCreateTime(sysMenuEdis.getCreateTime());
			sysMenuEdis.setPermision(autoTableReq.getModel()+":"+ name +":edit");
			sysMenuService.insert(sysMenuEdis);
			SysMenu sysMenuView = new SysMenu();
			sysMenuView.setCreateBy(UserUtil.getUser().getId());
			sysMenuView.setCreateTime(new Date());
			sysMenuView.setIsShow(2);
			sysMenuView.setLevel(4);
			sysMenuView.setName("查看");
			sysMenuView.setOrderNo(10);
			sysMenuView.setPid(sysMenu.getId());
			sysMenuView.setStatus(2);
			sysMenuView.setUpdateBy(sysMenuView.getCreateBy());
			sysMenuView.setCreateTime(sysMenuView.getCreateTime());
			sysMenuView.setPermision(autoTableReq.getModel()+":"+ name +":view");
			sysMenuService.insert(sysMenuView);
		}
		return  "redirect:/autoTable/showTables";
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