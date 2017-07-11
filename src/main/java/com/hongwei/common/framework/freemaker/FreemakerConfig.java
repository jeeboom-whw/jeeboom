package com.hongwei.common.framework.freemaker;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 创建freemaker配置类
 */
@Component
public class FreemakerConfig {

    @Autowired
    @Qualifier("freeMarkerConfiguration")
    private Configuration configuration;

    @Autowired
    private FreemarkerValidtPermission freemarkerValidtPermission;

    @Autowired
    private FreemakerMdict freemakerMdict;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        //权限自定义标签
        configuration.setSharedVariable("role",freemarkerValidtPermission);
        //数据字典
        configuration.setSharedVariable("mdict",freemakerMdict);
    }

}
