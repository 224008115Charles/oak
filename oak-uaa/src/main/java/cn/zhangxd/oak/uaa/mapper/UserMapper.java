package cn.zhangxd.oak.uaa.mapper;

import cn.zhangxd.oak.uaa.entity.User;
import cn.zhangxd.oak.core.mapper.BaseMapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author zhangxd
 * @since 2018-02-01
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据登录名或邮箱查询用户及角色
     *
     * @param lowercaseLogin
     * @return
     */
    User selectOneWithRoleByLoginOrEmail(String lowercaseLogin);
}
