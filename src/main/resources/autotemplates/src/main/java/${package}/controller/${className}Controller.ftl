package ${package}.controller;

import com.hongwei.common.bean.Pager;
import com.hongwei.common.bean.ResultData;
import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.Permission;
import ${package}.entity.${className};
import ${package}.service.${className}Service;
<#if autoTable.isAllDel==1 || autoTable.isAllShow==1 || autoTable.isAllStatus==1>
import org.apache.commons.lang3.StringUtils;
</#if>
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
 * ${info}${autoInfo.controllerHeader}
 */
@Controller
@RequestMapping("${classNameLower}")
public class ${className}Controller extends BaseController {

    @Resource
    private ${className}Service ${classNameLower}Service;

    @ModelAttribute
    public ${className} get(@RequestParam(required=false) Long id) {
        if (id != null){
            return ${classNameLower}Service.selectById(id);
        }else{
            return new ${className}();
        }
    }

    //${autoInfo.controllerPage}
    @Permission("${model}:${classNameLower}:view")
    @RequestMapping("page")
    public String page(${className} ${classNameLower}, Pager<${className}> pager, Model model){
        Pager<${className}> page = ${classNameLower}Service.selectPage(${classNameLower}, pager);
        model.addAttribute("pager",page);
        model.addAttribute("${classNameLower}",${classNameLower});
        return "${model}/${classNameLower}/${classNameLower}Page";
    }

    //${autoInfo.controllerSaveFrom}
    @Permission("${model}:${classNameLower}:view")
    @GetMapping("saveFrom")
    public String saveFrom(${className} ${classNameLower},Model model){
        model.addAttribute("${classNameLower}",${classNameLower});
        return "${model}/${classNameLower}/${classNameLower}Save";
    }

    //${autoInfo.controllerSave}
    @Permission("${model}:${classNameLower}:edit")
    @ResponseBody
    @PostMapping("save")
    public ResultData<Object> save(${className} ${classNameLower}){
        try {
            ${classNameLower}Service.save(${classNameLower});
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error();
        }
    }
    <#if autoTable.isDel==1>

    //${autoInfo.controllerDeleteById}
    @Permission("${model}:${classNameLower}:edit")
    @GetMapping("delById")
    public String delById(Long id){
        if(id != null){
            ${classNameLower}Service.delById(id);
        }
        return "redirect:/${classNameLower}/page";
    }
    </#if>
    <#if autoTable.isAllDel==1>

    //${autoInfo.controllerDeleteByIds}
    @Permission("${model}:${classNameLower}:edit")
    @GetMapping("delByIds")
    public String delByIds(String ids){
        if(StringUtils.isBlank(ids)){
            ${classNameLower}Service.deleteByIds(ids.split(","));
        }
        return "redirect:/${classNameLower}/page";
    }
    </#if>
    <#if autoTable.isShow==1 || autoTable.isAllShow==1>

    //${autoInfo.controllerIsShow}
    @Permission("${model}:${classNameLower}:edit")
    @GetMapping("isShow")
    public String isShow(String ids,Integer showType){
        if(ids != null){
            ${classNameLower}Service.isShow(ids,showType);
        }
        return "redirect:/${classNameLower}/page";
    }
    </#if>
    <#if autoTable.isStatus==1 || autoTable.isAllStatus==1>

    //${autoInfo.controllerIsStatus}
    @Permission("${model}:${classNameLower}:edit")
    @GetMapping("accredit")
    public String accredit(String ids,Integer status){
        if(ids != null){
            ${classNameLower}Service.accredit(ids,status);
        }
        return "redirect:/${classNameLower}/page";
    }
    </#if>


}