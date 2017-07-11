package com.hongwei.moddle.sys.vo.resp;

/**
 * ZTree对应的实体类
 */
public class ZTreeResp {

    /** id */
    private Long id;
    /** PID */
    private Long pId;
    /** 名称 */
    private String name;
    /** 是否被选中 */
    private boolean checked;
    /** 是否被展开 */
    private boolean open;

    public ZTreeResp(Long id, Long pId, String name, boolean checked) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.checked = checked;
        this.open = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
