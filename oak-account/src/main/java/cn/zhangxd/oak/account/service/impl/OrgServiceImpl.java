package cn.zhangxd.oak.account.service.impl;

import cn.zhangxd.oak.account.mapper.OrgMapper;
import cn.zhangxd.oak.account.service.IOrgService;
import cn.zhangxd.oak.account.entity.Org;
import cn.zhangxd.oak.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author zhangxd
 * @since 2018-03-18
 */
@Service
public class OrgServiceImpl extends BaseServiceImpl<OrgMapper, Org> implements IOrgService {

}
