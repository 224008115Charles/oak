package cn.zhangxd.oak.account.service.dto;

import cn.zhangxd.oak.account.config.Constants;
import cn.zhangxd.oak.account.entity.Role;
import cn.zhangxd.oak.account.entity.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 *
 * @author zhangxd
 */
public class UserDTO {

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String name;

    @Email
    @Size(min = 5, max = 50)
    private String email;

    @Size(max = 50)
    private String phone;

    @Size(max = 50)
    private String mobile;

    private Boolean enabled;

    @Size(max = 255)
    private String remarks;

    private Date gmtCreate;

    private Date gmtModified;

    private Set<String> authorities;

    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserDTO(User user) {
        this(user.getId(), user.getLogin(), user.getName(),
            user.getEmail(), user.getPhone(), user.getMobile(), user.getEnabled(),
            user.getRemarks(), user.getGmtCreate(), user.getGmtModified(),
            user.getRoles().stream().map(Role::getCode)
                .collect(Collectors.toSet()));
    }

    public UserDTO(Long id, String login, String name,
                   String email, String phone, String mobile, Boolean enabled,
                   String remarks, Date gmtCreate, Date gmtModified, Set<String> authorities) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.enabled = enabled;
        this.remarks = remarks;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        this.email = email;
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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", mobile='" + mobile + '\'' +
            ", enabled=" + enabled +
            ", remarks='" + remarks + '\'' +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
            '}';
    }
}
