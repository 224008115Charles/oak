package cn.zhangxd.oak.account.entity;

import cn.zhangxd.oak.core.entity.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 登录名
     */
    @TableField("login")
    private String login;
    /**
     * 密码
     */
    @TableField("password")
    private String password;
    /**
     * 姓名
     */
    @TableField("name")
    private String name;
    /**
     * 邮件
     */
    @TableField("email")
    private String email;
    /**
     * 电话
     */
    @TableField("phone")
    private String phone;
    /**
     * 手机
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 是否可用
     */
    @TableField("is_enabled")
    private Boolean enabled;
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

    @TableField(exist=false)
    private Set<Role> roles = new HashSet<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = StringUtils.lowerCase(email, Locale.ENGLISH);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
        ", login=" + login +
        ", password=" + password +
        ", name=" + name +
        ", email=" + email +
        ", phone=" + phone +
        ", mobile=" + mobile +
        ", enabled=" + enabled +
        ", remarks=" + remarks +
        ", deleted=" + deleted +
        "} " + super.toString();
    }
}
