package cn.zhangxd.oak.account.service;

import cn.zhangxd.oak.account.entity.User;
import cn.zhangxd.oak.core.service.IBaseService;
import cn.zhangxd.oak.account.service.dto.WithPasswordUserDTO;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 通过用户名查询用户及其角色信息
     *
     * @param login 用户名
     * @return 用户
     */
    WithPasswordUserDTO findUserByLogin(String login);
}
