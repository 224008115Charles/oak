package cn.zhangxd.oak.service.system.service.impl;

import cn.zhangxd.oak.core.exception.InternalServerErrorException;
import cn.zhangxd.oak.service.system.entity.SysUser;
import cn.zhangxd.oak.service.system.mapper.SysUserMapper;
import cn.zhangxd.oak.service.system.service.ISysUserService;
import cn.zhangxd.oak.core.service.impl.BaseServiceImpl;
import cn.zhangxd.oak.service.system.service.dto.WithPasswordUserDTO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public WithPasswordUserDTO findUserByLogin(String login) {
        SysUser user = baseMapper.selectOneWithRoleByLogin(login);
        if (user == null) {
            throw new InternalServerErrorException(String.format("用户 '%s' 不存在", login));
        }
        return new WithPasswordUserDTO(user);
    }
}
