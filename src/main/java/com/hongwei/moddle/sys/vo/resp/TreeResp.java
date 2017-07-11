package com.hongwei.moddle.sys.vo.resp;

import java.util.List;
import java.util.stream.Collectors;

/**
 * layui.Tree对应的实体类
 */
public class TreeResp {
    /** id */
    private Long id;
    /** 名称 */
    private String name;
    /** 附加字段 */
    private String alias;
    /** 是否展开 */
    private boolean spread;
    /** 子级 */
    private List<TreeResp> children;

    public static TreeResp getTreeResp(List<SysMenuResp> sysMenuResps){
        TreeResp tree = new TreeResp();
        tree.setId(0L);
        tree.setSpread(true);
        tree.setAlias("0");
        tree.setName("主菜单");
        tree.setChildren(getTreeResps(sysMenuResps));
        return tree;
    }

    public static List<TreeResp> getTreeResps(List<SysMenuResp> sysMenuResps){
        List<TreeResp> list = sysMenuResps.stream().map(menu -> {
            TreeResp tree = new TreeResp();
            tree.setId(menu.getId());
            tree.setName(menu.getName());
            tree.setSpread(true);
            tree.setAlias(menu.getLevel().toString());
            if (menu.getSysMenuResps() != null && menu.getSysMenuResps().size() > 0) {
                tree.setChildren(getTreeResps(menu.getSysMenuResps()));
            }
            return tree;
        }).collect(Collectors.toList());
        return list;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<TreeResp> getChildren() {
        return children;
    }

    public void setChildren(List<TreeResp> children) {
        this.children = children;
    }
}
