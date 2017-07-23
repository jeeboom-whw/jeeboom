package com.hongwei.moddle.auto.vo.req;

import com.hongwei.moddle.auto.entity.AutoTable;
import com.hongwei.moddle.auto.entity.AutoTableColumn;

import java.util.List;

/**
 * @author 王宏伟
 * @version 1.0
 * @see <p>功能描述：</p>
 */
public class AutoTableReq {

    private String outRoot;

    private String basePackage;

    private String model;

    private String info;

    private Integer createType;

    private AutoTable autoTable;

    private List<AutoTableColumn> autoTableColumns;

    public AutoTable getAutoTable() {
        return autoTable;
    }

    public void setAutoTable(AutoTable autoTable) {
        this.autoTable = autoTable;
    }

    public List<AutoTableColumn> getAutoTableColumns() {
        return autoTableColumns;
    }

    public void setAutoTableColumns(List<AutoTableColumn> autoTableColumns) {
        this.autoTableColumns = autoTableColumns;
    }

    public String getOutRoot() {
        return outRoot;
    }

    public void setOutRoot(String outRoot) {
        this.outRoot = outRoot;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }
}
