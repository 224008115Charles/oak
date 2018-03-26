package cn.zhangxd.oak.account.service.impl;

import cn.zhangxd.oak.account.service.IRoleService;
import cn.zhangxd.oak.account.entity.Role;
import cn.zhangxd.oak.account.mapper.RoleMapper;
import cn.zhangxd.oak.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

}
