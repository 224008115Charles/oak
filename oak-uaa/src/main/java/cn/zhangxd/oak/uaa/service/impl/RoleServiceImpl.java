package cn.zhangxd.oak.uaa.service.impl;

import cn.zhangxd.oak.uaa.entity.Role;
import cn.zhangxd.oak.uaa.mapper.RoleMapper;
import cn.zhangxd.oak.uaa.service.IRoleService;
import cn.zhangxd.oak.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author zhangxd
 * @since 2018-02-01
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

}
