package cn.zhangxd.oak.service.system.entity;

import cn.zhangxd.oak.core.entity.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父ID
     */
    @TableField("pid")
    private Integer pid;
    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 链接
     */
    @TableField("href")
    private String href;
    /**
     * 图标
     */
    @TableField("icon")
    private String icon;
    /**
     * 请求路径
     */
    @TableField("url")
    private String url;
    /**
     * 请求方法
     */
    @TableField("method")
    private String method;
    /**
     * 菜单类型（0菜单 1权限）
     */
    @TableField("type")
    private Boolean type;
    /**
     * 权限标识
     */
    @TableField("permission")
    private String permission;
    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;
    /**
     * 删除标记
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        ", pid=" + pid +
        ", name=" + name +
        ", sort=" + sort +
        ", href=" + href +
        ", icon=" + icon +
        ", url=" + url +
        ", method=" + method +
        ", type=" + type +
        ", permission=" + permission +
        ", remarks=" + remarks +
        ", deleted=" + deleted +
        "} " + super.toString();
    }
}
