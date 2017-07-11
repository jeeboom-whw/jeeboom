package com.hongwei.common.framework.freemaker;

import com.hongwei.moddle.sys.entity.SysMdict;
import com.hongwei.moddle.sys.service.SysMdictService;
import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 数据字典
 */
@Component
public class FreemakerMdict implements TemplateDirectiveModel {

    @Autowired
    private SysMdictService sysMdictService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        //获取title
        TemplateScalarModel title = (TemplateScalarModel) map.get("t");
        //获取value
        TemplateScalarModel value = (TemplateScalarModel) map.get("v");
        //判断value是否为空。空为根据title查询数据字典列表，非空则为根据title与value查询当前数据字典的info值
        if(value == null){
            //获取字典列表
            List<SysMdict> sysMdicts = sysMdictService.selectByTitleCache(title.getAsString());
            //设置返回列表对象
            environment.setVariable("fre_mdicts",getBeanWrapper().wrap(sysMdicts));
        }else{
            //获取字典对象INFO值
            String info = sysMdictService.selectByTitleAndValueCache(title.getAsString(),value.getAsString());
            //设置返回字典信息
            environment.setVariable("fre_info",getBeanWrapper().wrap(info));
        }
        templateDirectiveBody.render(environment.getOut());
    }

    //初始化freemaker中BeanWrapper对象
    public static BeansWrapper getBeanWrapper(){
        BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();
        return beansWrapper;
    }
}
