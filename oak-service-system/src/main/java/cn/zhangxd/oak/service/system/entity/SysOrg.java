package cn.zhangxd.oak.service.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import cn.zhangxd.oak.core.entity.BaseEntity;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
public class SysOrg extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父ID
     */
    @TableField("pid")
    private Integer pid;
    /**
     * 组织名称
     */
    @TableField("name")
    private String name;
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
        return "SysOrg{" +
        ", pid=" + pid +
        ", name=" + name +
        ", remarks=" + remarks +
        ", deleted=" + deleted +
        "}";
    }
}
