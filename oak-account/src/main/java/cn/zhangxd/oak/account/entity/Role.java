package cn.zhangxd.oak.account.entity;

import cn.zhangxd.oak.core.entity.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;

import java.util.Objects;

/**
 * <p>
 * 系统角色
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色代码
     */
    @TableField("code")
    private String code;
    /**
     * 角色名称
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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(code, role.code) &&
            Objects.equals(name, role.name) &&
            Objects.equals(remarks, role.remarks) &&
            Objects.equals(deleted, role.deleted);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, name, remarks, deleted);
    }

    @Override
    public String toString() {
        return "Role{" +
        ", code=" + code +
        ", name=" + name +
        ", remarks=" + remarks +
        ", deleted=" + deleted +
        "} " + super.toString();
    }
}
