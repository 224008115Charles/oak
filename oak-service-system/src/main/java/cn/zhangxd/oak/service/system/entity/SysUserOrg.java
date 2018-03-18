package cn.zhangxd.oak.service.system.entity;

import java.io.Serializable;

/**
 * <p>
 * 用户组织
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
public class SysUserOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 组织ID
     */
    private Integer orgId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "SysUserOrg{" +
        ", userId=" + userId +
        ", orgId=" + orgId +
        "}";
    }
}
