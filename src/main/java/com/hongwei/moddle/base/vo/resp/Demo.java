package com.hongwei.moddle.base.vo.resp;

import com.hongwei.common.interfaces.OutputExcel;
import com.hongwei.common.util.OutputExcelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/10 0010.
 */
public class Demo {

    @OutputExcel(title = "姓名",sort = 5)
    private String name;
    @OutputExcel(title = "手机号码",sort = 10)
    private String phone;
    @OutputExcel(title = "年龄",sort = 15)
    private Integer age;
    @OutputExcel(title = "性别",sort = 20)
    private String sex;
    @OutputExcel(title = "加入时间",sort = 25)
    private String infoTime;
    @OutputExcel(title = "详细",sort = 30)
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(String infoTime) {
        this.infoTime = infoTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
