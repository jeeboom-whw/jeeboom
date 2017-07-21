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
}
