package cn.zhangxd.oak.service.system.mapper;

import cn.zhangxd.oak.service.system.entity.SysUser;
import cn.zhangxd.oak.core.mapper.BaseMapper;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据登录名查询用户及角色
     *
     * @param lowercaseLogin
     * @return
     */
    SysUser selectOneWithRoleByLogin(String lowercaseLogin);

}
