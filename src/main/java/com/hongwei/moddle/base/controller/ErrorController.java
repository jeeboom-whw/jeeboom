package com.hongwei.moddle.base.controller;

import com.hongwei.common.framework.base.BaseController;
import com.hongwei.common.interfaces.NotLogin;
import com.hongwei.common.interfaces.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误
 */
@NotLogin
@Controller
@RequestMapping("error")
public class ErrorController extends BaseController {

    @GetMapping("noPermission")
    public String onPermission(){
        return "common/error/page401";
    }

    @NotLogin(false)
    @Permission("error:error:error")
    @GetMapping("permissionTest")
    public String permissionTest(){
        return "common/error/page404";
    }
}
