package cn.zhangxd.oak.service.system.entity;

import java.io.Serializable;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 角色ID
     */
    private Integer roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
        ", userId=" + userId +
        ", roleId=" + roleId +
        "}";
    }
}
