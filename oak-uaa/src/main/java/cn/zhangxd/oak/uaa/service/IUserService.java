package cn.zhangxd.oak.uaa.service;

import cn.zhangxd.oak.core.service.IBaseService;
import cn.zhangxd.oak.uaa.entity.User;
import cn.zhangxd.oak.uaa.service.dto.UserDTO;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author zhangxd
 * @since 2018-02-01
 */
public interface IUserService extends IBaseService<User> {

    User registerUser(UserDTO userDTO, String password);

    void activateRegistration(String key);

    void changePassword(String password);

    User getUserWithRoles();

    void updateCurrentAccount(UserDTO userDTO);

    User requestPasswordReset(String mail);

    void completePasswordReset(String newPassword, String key);

}
