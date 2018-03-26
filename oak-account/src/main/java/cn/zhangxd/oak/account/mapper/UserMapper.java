package cn.zhangxd.oak.account.mapper;

import cn.zhangxd.oak.account.entity.User;
import cn.zhangxd.oak.core.mapper.BaseMapper;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据登录名查询用户及角色
     *
     * @param lowercaseLogin
     * @return
     */
    User selectOneWithRoleByLogin(String lowercaseLogin);

}
