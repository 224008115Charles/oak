package cn.zhangxd.oak.account.service.impl;

import cn.zhangxd.oak.account.service.IMenuService;
import cn.zhangxd.oak.account.entity.Menu;
import cn.zhangxd.oak.account.mapper.MenuMapper;
import cn.zhangxd.oak.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

}
