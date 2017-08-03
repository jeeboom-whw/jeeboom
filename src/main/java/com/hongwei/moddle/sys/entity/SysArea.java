package com.hongwei.moddle.sys.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 地区实体类
 */
public class SysArea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**  id编号 */
    private Long id;
    /**  名称 */
    private String name;
    /**  父级id */
    private Long pid;
    /**  级别 */
    private Integer level;

    public SysArea(){
    }

    // -------------------- GET AND SET --------------------

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getPid(){
        return pid;
    }

    public void setPid(Long pid){
        this.pid = pid;
    }

    public Integer getLevel(){
        return level;
    }

    public void setLevel(Integer level){
        this.level = level;
    }
}
