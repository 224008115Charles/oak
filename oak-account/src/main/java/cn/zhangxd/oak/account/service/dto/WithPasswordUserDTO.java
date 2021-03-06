package cn.zhangxd.oak.account.service.dto;

import cn.zhangxd.oak.account.entity.Role;
import cn.zhangxd.oak.account.entity.User;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 *
 * @author zhangxd
 */
public class WithPasswordUserDTO extends UserDTO {

    private String password;

    public WithPasswordUserDTO() {
        // Empty constructor needed for Jackson.
    }

    public WithPasswordUserDTO(User user) {
        this(user.getId(), user.getLogin(), user.getName(), user.getPassword(),
            user.getEmail(), user.getPhone(), user.getMobile(), user.getEnabled(),
            user.getRemarks(), user.getGmtCreate(), user.getGmtModified(),
            user.getRoles().stream().map(Role::getCode)
                .collect(Collectors.toSet()));
    }

    public WithPasswordUserDTO(Long id, String login, String name, String password,
                               String email, String phone, String mobile, Boolean enabled,
                               String remarks, Date gmtCreate, Date gmtModified, Set<String> authorities) {
        super(id, login, name, email, phone, mobile, enabled, remarks, gmtCreate, gmtModified, authorities);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "WithPasswordUserDTO{" +
            "password='" + password + '\'' +
            '}' + super.toString();
    }
}
