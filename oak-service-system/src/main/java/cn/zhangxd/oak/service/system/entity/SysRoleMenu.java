package cn.zhangxd.oak.service.system.entity;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 菜单ID
     */
    private Integer menuId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "SysRoleMenu{" +
        ", roleId=" + roleId +
        ", menuId=" + menuId +
        "}";
    }
}
