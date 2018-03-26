package cn.zhangxd.oak.account.service.impl;

import cn.zhangxd.oak.account.service.IUserService;
import cn.zhangxd.oak.core.exception.InternalServerErrorException;
import cn.zhangxd.oak.account.entity.User;
import cn.zhangxd.oak.account.mapper.UserMapper;
import cn.zhangxd.oak.core.service.impl.BaseServiceImpl;
import cn.zhangxd.oak.account.service.dto.WithPasswordUserDTO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public WithPasswordUserDTO findUserByLogin(String login) {
        User user = baseMapper.selectOneWithRoleByLogin(login);
        if (user == null) {
            throw new InternalServerErrorException(String.format("用户 '%s' 不存在", login));
        }
        return new WithPasswordUserDTO(user);
    }
}
