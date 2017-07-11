package com.hongwei.moddle.base.controller;

import com.hongwei.common.interfaces.NotLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/6/14 0014.
 */
@NotLogin
@Controller
public class MainsiteErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(HttpServletResponse response){
        if(response.getStatus()==400){
            return "common/error/page400";
        }else if(response.getStatus()==404){
            return "common/error/page404";
        }else if(response.getStatus()==500){
            return "common/error/page500";
        }
        return "common/error/pageOther";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
