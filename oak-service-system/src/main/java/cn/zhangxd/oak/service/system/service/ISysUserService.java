package cn.zhangxd.oak.service.system.service;

import cn.zhangxd.oak.service.system.entity.SysUser;
import cn.zhangxd.oak.core.service.IBaseService;
import cn.zhangxd.oak.service.system.service.dto.WithPasswordUserDTO;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
public interface ISysUserService extends IBaseService<SysUser> {

    /**
     * 通过用户名查询用户及其角色信息
     *
     * @param login 用户名
     * @return 用户
     */
    WithPasswordUserDTO findUserByLogin(String login);
}
