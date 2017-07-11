package com.hongwei.common.framework.freemaker;

import com.hongwei.moddle.sys.entity.SysUser;
import com.hongwei.moddle.sys.service.SysMenuService;
import com.hongwei.moddle.sys.utils.UserUtil;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 页面dom层权限验证
 */
@Component
public class FreemarkerValidtPermission implements TemplateDirectiveModel {

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        //获取当前登录用户
        SysUser sysUser = UserUtil.getUser();
        //初始化状态为FALSE，如果验证通过则状态修改成TRUE
        templateModels[0] = TemplateBooleanModel.FALSE;
        //获取页面传递过来的permission参数
        TemplateScalarModel permission = (TemplateScalarModel) map.get("permission");
        //判断当前登录用户是否存在
        if(sysUser != null){
            //判断是否为超级管理员
            if(sysUser.getId().equals(1L)){
                //超级管理员（id为1）直接验证通过
                templateModels[0] = TemplateBooleanModel.TRUE;
            }else{
                //通过用户ID获取用户的权限标识列表
                List<String> permissions = sysMenuService.getPermissionByCache(sysUser.getId());
                //判断是否包含权限标识
                if(permissions.contains(permission.getAsString())){
                    //包含权限标识返回TRUE
                    templateModels[0] = TemplateBooleanModel.TRUE;
                }
            }
        }
        templateDirectiveBody.render(environment.getOut());
    }
}
